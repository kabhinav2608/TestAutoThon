package Movies;

import static ConfigPackage.Config.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DirectorNamesSteps {



    @Given("^a list of movie name and urls$")
    public void aListOfMovieNameAndUrls() {
        System.out.println("current run mode"+runmode);
    }

    @When("^user tries to compare the director names on wikipedia and imdb$")
    public void userTriesToCompareTheDirectorNamesOnWikipediaAndImdb(){

    }

    @Then("^the director names should match$")
    public void theDirectorNamesShouldMatch() {
    }
}
