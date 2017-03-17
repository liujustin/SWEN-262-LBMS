//FILE::LBMS_VisitorKeeper.java
//AUTHOR::Kevin.P.Barnett
//DATE::Mar.04.2017

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class LBMS_VisitorKeeper
{
    private HashMap<Integer, Visitor> visitorRegistry;
    private HashMap<Integer, Date> activeVisitor;

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
                String[] visitor = loadVisitorReg.nextLine().split(",");
                Visitor tempVisitor = new Visitor(visitor[0], visitor[1], visitor[2], Double.parseDouble(visitor[3]), visitor[4], Integer.parseInt(visitor[5]));

                this.visitorRegistry.put(Integer.parseInt(visitor[5]), tempVisitor);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public Visitor registerVisitor(String firstName, String lastName, String address, String phoneNumber)
    {
        Integer newID = 999999999; //Start with an id of 1000000000 so the unique id is at least 10 digits

        for(Integer key: this.visitorRegistry.keySet())
            newID = Math.max(newID, key);

        newID += 1;

        Visitor temporaryNewVisitor = new Visitor(firstName, lastName, address, 0.0, phoneNumber, newID);

        this.visitorRegistry.put(newID, temporaryNewVisitor);

        return temporaryNewVisitor;
    }

    public void beginVisit(Integer visitorID) throws Exception
    {
        if(this.visitorRegistry.containsKey(visitorID))
        {
            if(! this.activeVisitor.containsKey(visitorID))
                this.activeVisitor.put(visitorID, new Date());
            else
                throw new Exception("arrive,duplicate;");
        }
        else
            throw new Exception("arrive,invalid-id;");
    }

    public void endVisit(Integer visitorID) throws Exception
    {
        if(this.activeVisitor.containsKey(visitorID))
           this.activeVisitor.remove(visitorID);
        else
            throw new Exception("depart,invalid-id;");
    }

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
}