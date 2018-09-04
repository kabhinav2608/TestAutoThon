package movies.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.Do;

import static movies.steps.DirectorNamesSteps.threadInfo;

/**
 * @author kumar on 28/08/18
 * @project X-search
 */

@DefaultUrl("https://www.google.com/")
public class GoogleSearchPage extends PageObject {

    @FindBy(id = "lst-ib")
    private By searchTextBox;

    @FindBy(name = "btnK")
    private By searchButton;

    public void searchName(String movie, String wikiLink) throws Exception {
        WebDriver driver = threadInfo.getDriver(movie);
        driver.get("https://www.google.com/");
        driver.manage().window().fullscreen();
       // Thread.sleep(5000);

        System.out.println("Movie: "+ movie +"Wikilink: "+wikiLink );

        driver.findElement(By.id("lst-ib")).sendKeys(movie);
        driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);

       // driver.get(wikiLink);
    }

}
