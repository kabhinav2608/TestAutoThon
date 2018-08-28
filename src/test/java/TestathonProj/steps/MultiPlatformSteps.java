package TestathonProj.steps;

import Driver.MyMobileDriver;
import TestathonProj.Model.TestModel;
import TestathonProj.api.TestApi;
import TestathonProj.steps.serenity.EndUserSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

/**
 * @author kumar on 28/08/18
 * @project X-search
 */
public class MultiPlatformSteps {


  @Steps
  EndUserSteps endUserSteps;

  TestApi testApi = new TestApi();
  MyMobileDriver myMobileDriver = new MyMobileDriver();
  TestModel testModel = new TestModel();

  String title ;

  @Given("google returns result for samsung")
  public void googReturnsResultForTerm(){
    endUserSteps.is_at_google_home_page();
    endUserSteps.enterSearchTermInGoogle("samsung");
    endUserSteps.startGoogleSearch();
    endUserSteps.checkTitle();
    title = endUserSteps.getNumberofResults();
  }


  @When("^user runs dummy api$")
  public void userRunsDummyApi(){
    System.out.println("----title--"+title);
    if(title!=null|| !title.isEmpty()){
      String response = testApi.prepareRequest();
      testModel.setApiResponse(response);
      System.out.println("-----response----"+response);
    }
  }

  @Then("^data is passed to different env$")
  public void dataIsPassedToDifferentEnv(){

    try {
    if(testModel.getApiResponse()!=null || testModel.getApiResponse().isEmpty()){
      WebDriver driver = myMobileDriver.mobileDriver();
      driver.get("https://www.google.com");
      System.out.println("-----Current URL---"+driver.getCurrentUrl());
      driver.quit();
    }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }
}
