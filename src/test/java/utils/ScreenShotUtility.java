package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotUtility {

    public static void takeScreenShot(WebDriver driver, String fileName) throws IOException {
        String path;

        File directory = new File("src/test/java/report");
        if (!directory.exists()) {
            directory.mkdir();
        }

        path = "src/test/java/report/" + fileName;

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(path));
    }

}