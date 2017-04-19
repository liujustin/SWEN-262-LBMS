package Books;//FILE::Books.GoogleAPI_Search.java
//AUTHOR::Kevin.P.Barnett
//DATE::Apr.08.2017

import jdk.nashorn.internal.ir.debug.JSONWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GoogleAPI_Search
{
    private ArrayList<Book> parseJson(String jsonString)
    {
        ArrayList<Book> searchedBooks = new ArrayList<>();
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

        for(int i=1; i<tempStringArray.length; i+=5)
        {
            String[] tempString = new String[5];

            for(int bIdx=0; bIdx<5; ++bIdx)
                tempString[bIdx] = tempStringArray[i+bIdx].trim();

            Book tempBook = new Book(tempString[4], tempString[0], tempString[2], tempString[3]);

            tempBook.setAuthors(new ArrayList<>(Arrays.asList(tempString[1])));

            searchedBooks.add(tempBook);
        }

        return searchedBooks;
    }

    private String formURL(String title, ArrayList<String> authors, String isbn, String publisher)
    {
        String formedURL = "";

        if(!title.equals("*"))
            formedURL += "intitle:" + title.replace(" ", "+");

        if(authors.size() > 0 && !authors.get(0).equals("*"))
        {
            if(formedURL.length()>0)
                formedURL += "+";
            formedURL += "inauthor:" + authors.get(0).replace(" ", "+");
        }

        if(isbn != null && !isbn.equals("*"))
        {
            if(formedURL.length()>0)
                formedURL += "+";
            formedURL += "isbn:" + isbn;
        }

        if(publisher != null && !publisher.equals("*"))
        {
            if(formedURL.length()>0)
                formedURL += "+";
            formedURL += "inpublisher:" + publisher.replace(" ", "+");
        }

        return "";
    }

    public ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, String publisher)
    {
        try
        {
            String urlSearchParameters = this.formURL(title, authors, isbn, publisher);
            String url = "https://www.googleapis.com/books/v1/volumes?q="+urlSearchParameters+"&maxResults=5&fields=items(volumeInfo/title,volumeInfo/authors,volumeInfo/industryIdentifiers,volumeInfo/publisher,volumeInfo/publishedDate)";

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
            return parseJson(response.toString());
        }
        catch(IOException e)
        {
            System.out.println("Failed to Connect to Google Books");
        }
        return null;
    }
}