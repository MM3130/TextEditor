import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;

public class FunctionFile {

    TEditor teditor;
    String filename, fileAddress;

    public FunctionFile(TEditor teditor){
        this.teditor = teditor;
    }

    public void newFile(){
        teditor.textArea.setText("");
        teditor.window.setTitle("New");
        filename = null;
        fileAddress = null;
    }

    public void open(){
        FileDialog fd = new FileDialog(teditor.window,"Open", FileDialog.LOAD);
        fd.setVisible(true);

        if(fd.getFile()!=null){
            filename = fd.getFile();
            fileAddress = fd.getDirectory();
            teditor.window.setTitle(filename);
        }

        try{
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + filename));

            teditor.textArea.setText("");
            String line = null;
            while((line = br.readLine()) != null){
                teditor.textArea.append(line + "\n");
            }
            br.close();

        }catch(Exception e){

            System.out.println("File Not Opened!");

        }
    }

    public void save(){

        if(filename == null){
            saveAs();
        }
        else{
            try{
                FileWriter fw = new FileWriter(fileAddress + filename);
                fw.write(teditor.textArea.getText());
                teditor.window.setTitle(filename);
                fw.close();
            }catch(Exception e){
                System.out.println("Someting Worng!");
            }
        }


    }

    public void saveAs(){
        FileDialog fd = new FileDialog(teditor.window,"Save",FileDialog.SAVE);
        fd.setVisible(true);

        if(fd.getFile() != null){
            filename = fd.getFile();
            fileAddress = fd.getDirectory();
            teditor.window.setTitle(filename);
        }

        try{
            FileWriter fw = new FileWriter(fileAddress + filename);
            fw.write(teditor.textArea.getText());
            fw.close();
        }catch(Exception e){
            System.out.println("Someting Worng!");
        }
    }

    public void exit(){

        System.exit(0);

    }


}
