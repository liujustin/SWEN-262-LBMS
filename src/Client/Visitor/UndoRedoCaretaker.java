package Client.Visitor;

import java.util.Stack;

/**
 * Created by Ryan on 4/12/2017.
 */
public class UndoRedoCaretaker {
    private Stack<Object> UndoStack;
    private Stack<Object> RedoStack;

    private static final UndoRedoCaretaker Caretaker = new UndoRedoCaretaker();

    public UndoRedoCaretaker() {
        this.UndoStack = new Stack<>();
        this.RedoStack = new Stack<>();
    }

    public Stack<Object> getUndoStack() {
        return UndoStack;
    }

    public Stack<Object> getRedoStack() {
        return RedoStack;
    }

    public static UndoRedoCaretaker getCaretaker() {
        return Caretaker;
    }
}
