import javax.xml.transform.Templates;

public class FunctionEdit {

    TEditor tEditor;

    public FunctionEdit(TEditor tEditor) {
        this.tEditor = tEditor;
    }

    public void undo() {
        tEditor.um.undo();
    }

    public void redo() {
        tEditor.um.redo();
    }

}
