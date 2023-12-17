import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

public class Readtxt {
    private LinkedList<ExecutableProcess> processList;
    private String fileName="giris.txt";
    FileReader fileReader=new FileReader(fileName);
    String line;
    BufferedReader br= new BufferedReader((fileReader));
    while ((line=br.readLine())!=null)
    {System.out.println(line);}
    br.close;


    public Readtxt() throws FileNotFoundException {
    }
}
