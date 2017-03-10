//FILE::LBMS_BookKeeper.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LBMS_BookKeeper
{
    private final String bookListURI = "books.txt";
    private HashMap<String, Book> purchasedBooks;
    private HashMap<String, Book> bookRegistry;

    public LBMS_BookKeeper()
    {
        this.purchasedBooks = new HashMap<>();
        this.bookRegistry = new HashMap<>();
    }

    private void getBookList()
    {
        Scanner reader;
        try
        {
            reader = new Scanner(new File(this.bookListURI));

            while(reader.hasNextLine())
            {
                String[] tempLine = reader.nextLine().split("'");
                this.bookRegistry.put(tempLine[0], new Book(tempLine[0],
                                                            tempLine[1],
                                                            tempLine[2],
                                                            tempLine[3],
                                                            false));
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        System.out.println(this.bookRegistry.get());
    }

    public Book buyBook()
    {
        return null;
    }
}