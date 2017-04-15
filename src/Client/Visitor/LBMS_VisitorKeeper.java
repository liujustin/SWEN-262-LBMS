package Client.Visitor;//FILE::Client.Visitor.LBMS_VisitorKeeper.java
//AUTHOR::Kevin.P.Barnett, Adam Nowak
//DATE::Mar.04.2017

import Books.Book_Loan;
import Time.LBMS_StatisticsKeeper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LBMS_VisitorKeeper
{
    private static final LBMS_VisitorKeeper visitorKeeper = new LBMS_VisitorKeeper();
    private static HashMap<Long, Visitor> visitorRegistry;
    private static HashMap<Long, Date> activeVisitor;
    private Long newID = 999999999L;

    //================================================================================
    // Visitors
    //================================================================================

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

    public static LBMS_VisitorKeeper getInstance(){
        return visitorKeeper;
    }

    public static HashMap<Long,Date> getActiveVisitors(){ return activeVisitor;}
    /**
     *
     * @return visitor registry
     */
    public static HashMap<Long, Visitor> getVisitorRegistry()
    {
        return visitorRegistry;
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
    public Visitor registerVisitor(String firstName, String lastName, String address, String phoneNumber) throws Exception
    {
        String time = LBMS_StatisticsKeeper.Get_Time();
        Long id = incrementID(); // starts visitor id as 1000000000 and increments by 1 each GUI.timeGUI register visitor is called

        for(Long key: this.visitorRegistry.keySet())
            newID = Long.max(newID, key);

        Visitor temporaryNewVisitor = new Visitor(firstName, lastName, address, 0.0, phoneNumber, newID);

        this.visitorRegistry.put(id, temporaryNewVisitor);

        System.out.println("register," + id + "," + time.substring(0,10));
        return temporaryNewVisitor;
    }
    public Long incrementID(){
        newID++;
        return newID;
    }

    /**
     *
     * @param visitorID
     * @throws Exception
     */
    public String beginVisit(Long visitorID) throws Exception
    {
        String time = LBMS_StatisticsKeeper.Get_Time();
        if(!LBMS_StatisticsKeeper.getIsopen(LBMS_StatisticsKeeper.Get_Time())){
            throw new Exception("Library is currently closed.");
        }
        if(this.visitorRegistry.containsKey(visitorID))
        {
            if(! this.activeVisitor.containsKey(visitorID)) {

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss");

                String currentTime = time.split(",")[1];

                this.activeVisitor.put(visitorID, dateFormat.parse(time));

                return "arrive,"+ visitorID + "," + currentTime;
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
    public void returnBook(Long visitorID, ArrayList<String> ISBNS) throws Exception {
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
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss");
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

    /**
     *
     * @param visitorID
     * @param amount
     * @throws Exception
     */
    public void payFine(Long visitorID, double amount)throws Exception{
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

    /**
     *
     * @param visitorID
     * @return
     * @throws Exception
     */
   public String borrowedBooks(Long visitorID) throws Exception{
       if (!this.visitorRegistry.containsKey(visitorID)) {
           throw new Exception("borrowed,invalid-visitor-id;");
       }
       Visitor visitor = this.visitorRegistry.get(visitorID);
       ArrayList visitorsbooks = visitor.getBorrowed_books();
       String response = "borrowed," + visitorsbooks.size() + "," + "\n";
       for(int i = 0;i < visitorsbooks.size();i++ ){
            Book_Loan book = (Book_Loan) visitorsbooks.get(i);
            response += book.getBook().getBookIsbn() + "," + book.getBook().getBookIsbn()+ "," +
                    book.getBook().getBookName() + "," + book.getStart_date().substring(0,10) + "\n";
       }
       return response;
    }

    //================================================================================
    // Accounts
    //================================================================================

    private static HashMap<String, Account> activeAccounts = new HashMap<>();
    private ArrayList<Account> loggedIn = new ArrayList<>();

    /**
     * This method takes in a username string, a password string, a role (0 for visitor, 1 for employee),
     * and a long visitorID. From there, it will create a new Account using those credentials and add it into
     * the activeAccounts hashmap.
     * @param username
     * @param password
     * @param role
     * @param visitorID
     * @return
     */
    public void createAccount(String username, String password, int role, long visitorID){
        //TODO wherever this method is called, you must check whether or not the username currently exists
        // in the activeAccounts HashMap using the getActiveAccounts. If they pass that, you must check if there is
        // an account with the visitorID. From there, you must check whether it exists in the activeVisitor.
        // It cannot be handled in here.

        Account newAccount = new Account(username,password,role,visitorID);
        System.out.println("create,success");
        activeAccounts.put(newAccount.getUsername(),newAccount);
    }

    /**
     * returns the activeAccounts hashmap.
     * @return
     */
    public HashMap<String,Account> getActiveAccounts(){
        return activeAccounts;
    }
    /**
     * This method is used to verify whether or not a user is registered in the system. If it is registered,
     * it will return a true which means it exists, but if not, it will return false.
     * @param username
     * @param password
     * @return
     */
    public boolean loginAccount(String username, String password){
        boolean result = true;
        for (String key : activeAccounts.keySet() ) {
            if (username.equals(key)){
                if(password.equals(activeAccounts.get(key).getPassword())){
                    loggedIn.add(activeAccounts.get(key));
                }
                result = false;
            }
            else{
                result = false;
            }
        }
        return result;
    }

    /**
     * This method is used to log out a user. Because the specifications say that it will return a success
     * method despite whether or not there exists a user logged in, it does not return anything.
     * @param username
     */
    public void logoutAccount(String username){
        for(Account user: loggedIn) {
            if (username.equals(user.getUsername())) {
                loggedIn.remove(user);
            }
        }
    }

    //================================================================================
    // Clients
    //================================================================================

    private ArrayList<Integer> activeConnections = new ArrayList<>();

    /**
     * This method uses Random.nextInt in order to generate a random number with a minimum of 1
     * and a maximum of 100.
     * @return
     */
    public int generateClient(){
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }

    /**
     * This method utilizes the generateClient method and creates a tempClient. From there
     * it will check if that tempClient is in the activeConnections ArrayList and if it is,
     * then it will recurse until it starts a connection with a client that is not already active.
     * After that, it will add that tempClient into the activeConnections list and
     * return the ID of the client.
     * @return tempClient
     */
    public Integer startConnection(){
        int tempClient = generateClient();
        if(activeConnections.contains(tempClient)) {
            startConnection();
        }
        activeConnections.add(tempClient);
        System.out.format("connect,%d%n;",tempClient);
        return tempClient;
    }

    /**
     * This method takes in a clientID and checks to see if its in the ArrayList. If it is,
     * it will remove the clientID and then print out that it was disconnected. If it does
     * not exist, it will do nothing.
     * @param clientID
     * @return
     */
    public void disconnectConnection(int clientID){
        if(activeConnections.contains(clientID)){
            activeConnections.remove(clientID);
            System.out.format("%d%n,disconnect",clientID);
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

        //Validate that Client.Visitor.Client.Visitor File was Read Correctly//
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
