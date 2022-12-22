import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import java.awt.Font;

public class TEditor implements ActionListener {

    JFrame window;
    // textArea
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn = false;
    // topMenu
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat;
    // fileMenuItem
    JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemExit;
    // editMenuItem
    JMenuItem itemUndo, itemRedo;
    // formatMenuItem
    JMenuItem itemWrap, itemFontArial, itemFontTNR, itemFontSize8, itemFontSize12, itemFontSize16, itemFontSize20,
            itemFontSize24, itemFontSize28;
    JMenu menuFont, menuSize;

    FunctionFile file = new FunctionFile(this);
    FunctionFormat format = new FunctionFormat(this);
    FunctionEdit edit = new FunctionEdit(this);
    UndoManager um = new UndoManager();

    public static void main(String args[]) {

        new TEditor();

    }

    public TEditor() {
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createFormatMenu();
        createEditMenu();
        format.selectedFont = "Arial";
        format.createFont(12);
        format.wordwrap();
        window.setVisible(true);
    }

    public void createWindow() {
        window = new JFrame("Notepad");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTextArea() {
        textArea = new JTextArea();
        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    public void undoableEditHappened(UndoableEditEvent e) {
                        um.addEdit(e.getEdit());
                    }
                });
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        window.add(scrollPane);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);
        menuFile = new JMenu("File");
        menuBar.add(menuFile);
        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);
        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);
    }

    public void createFileMenu() {
        itemNew = new JMenuItem("New");
        itemNew.addActionListener(this);
        itemNew.setActionCommand("New");
        menuFile.add(itemNew);

        itemOpen = new JMenuItem("Open");
        itemOpen.addActionListener(this);
        itemOpen.setActionCommand("Open");
        menuFile.add(itemOpen);

        itemSave = new JMenuItem("Save");
        itemSave.addActionListener(this);
        itemSave.setActionCommand("Save");
        menuFile.add(itemSave);

        itemSaveAs = new JMenuItem("Save As");
        itemSaveAs.addActionListener(this);
        itemSaveAs.setActionCommand("SaveAs");
        menuFile.add(itemSaveAs);

        itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(this);
        itemExit.setActionCommand("Exit");
        menuFile.add(itemExit);
    }

    public void createEditMenu() {
        itemUndo = new JMenuItem("Undo");
        itemUndo.addActionListener(this);
        itemUndo.setActionCommand("Undo");
        menuEdit.add(itemUndo);

        itemRedo = new JMenuItem("Redo");
        itemRedo.addActionListener(this);
        itemRedo.setActionCommand("Redo");
        menuEdit.add(itemRedo);
    }

    public void createFormatMenu() {
        itemWrap = new JMenuItem("Word Wrap : Off");
        itemWrap.addActionListener(this);
        itemWrap.setActionCommand("WordWrap");
        menuFormat.add(itemWrap);

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        itemFontArial = new JMenuItem("Arial");
        itemFontArial.addActionListener(this);
        itemFontArial.setActionCommand("Arial");
        menuFont.add(itemFontArial);

        itemFontTNR = new JMenuItem("Times New Roman");
        itemFontTNR.addActionListener(this);
        itemFontTNR.setActionCommand("TNR");
        menuFont.add(itemFontTNR);

        menuSize = new JMenu("Font Size");
        menuFormat.add(menuSize);

        itemFontSize8 = new JMenuItem("8");
        itemFontSize8.addActionListener(this);
        itemFontSize8.setActionCommand("size8");
        menuSize.add(itemFontSize8);

        itemFontSize12 = new JMenuItem("12");
        itemFontSize12.addActionListener(this);
        itemFontSize12.setActionCommand("size12");
        menuSize.add(itemFontSize12);

        itemFontSize16 = new JMenuItem("16");
        itemFontSize16.addActionListener(this);
        itemFontSize16.setActionCommand("size16");
        menuSize.add(itemFontSize16);

        itemFontSize20 = new JMenuItem("20");
        itemFontSize20.addActionListener(this);
        itemFontSize20.setActionCommand("size20");
        menuSize.add(itemFontSize20);

        itemFontSize24 = new JMenuItem("24");
        itemFontSize24.addActionListener(this);
        itemFontSize24.setActionCommand("size24");
        menuSize.add(itemFontSize24);

        itemFontSize28 = new JMenuItem("28");
        itemFontSize28.addActionListener(this);
        itemFontSize28.setActionCommand("size28");
        menuSize.add(itemFontSize28);
    }

    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        switch (command) {
            case "New":
                file.newFile();
                break;
            case "Open":
                file.open();
                break;
            case "Save":
                file.save();
                break;
            case "SaveAs":
                file.saveAs();
                break;
            case "Exit":
                file.exit();
                break;
            case "Undo":
                edit.undo();
                break;
            case "Redo":
                edit.redo();
                break;
            case "WordWrap":
                format.wordwrap();
                break;
            case "Arial":
                format.setFont(command);
                break;
            case "TNR":
                format.setFont(command);
                break;
            case "size8":
                format.createFont(8);
                break;
            case "size12":
                format.createFont(12);
                break;
            case "size16":
                format.createFont(16);
                break;
            case "size20":
                format.createFont(20);
                break;
            case "size24":
                format.createFont(24);
                break;
            case "size28":
                format.createFont(28);
                break;
        }

    }

}