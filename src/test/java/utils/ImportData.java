package utils;

import au.com.bytecode.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author kumar on 29/08/18
 * @project X-search
 */


public class ImportData {

  public static ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap();

 private static String PATH_OF_FILE = "testData/movie.csv";
  public static ConcurrentHashMap<String, String> generateData(){

   // HashMap<String, String> hashMap = new HashMap();


    try {
      CSVReader reader = new CSVReader(new FileReader(PATH_OF_FILE));
      String[] csvCell;

      //while loop will be executed till the last line In CSV.
      while ((csvCell = reader.readNext()) != null) {

        new Thread(new GoogleSearch(csvCell)).start();

      }
    }
    catch (Exception e){
      e.printStackTrace();
    }

    return hashMap;
  }

  public static int stringContains(String longer, String shorter) {
    int i = 0;
    for (char c : shorter.toCharArray()) {
      if (longer.indexOf(c, i)>= 0) { i++; }
    }
    return i;
  }

  public static void addData(String movieName,String url){
    int count = 0;
    int count1=0;
    String[] words = movieName.split("\\s");
    if(words.length>1)
      for (String str : words) {
        if (url.contains(str))
          count++;
      }
    else
      count1=ImportData.stringContains(url,movieName);

    if ((count >= 2) || (count1>=movieName.toCharArray().length-1))
     hashMap.put(movieName, url);
    else
    hashMap.put(movieName, "No url found");
  }

}

class GoogleSearch implements Runnable {

  String[] csvCell;

  public GoogleSearch(String[] csvCell){
    this.csvCell = csvCell;
  }

  @Override
  public void run() {

    System.out.println("---csvCell[0]-" + csvCell[0]);
    String movieName;
    String temp = csvCell[0].split("\\.")[1];
    if (temp != null || temp.length() != 0)
      movieName = temp.trim();
    else
      movieName = csvCell[0];
    System.out.println("----moviename--" + movieName);
    ChromeOptions chromeOptions = new ChromeOptions();
    //chromeOptions.addArguments("headless");
    WebDriver driver = new ChromeDriver(chromeOptions);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.navigate().to("https://www.google.co.in/");
    driver.findElement(By.id("lst-ib")).sendKeys("movie:" + movieName + " wikipedia page");
    driver.findElement(By.name("btnK")).sendKeys(Keys.ENTER);
    WebElement webElement = driver.findElements(By.xpath("//a[starts-with(@href,'https://en.wikipedia.org/wiki')]")).get(0);
    if (webElement != null) {
      webElement.click();
      System.out.println("MovieName" + movieName + " " + "Title:" + driver.getTitle());
      ImportData.addData(movieName,driver.getTitle());
    }
    else
      ImportData.addData(movieName,"No url found");
    driver.quit();

  }

}

