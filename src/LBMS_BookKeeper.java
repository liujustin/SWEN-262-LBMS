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
    private final String bookListURI = "book.txt";
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
                String[] tempLine = reader.nextLine().split(",\"|\",\\{|},\"|\",");

                Book tempBook = new Book(tempLine[0],
                                         tempLine[1],
                                         tempLine[3],
                                         tempLine[4],
                                        false);

                ArrayList<String> authors = new ArrayList<>();
                String[] tempAuthors = tempLine[2].split(",");

                for(int i=0; i<tempAuthors.length; ++i)
                    authors.add(tempAuthors[i]);

                tempBook.setAuthors(authors);

                this.bookRegistry.put(tempLine[0], tempBook);
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public Book buyBook()
    {
        return null;
    }

    public void borrowBook(Visitor visitor, String bookList) throws Exception
    {
        String[] bookISBN = bookList.split(",");

        if(bookISBN.length > 5 - visitor.getBorrowed_books().size())
            throw new Exception("borrow,book-limit-exceeded;");

        for(int i=0; i<bookISBN.length; ++i)
        {
            if(!this.purchasedBooks.containsKey(bookISBN[i]))
                throw new Exception("");
        }
    }

    public static void main(String[] args)
    {
        new LBMS_BookKeeper().getBookList();
    }
}