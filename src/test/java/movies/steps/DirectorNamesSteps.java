package movies.steps;

import configPackage.Config;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import movies.pages.DirectorNameStepsImpl;
import movies.pages.WikiIMDbPage;
import org.junit.Assert;
import utils.Do;
import utils.ImportData;
import utils.JSONObjects;
import utils.ThreadInfo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class DirectorNamesSteps {


    public static ThreadInfo threadInfo;
    public String runmode = Config.runmode;


    DirectorNameStepsImpl directorNameStepsimpl = new DirectorNameStepsImpl();

    public DirectorNamesSteps() throws IllegalAccessException, InstantiationException {
    }

    @Given("^a list of movie name and urls$")
    public void aListOfMovieNameAndUrls() throws Exception {

      System.out.println("---inside given---");
      HashMap<String, String> movies = ImportData.generateData();
      for (Map.Entry<String, String> entry : movies.entrySet()) {
        System.out.println(entry.getKey() + " : " + entry.getValue());
      }

        System.out.println("current run mode" + runmode);

        threadInfo = new ThreadInfo(movies);

        Class[] params = new Class[2];
        params[0] = String.class;
        params[1] = String.class;

      Class cls;
      Object obj;

        //Step Class & Functions

        if(runmode.equals("api"))
        {
          cls = DirectorNameStepsImpl.class;
          obj = cls.newInstance();

          Method m = cls.getDeclaredMethod("extractDataFromWiki", params);

          threadInfo.doMethods(obj, m).startThreads();
          threadInfo.waitForThreadsToComplete();
        }
        else if(runmode.equals("hybrid"))
        {
            Class apicls = DirectorNameStepsImpl.class;
            Object apiobj = apicls.newInstance();

            Class uicls = WikiIMDbPage.class;
            Object uiiobj = uicls.newInstance();

            Method[] uimethods = new Method[1];
            Method[] apimethods = new Method[1];

            uimethods[0] = uicls.getDeclaredMethod("extractDataFromWiki", params);
            apimethods[0] = apicls.getDeclaredMethod("extractDataFromWiki", params);

            threadInfo.doMethods(uiiobj, apiobj, uimethods, apimethods).startThreads();
            threadInfo.waitForThreadsToComplete();
        }
        else
        {
          cls = WikiIMDbPage.class;
          obj = cls.newInstance();

          Method m = cls.getDeclaredMethod("extractDataFromWiki", params);

          threadInfo.doMethods(obj, m).startThreads();
          threadInfo.waitForThreadsToComplete();
        }


    }

    @When("^user tries to extract the director names on wikipedia and imdb$")
    public void userTriesToCompareTheDirectorNamesOnWikipediaAndImdb() throws Exception {


        Class[] params = new Class[2];
        params[0] = String.class;
        params[1] = String.class;

      Class cls;
      Object obj;

      //Step Class & Functions

      if(runmode.equals("api"))
      {
        cls = DirectorNameStepsImpl.class;
        obj = cls.newInstance();

        Method m = cls.getDeclaredMethod("extractDataFromImdb", params);
        threadInfo.setNewMethods("movies.pages.DirectorNameStepsImpl", obj, m).startThreads();
      }
      else if(runmode.equals("hybrid"))
      {
          Class apicls = DirectorNameStepsImpl.class;
          Object apiobj = apicls.newInstance();

          Class uicls = WikiIMDbPage.class;
          Object uiiobj = uicls.newInstance();

          Method[] uimethods = new Method[1];
          Method[] apimethods = new Method[1];

          uimethods[0] = uicls.getDeclaredMethod("extractDataFromWiki", params);
          apimethods[0] = apicls.getDeclaredMethod("extractDataFromWiki", params);

          threadInfo.setNewMethods(uiiobj, apiobj, uimethods, apimethods).startThreads();
          threadInfo.waitForThreadsToComplete();
      }
      else
      {
        cls = WikiIMDbPage.class;
        obj = cls.newInstance();

        Method m = cls.getDeclaredMethod("extractDataFromImdb", params);
        threadInfo.setNewMethods("movies.pages.DirectorNameStepsImpl", obj, m).startThreads();
      }

      threadInfo.waitForThreadsToComplete();

    }

    @Then("^the director names should match$")
    public void theDirectorNamesShouldMatch() throws Exception {

        threadInfo.quitAllDrivers();

        for (int i = 0; i < threadInfo.getDo().size(); i++) {
            System.out.println("Movie : " + threadInfo.getDo().get(i).moviename);
            System.out.println("Wiki url : " + threadInfo.getDo().get(i).wikirurls);
            System.out.println("IMDB url : " + threadInfo.getDo().get(i).imdburl);

            int imdbDirectorNamesSize = threadInfo.getDo().get(i).imdbdirectornames.length;
            int wikiDirectorNamesSize = threadInfo.getDo().get(i).wikidirectornames.length;
            int expectedMatch = (imdbDirectorNamesSize > wikiDirectorNamesSize) ? imdbDirectorNamesSize : wikiDirectorNamesSize;
            int count = 0;
            boolean found = false;
            ArrayList<String> notfound = new ArrayList<>();
            for (int j = 0; j < wikiDirectorNamesSize; j++) {
                for (int k = 0; k < imdbDirectorNamesSize; k++) {
                    System.out.println("Actual: "+threadInfo.getDo().get(i).imdbdirectornames[k]);
                    System.out.println("Expected: "+threadInfo.getDo().get(i).wikidirectornames[j]);
                    if (threadInfo.getDo().get(i).imdbdirectornames[k].equalsIgnoreCase(threadInfo.getDo().get(i).wikidirectornames[j]))
                    {
                        found = true;
                        count++;
                        break;
                    }
                }
                if(!found)
                {
                    notfound.add(threadInfo.getDo().get(i).wikidirectornames[j]);
                    System.out.println("Could not find " + threadInfo.getDo().get(i).wikidirectornames[j]);
                }
            }

            threadInfo.getDo().get(i).notFound = notfound;
//            Assert.assertEquals(expectedMatch,count);
            System.out.println("Expected Match Count: "+expectedMatch );
            System.out.println("Actual Match Count: "+ count );
        }
        new JSONObjects().createJSON();
    }
}
