//FILE::LBMS_VisitorKeeper.java
//AUTHOR::Kevin.P.Barnett
//DATE::Mar.04.2017

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LBMS_VisitorKeeper
{
    private HashMap<Long, Visitor> visitorRegistry;
    private HashMap<Long, Date> activeVisitor;

    public LBMS_VisitorKeeper()
    {
        //This stores all visitors that have ever been registered//
        this.visitorRegistry = new HashMap<>();

        //This stores the currently visiting visitors//
        this.activeVisitor = new HashMap<>();

        try
        {
            Scanner loadVisitorReg = new Scanner(new File("visitor.log"));

            while(loadVisitorReg.hasNextLine())
            {
                String[] visitor = loadVisitorReg.nextLine().split(":");
                Visitor tempVisitor = new Visitor(visitor[0], visitor[1], visitor[2], Double.parseDouble(visitor[3]), visitor[4], Long.parseLong(visitor[5]));

                this.visitorRegistry.put(Long.parseLong(visitor[5]), tempVisitor);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return visitor registry
     */
    public HashMap<Long, Visitor> getVisitorRegistry()
    {
        return this.visitorRegistry;
    }

    /**
     *
     * @return active visitor
     */
    public HashMap<Long, Date> getActiveVisitor()
    {
        return this.activeVisitor;
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @return registers a new visitor to the system
     */
    public Visitor registerVisitor(String firstName, String lastName, String address, String phoneNumber)
    {
        String time = LBMS_StatisticsKeeper.Get_Time();

        Long newID = 999999999L; //Start with an id of 1000000000 so the unique id is at least 10 digits

        //for(Long key: this.visitorRegistry.keySet())
        //    newID = Math.max(newID, key);

        newID += 1;

        Visitor temporaryNewVisitor = new Visitor(firstName, lastName, address, 0.0, phoneNumber, newID);

        this.visitorRegistry.put(newID, temporaryNewVisitor);

        System.out.println("register," + newID + "," + time.substring(0,10));

        return temporaryNewVisitor;
    }

    /**
     *
     * @param visitorID
     * @throws Exception
     */
    public void beginVisit(Long visitorID) throws Exception
    {
        String time = LBMS_StatisticsKeeper.Get_Time();
        if(!LBMS_StatisticsKeeper.check_Time()){
            throw new Exception("Library is currently closed.");
        }
        if(this.visitorRegistry.containsKey(visitorID))
        {
            if(! this.activeVisitor.containsKey(visitorID)) {

                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy,HH:mm:ss");

                String currentTime = time.split(",")[1];

                System.out.println("arrive,"+ visitorID + "," + currentTime);

                this.activeVisitor.put(visitorID, dateFormat.parse(time));
            }
            else
                throw new Exception("arrive,duplicate;");
        }
        else
            throw new Exception("arrive,invalid-id;");
    }


    /**
     *
     * @param visitorID
     * @throws Exception
     */
    public void endVisit(Long visitorID) throws Exception
    {
        if(this.activeVisitor.containsKey(visitorID)) {
            this.activeVisitor.remove(visitorID);
            String time = LBMS_StatisticsKeeper.Get_Time().split(",")[1];
            System.out.println("depart," + visitorID + "," + time);
        }
        else
            throw new Exception("depart,invalid-id;");
    }

    /**
     * this function shuts down the system
     *
     */
    public void shutdown()
    {
        try
        {
            PrintStream saveState = new PrintStream(new FileOutputStream(new File("visitor.log")));
            saveState.flush();

            for(Visitor v:this.visitorRegistry.values())
                saveState.println(v.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param visitorID
     * @param ISBNS
     * @throws Exception
     */
    public void returnBook(long visitorID, ArrayList<String> ISBNS) throws Exception {
        String errormessage1 = "return,invalid-book-id";
        String errormessage2a = "return,overdue,";
        String errormessage2b = "";
        double visitor_balance = 0;
        if (!this.visitorRegistry.containsKey(visitorID)) {
            throw new Exception("return,invalid-visitor-id;");
        }
        Visitor visitor = this.visitorRegistry.get(visitorID);
        for (int i = 0; i < ISBNS.size(); i++) {
            for (int j = 0; j < visitor.getBorrowed_books().size(); j++) {
                double book_balance = 0;
                if (ISBNS.get(i).equals(visitor.getBorrowed_books().get(j))) {
                    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy,HH:mm:ss");
                    Date time = dateFormat.parse(LBMS_StatisticsKeeper.Get_Time());
                    if (time.after(dateFormat.parse(visitor.getBorrowed_books().get(j).getDue_date()))) { // check if due date is before current date
                        book_balance += 10;
                        visitor.getBorrowed_books().get(j).setBalance(book_balance);
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(time);
                    calendar.add(Calendar.DAY_OF_YEAR, 7);
                    Date futureDate = calendar.getTime();
                    while (futureDate.after(dateFormat.parse(visitor.getBorrowed_books().get(j).getDue_date()))) {// if the date is a week past the due date  (current date + week is after the due date)
                        if (book_balance == 30) {
                            book_balance = 30;
                            visitor.getBorrowed_books().get(j).setBalance(book_balance);
                            break;
                        }
                        book_balance += 2;
                        visitor.getBorrowed_books().get(j).setBalance(book_balance);
                        calendar.add(Calendar.DAY_OF_YEAR, 7);
                        futureDate = calendar.getTime();
                    }
                    if (visitor.getBorrowed_books().get(j).getBalance() > 0) {
                        errormessage2b += ISBNS.get(i) + ",";
                    }
                    visitor_balance += book_balance;
                    break;
                }
                if (!ISBNS.get(i).equals(visitor.getBorrowed_books().get(j)) && j == visitor.getBorrowed_books().size()) {
                    errormessage1 += visitorID + ",";
                }
            }
        }
        if (!errormessage1.endsWith("return,invalid-book-id")) {
            errormessage1 = errormessage2b.substring(0, errormessage2b.length() - 1);
            errormessage1 += ";";
            throw new Exception(errormessage1);
        }
        visitor.setBalance(visitor_balance);
        for (int i = 0; i < ISBNS.size(); i++) {
            for (int j = 0; j < visitor.getBorrowed_books().size(); j++) {
                if (ISBNS.get(i).equals(visitor.getBorrowed_books().get(j))) {
                    visitor.getBorrowed_books().remove(j);
                    j--;
                }
            }
        }
        if (visitor_balance > 0) {
            errormessage2b = errormessage2b.substring(0, errormessage2b.length() - 1);
            errormessage2b += ";";
            throw new Exception(errormessage2a + "$" + visitor_balance + "," + errormessage2b);
        }
        System.out.println("return,success");
    }

    public void payFine(long visitorID, double amount)throws Exception{
        if (!this.visitorRegistry.containsKey(visitorID)) {
            throw new Exception("pay,invalid-visitor-id;");
        }
        Visitor visitor = this.visitorRegistry.get(visitorID);
        if ( amount < 0 || amount > visitor.getBalance()){
            throw new Exception("pay,invalid-amount," + amount + "," + visitor.getBalance() + ";");
        }
        double new_visitor_balance = visitor.getBalance() - amount;
        visitor.setBalance(new_visitor_balance);
        System.out.println("pay,success," + visitor.getBalance() + ";");
    }
   public void borrowedBooks(long visitorID) throws Exception{
       if (!this.visitorRegistry.containsKey(visitorID)) {
           throw new Exception("borrowed,invalid-visitor-id;");
       }
       Visitor visitor = this.visitorRegistry.get(visitorID);
        ArrayList visitorsbooks = visitor.getBorrowed_books();
        String response = "borrowed," + visitorsbooks.size() + ",";
        System.out.println(response);
        for(int i = 0;i < visitorsbooks.size();i++ ){
            Book_Loan book = (Book_Loan) visitorsbooks.get(i);
            System.out.println(book.getBook().getBookID() + "," + book.getBook().getBookIsbn()+ "," +
                    book.getBook().getBookName() + "," + book.getStart_date().substring(0,10));
        }
    }

    /**
     *
     * @param args
     * main function used for testing purposes
     */

    public static void main(String[] args)
    {
        LBMS_VisitorKeeper mainTest = new LBMS_VisitorKeeper();

        //Validate that Visitor File was Read Correctly//
        System.out.println(mainTest.getVisitorRegistry().get(2365153268L));
        System.out.println(mainTest.getVisitorRegistry().get(4561235867L));

        //Validate Registering User//
        try {
            System.out.println(mainTest.registerVisitor("Hubert", "Humphrey", "200 East Landia Street", "3194912816"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Validate Begining Visit//
        try{mainTest.beginVisit(4561235867L);}
        catch(Exception e){e.printStackTrace();}
        System.out.println(mainTest.getActiveVisitor().size());

        //Validate End Visit//
        try{mainTest.endVisit(4561235867L);}
        catch(Exception e){e.printStackTrace();}
        System.out.println(mainTest.getActiveVisitor().size());

        //Validate Shutting Down//
        mainTest.shutdown();
    }
}