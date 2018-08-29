//package utilities;
//
//import org.apache.commons.lang3.StringUtils;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.support.FindBy;
//import net.serenitybdd.core.pages.WebElementFacade;
//
//import java.io.*;
//
//
//public class PerformanceUtility {
//
//    @FindBy(xpath="//*[@id='perfbook-iframe']")
//    private WebElementFacade iFrame;
//
//    @FindBy(xpath="//*[contains (text(),'Total')]/following-sibling::dd")
//    private WebElementFacade total;
//
//    @FindBy(xpath="//button[@class='perfbook-close']")
//    private WebElementFacade closeButton;
//
//
//
//    public Double getTotalTimeTaken() throws Exception {
//        loadJs();
//
//        getDriver().switchTo().frame(iFrame);
//
//
//
//
//
//        Double timeTaken = getCount(total.getText()) / 1000;
//        closeButton.click();
//        return timeTaken;
//    }
//
//
//    private boolean loadJs() throws Exception {
//        String jQueryLoader = readFile(new File("performanceBookmarklet.js"));
//        ((JavascriptExecutor)  getDriver()).executeScript(jQueryLoader);
//        return true;
//    }
//
//
//
//
//
//    public double getCount(String count) {
//        return Double.parseDouble(StringUtils.getDigits(count));
//    }
//
//
//
//
//
//    public static String readFile(File f) throws IOException {
//        try (InputStream is = new FileInputStream(f)) {
//            return readFile(is);
//        }
//    }
//
//    public static String readFile(InputStream is) throws IOException {
//        StringBuilder result = new StringBuilder();
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        String line = br.readLine();
//        while (line != null) {
//            result.append(line + "\n");
//            line = br.readLine();
//        }
//        return result.toString();
//    }
//
//
//
//
//}
