//FILE::LBMS_VisitorKeeper.java
//AUTHOR::Kevin.P.Barnett
//DATE::Mar.04.2017

import java.util.HashMap;

public class LBMS_VisitorKeeper
{
    private HashMap<Integer, Visitor> visitorRegistry;

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
}