package movies.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static movies.steps.DirectorNamesSteps.threadInfo;
import static utils.ScreenShotUtility.takeScreenShot;

public class WikiIMDbPage extends PageObject {


    String keyToVerify;


    @FindBy(xpath = "//*[contains(text(),'Directed by')]//following-sibling::td/a")
    private By wikiDirectorName;

    @FindBy(xpath = "//*[contains (@href,'imdb')]")
    private By imdbLinkFromWiki;

    @FindBy(xpath = "//*[contains (text(),'Director')]/following-sibling::a")
    private By imdbDirectorName;

    private String[] globalDirectorNameResult = null;
    private String[] getGlobalDirectorName = null;

    public void extractDataFromWiki(String movie, String url) throws Exception {

        if (!url.equalsIgnoreCase("No url found")) {
            if (threadInfo.getDo(movie).isMobile) {
                url = url.replaceFirst("en", "en.m");
            }
            System.out.println("here for " + movie);
            threadInfo.getDo(movie).getDriver().get(url);
            WebDriver driver = threadInfo.getDriver(movie);

            //takeScreenshotStart
            String wikiImageName = threadInfo.getDo(movie).moviename + "_wiki.png";
            takeScreenShot(driver, wikiImageName);
            threadInfo.getDo(movie).wikiScreenShot = wikiImageName;
            //takeScreenshotEnd

            List<WebElement> directorNames = driver.findElements(By.xpath("//*[contains(text(),'Directed by')]//following-sibling::td/a"));
            String directorNameResult[] = new String[directorNames.size()];

            for (int i = 0; i < directorNames.size(); i++) {
                directorNameResult[i] = directorNames.get(i).getText();
            }
            threadInfo.getDo(movie).wikidirectornames = directorNameResult;
            threadInfo.getDo(movie).imdburl = driver.findElement(By.xpath("//a[text() = 'IMDb']//preceding-sibling::a[1]")).getAttribute("href");
        } else {
            String[] directorNameResult = {"Not Available"};
            threadInfo.getDo(movie).wikidirectornames = directorNameResult;
            threadInfo.getDo(movie).imdburl = "IMDb Movie URL Not Found";
        }

    }

    public void extractDataFromImdb(String movie, String url) throws Exception {
        List<WebElement> directorElements;

        if (!url.equalsIgnoreCase("No url found")) {
            WebDriver driver = threadInfo.getDriver(movie);


            if (threadInfo.getDo(movie).isMobile) {
                driver.findElement(By.xpath("//*[contains(text(),'External links')]")).click();
                driver.findElement(By.xpath("//a[text() = 'IMDb']//preceding-sibling::a[1]")).click();
                directorElements = driver.findElements(By.xpath("//*[contains (text(),'Director')]//following-sibling::span"));
            } else {

                driver.findElement(By.xpath("//a[text() = 'IMDb']//preceding-sibling::a[1]")).click();
                directorElements = driver.findElements(By.xpath("//*[contains (text(),'Director')]/following-sibling::a"));
            }

            //takeScreenShotStart
            String imdbImageName = threadInfo.getDo(movie).moviename + "_imdb.png";
            takeScreenShot(driver, imdbImageName);
            threadInfo.getDo(movie).imdbScreenShot = imdbImageName;
            //takeScreenShotEnd

            String[] directorName = new String[directorElements.size()];
            for (int i = 0; i < directorElements.size(); i++) {
                directorName[i] = directorElements.get(i).getText();
            }
            threadInfo.getDo(movie).imdbdirectornames = directorName;
        } else {
            String[] directorName = {"Not Available"};
            threadInfo.getDo(movie).imdbdirectornames = directorName;
        }
    }


}
