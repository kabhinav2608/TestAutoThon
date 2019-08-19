package movies.pages;

import com.google.gson.Gson;
import helpers.HttpHelper;
import org.json.simple.parser.JSONParser;
import model.ImdbData;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;

import static movies.steps.DirectorNamesSteps.threadInfo;

public class DirectorNameStepsImpl {

    private static HttpHelper httpHelper = new HttpHelper();

    private static String IMDB_URL="https://www.omdbapi.com/?t=";

    public void extractDataFromImdb(String movie, String url){

        try {
            if(url.startsWith("https://en.")){
                String imdburl = IMDB_URL+movie.replaceAll(" ","+")+"&plot=full&apikey=380de361";
                String response = httpHelper.doGetRequest(imdburl);
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(response);
                String director = (String) jsonObject.get("Director");
                threadInfo.getDo(movie).imdburl = imdburl;
                threadInfo.getDo(movie).imdbdirectornames = director.split(",");
            }
            else {
                String[] directorName = {"Not Available"};
                threadInfo.getDo(movie).imdburl = "IMDb Movie URL Not Found";
                threadInfo.getDo(movie).imdbdirectornames = directorName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void extractDataFromWiki(String movie, String wikiUrl){

        try {
            System.out.println("This started also" + movie);
            StringBuffer stringBuffer = new StringBuffer();
            if(wikiUrl.startsWith("https://en.")){
                Document document = Jsoup.connect(wikiUrl).get();
                Elements table= document.select("table[class=infobox vevent]");
                Elements tr = table.select("tr:nth-child(3)");
                Elements td = tr.select("td");
                threadInfo.getDo(movie).wikidirectornames = td.text().split(",");
            }
            else{
                String[] directorNameResult = {"Not Available"};
                threadInfo.getDo(movie).wikidirectornames = directorNameResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}