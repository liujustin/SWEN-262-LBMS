package Time;

import Books.Book;
import Books.Book_Operations;
import Client.Visitor.Visitor_Operations;
import Client.Visitor.Visitor;

import java.text.DecimalFormat;
import java.util.ArrayList;

//FILE:: Statistics_Report.java
//AUTHOR::Justin Liu, Adam Nowak
//DATE::Apr.15.2017

public class Statistics_Report {
    public static void generateReport(int days){

        //data regarding books
        Book_Operations bookKeeper = Book_Operations.getInstance();
        ArrayList<Book> books = new ArrayList<Book>(bookKeeper.getPurchasedBooks().keySet());
        ArrayList<Integer> bookcopies = new ArrayList<Integer>(bookKeeper.getPurchasedBooks().values());
        int numberOfBooksTotal = 0; //books in library
        int numberOfBooks = 0; //books purchased

        //data regarding visitors
        Visitor_Operations visitorKeeper = Visitor_Operations.getInstance();
        ArrayList<Visitor> registeredVisitors = new ArrayList<Visitor>(visitorKeeper.getVisitorRegistry().values());
        int outstandingfines = 0;
        double numberofvisitors = 0;

        //data regarding time
        Time_Operations statisticsKeeper = Time_Operations.getInstance();
        String time = statisticsKeeper.Get_Time();

        //Output string
        String reportString = "report," + time + "," + "\n";

        // get total number of books
        for(int copies : bookcopies) {
            numberOfBooksTotal += copies;
        }

        // gets total number of books in the library
        reportString += "Number of Books: " + numberOfBooksTotal + "\n";

        //get total number of visitors
        numberofvisitors = registeredVisitors.size();
        reportString += "Number of Visitors: " + numberofvisitors + "\n";

        //gets average length of visits
        reportString += "Average Length of Visits: " + visitorKeeper.getAvgVisit() + "\n";

        //Number of Books Purchased
        for(int copies : bookcopies) {
            numberOfBooks += copies;
        }

        //gets total number of books purchased
        reportString += "Number of Books Purchased: " + bookKeeper.getBookTotal() + "\n";

        DecimalFormat df = new DecimalFormat("#.00");
        reportString += "Fines Collected: " + df.format(visitorKeeper.getFinesCollected());

        //Number of fines outstanding
        for(Visitor visitor : registeredVisitors ){
            outstandingfines += visitor.getBalance();
        }
        reportString += "Fines Outstanding: " + outstandingfines + "\n";
        System.out.println(reportString);
    }
}
