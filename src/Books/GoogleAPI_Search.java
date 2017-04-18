package Books;//FILE::Books.GoogleAPI_Search.java
//AUTHOR::Kevin.P.Barnett
//DATE::Apr.08.2017

import jdk.nashorn.internal.ir.debug.JSONWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class GoogleAPI_Search
{
    private static String parseJson(String jsonString)
    {
        String finalOutString = "";

        //Initial trim off ends
        jsonString = jsonString.replace("{ \"items\": [", "").replace("     }    ]   }  } ]}", "").replace("     }    ]   }  },", "");

        //Break into manageable bits and continue to remove unneeded information
        for(String s: jsonString.split("\\{"))
        {
            s = s.replace(",    \"industryIdentifiers\": [", "").trim();

            if(!s.contains("ISBN_10") && !s.contains("volumeInfo") && !s.equals("\n"))
            {
                for(String s1: s.split(",     "))
                {
                    if(!s1.contains("ISBN_13"))
                        finalOutString += s1.trim() + "\n";
                }
            }
            //finalOutString = finalOutString.replace("\n", " ");
        }

        String[] tempStringArray = finalOutString.split("\"[^\"]*\":");
        finalOutString = "";

        for(String s: tempStringArray)
            finalOutString += s.replace("[     ", "").replace("    ],", "").replace("     },", "").replaceAll("\",", "").replace("\"", "");

        finalOutString = finalOutString.replace("\n", "    ");

        tempStringArray = finalOutString.split("    ");
        finalOutString = "";

        for(int i=0; i<tempStringArray.length; i+=4)
        {
            String tempString = "";
            for(int bIdx=0; bIdx<1; ++bIdx)
                tempString += tempStringArray[i];

            finalOutString += tempString+"\n";
        }

        return finalOutString;
    }

    public static void main(String[] args) throws IOException
    {
        String url = "https://www.googleapis.com/books/v1/volumes?q=inauthor:David+Richo&maxResults=5&fields=items(volumeInfo/title,volumeInfo/authors,volumeInfo/industryIdentifiers,volumeInfo/publisher)";

        // Create a URL and open a connection
        URL YahooURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection) YahooURL.openConnection();

        // Set the HTTP Request type method to GET (Default: GET)
        con.setRequestMethod("GET");
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        // Created a BufferedReader to read the contents of the request.
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }

        // MAKE SURE TO CLOSE YOUR CONNECTION!
        in.close();

        // response is the contents of the XML
        System.out.println(parseJson(response.toString()));
    }
}