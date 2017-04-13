package Client.Visitor;

import java.util.Stack;
import Network.Command;

/**
 * Created by Ryan on 4/12/2017.
 */
public class UndoRedoCaretaker {
    private Stack<Command> UndoStack;
    private Stack<Command> RedoStack;

    private static final UndoRedoCaretaker Caretaker = new UndoRedoCaretaker();

    public UndoRedoCaretaker() {
        this.UndoStack = new Stack<>();
        this.RedoStack = new Stack<>();
    }

    public Stack<Command> getUndoStack() {
        return UndoStack;
    }

    public Stack<Command> getRedoStack() {
        return RedoStack;
    }

    public static UndoRedoCaretaker getCaretaker() {
        return Caretaker;
    }
}
