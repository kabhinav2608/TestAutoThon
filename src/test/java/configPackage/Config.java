package configPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    public String getProperty(String keyname) throws IOException {
        workingDir = System.getProperty("user.dir");
        String environmentFile = "/src/test/resources/environment.properties";
        InputStream propertyfileinputstream = new FileInputStream(workingDir+environmentFile);
        System.out.println(workingDir+environmentFile);
        Properties prop = new Properties();
        prop.load(propertyfileinputstream);



        if(prop.containsKey(keyname))
        {
            String p = prop.getProperty(keyname).trim();
            propertyfileinputstream.close();
            return p;
        }
        else
        {
            propertyfileinputstream.close();
            return "key not found";
        }
    }


    public int getMobileThreadCount()
    {
        try {
            int count = Integer.parseInt(getProperty("mobile.thread.count"));
            if(count <= 0)
            {
                count = 1;
            }
            return count;
        } catch (Exception e) {
//            e.printStackTrace();
            return 0;
        }
    }

    public int getHTTPThreadCount()
    {
        try {
            int count = Integer.parseInt(getProperty("API.thread.count"));
            if(count <= 0)
            {
                count = 0;
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}
