//FILE::LBMS_VisitorKeeper.java
//AUTHOR::Kevin.P.Barnett
//DATE::Mar.04.2017

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

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
        if(!LBMS_StatisticsKeeper.getIsopen(time)){
            throw new Exception("Library is currently closed.");
        }
        if(this.visitorRegistry.containsKey(visitorID))
        {
            if(! this.activeVisitor.containsKey(visitorID)) {

                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy,HH:mm:ss");

                System.out.println("arrive,"+ visitorID + "," + time.substring(11,19));

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
            String time = LBMS_StatisticsKeeper.Get_Time();

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
   /* public void borrowedBooks(Visitor visitor){
        ArrayList visitorsbooks = visitor.getBorrowed_books();
        String response;
        response = "borrowed," + visitorsbooks.size() + ",";
        System.out.println(response);
        for(int i = 0;i < visitorsbooks.size();i++ ){
            System.out.println(visitorsbooks);
        }
    }
    */

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