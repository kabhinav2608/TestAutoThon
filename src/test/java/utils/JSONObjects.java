package utils;

import movies.steps.DirectorNamesSteps;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONObjects {

    public void createJSON() throws IOException {
        JSONObject jo = new JSONObject();
        JSONArray data = new JSONArray();

        for(int i = 0; i < DirectorNamesSteps.threadInfo.getDo().size(); i++)
        {
            JSONObject moviesData = new JSONObject();
            Do dos = DirectorNamesSteps.threadInfo.getDo().get(i);

            moviesData.put("movieID", (i+1));
            moviesData.put("movieName", dos.moviename);
            moviesData.put("wikipediaUrl", dos.wikirurls);
            moviesData.put("wikipediaSnapshot", dos.wikiScreenShot);
            moviesData.put("wikipediaDirector", getDirectorsName(dos.wikidirectornames));
            moviesData.put("imdbUrl", dos.imdburl);
            moviesData.put("imdbSnapshot", dos.imdbScreenShot);
            moviesData.put("imdbDirector", getDirectorsName(dos.imdbdirectornames));
            moviesData.put("difference", getNotFound(dos.notFound));
            moviesData.put("threadID", dos.threadID);


            if(dos.isMobile)
            {
                moviesData.put("platformName", "Mobile");
            }
            else if(dos.isHTTP)
            {
                moviesData.put("platformName", "HTTP");
            }
            else if(dos.isWeb)
            {
                moviesData.put("platformName", "Web");
            }

            moviesData.put("startTime",dos.startTime );
            moviesData.put("endTime",dos.endTime );
            if(getNotFound(dos.notFound).equals("NA"))
            {
                moviesData.put("status", "Pass");
            }
            else
            {
                moviesData.put("status", "Fail");
            }
            data.add(moviesData);
        }
        jo.put("Data", data);
        System.out.println(jo.toJSONString());

        String write = "function getJSON() {\n" +
                "\tvar JSON = " + jo.toJSONString() + ";\n" +
                "return JSON;\n" +
                "};";



        FileWriter file = null;
        try {
            File f = new File("src/test/java/report/js/data.js");
            if(!(f.exists() && f.isFile()))
            {
                f.createNewFile();
            }
            file = new FileWriter("src/test/java/report/js/data.js");
            file.write(write);
        } finally {
            file.flush();
            file.close();
        }

    }



    private String getDirectorsName(String arr[])
    {
        String director = "";
        for(int j = 0; j < arr.length; j++)
        {
            if(j == 0)
            {
                director = director + arr[j];
            }
            else
            {
                director = director + "," + arr[j];
            }
        }
        return director;
    }


    private String getNotFound(ArrayList<String> arr)
    {
        String director = "NA";
        for(int j = 0; j < arr.size(); j++)
        {
            if(j == 0)
            {
                director = "Not found ";
                director = director + arr.get(j);
            }
            else if(j == (arr.size() -1))
            {
                director = director + "," + arr.get(j) + " in wikipage";
            }
            else
            {
                director = director + "," + arr.get(j);
            }
        }
        return director;
    }


}