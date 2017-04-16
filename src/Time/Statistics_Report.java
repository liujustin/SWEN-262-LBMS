package Time;

import Books.Book;
import Books.LBMS_BookKeeper;
import Client.Visitor.LBMS_VisitorKeeper;
import Client.Visitor.Visitor;
import Time.LBMS_StatisticsKeeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//FILE:: Statistics_Report.java
//AUTHOR::Adam Nowak
//DATE::Apr.15.2017

public class Statistics_Report {
    public static void generateReport(int days){

        //data regarding books
        LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
        ArrayList<Book> books = new ArrayList<Book>(bookKeeper.getPurchasedBooks().keySet());
        ArrayList<Integer> bookcopies = new ArrayList<Integer>(bookKeeper.getPurchasedBooks().values());
        int numberOfBooksTotal = 0; //books in library
        int numberOfBooks = 0; //books purchased

        //data regarding visitors
        LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
        ArrayList<Visitor> registeredVisitors = new ArrayList<Visitor>(visitorKeeper.getVisitorRegistry().values());
        int outstandingfines = 0;
        double numberofvisitors = 0;

        //data regarding time
        LBMS_StatisticsKeeper statisticsKeeper = LBMS_StatisticsKeeper.getInstance();
        String time = statisticsKeeper.Get_Time();

        //Output string
        String reportString = "report," + time + "," + "\n";

        // get total number of books
        for(int copies : bookcopies) {
            numberOfBooksTotal += copies;
        }
        reportString += "Number of books: " + numberOfBooksTotal + "\n";

        //get total number of visitors
        numberofvisitors = registeredVisitors.size();
        reportString += "Number of Visitors: " + numberofvisitors + "\n";

        //Number of Books Purchased
        for(int copies : bookcopies) {
            numberOfBooks += copies;
        }
        reportString += "Number of Books Purchased: " + numberOfBooks + "\n";

        //Number of fines outstanding
        for(Visitor visitor : registeredVisitors ){
            outstandingfines += visitor.getBalance();
        }
        reportString += "Fines Outstanding: " + outstandingfines + "\n";
        System.out.println(reportString);
    }
}
