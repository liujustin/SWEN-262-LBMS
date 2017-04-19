package Client.Visitor;

import java.util.Stack;
import Network.Command;

//FILE::UndoRedoCaretaker.java
//AUTHOR::Ryan Connors
//DATE::Apr.12.2017
public class UndoRedoCaretaker {
    private Stack<Memento> UndoStack;
    private Stack<Memento> RedoStack;

    //This Class contains the stacks which are used in the Undo/Redo subsystem


    private static final UndoRedoCaretaker Caretaker = new UndoRedoCaretaker();

    //Undo and Redo Stack objects
    public UndoRedoCaretaker() {
        this.UndoStack = new Stack<>();
        this.RedoStack = new Stack<>();
    }

    //Getters for caretaker class

    /**
     *
     * @return UndoStack
     */
    public Stack<Memento> getUndoStack() {
        return UndoStack;
    }

    /**
     *
     * @return RedoStack
     */

    public Stack<Memento> getRedoStack() { return RedoStack; }

    /**
     *
     * @return CareTaker
     */

    public static UndoRedoCaretaker getCaretaker() {
        return Caretaker;
    }
}
