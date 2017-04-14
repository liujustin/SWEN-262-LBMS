package Client.Visitor;

import java.util.Stack;
import Network.Command;

/**
 * Created by Ryan on 4/12/2017.
 */
public class UndoRedoCaretaker {
    private Stack<Memento> UndoStack;
    private Stack<Memento> RedoStack;

    private static final UndoRedoCaretaker Caretaker = new UndoRedoCaretaker();

    public UndoRedoCaretaker() {
        this.UndoStack = new Stack<>();
        this.RedoStack = new Stack<>();
    }

    public Stack<Memento> getUndoStack() {
        return UndoStack;
    }

    public Stack<Memento> getRedoStack() {
        return RedoStack;
    }

    public static UndoRedoCaretaker getCaretaker() {
        return Caretaker;
    }
}