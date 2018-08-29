package ConfigPackage;

import java.io.*;
import java.util.Properties;

public final class Config {

    public static String workingDir;
    public static String runmode=new String();
    private final static Properties environment=new Properties();


    static {

        System.out.println("running static block");
        String workingDir = System.getProperty("user.dir");
        String propFileName = "/serenity.properties";
        String environmentFile = "/src/test/resources/environment.properties";

        try {
            InputStream envproperties = new FileInputStream(workingDir+environmentFile);
            environment.load(envproperties);

            if(environment.getProperty("test.run.mode") != null)
            {
                runmode=environment.getProperty("test.run.mode").toLowerCase();
                System.out.println("Run mode set for automation"+"----"+runmode);
            }
    } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("outside static block");
    }
}
