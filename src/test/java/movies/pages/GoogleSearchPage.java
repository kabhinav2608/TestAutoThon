package movies.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

import static movies.steps.DirectorNamesSteps.threadInfo;

/**
 * @author kumar on 28/08/18
 * @project X-search
 */

@DefaultUrl("https://www.google.com/")
public class GoogleSearchPage extends PageObject {

    @FindBy(id = "lst-ib")
    private WebElementFacade searchTextBox;

    @FindBy(name = "btnK")
    private WebElementFacade searchButton;

    public void searchName(String movie, String wikiLink) throws Exception {
        threadInfo.getDriver(movie).manage().window().fullscreen();
        searchTextBox.withTimeoutOf(10, TimeUnit.SECONDS).waitUntilClickable().click();
        searchTextBox.type(movie);
        searchButton.sendKeys(Keys.ENTER);
        threadInfo.getDriver(movie).get(wikiLink);
    }

}
