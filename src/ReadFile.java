import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    private final String fileName;

    public ReadFile(String fileName){
        this.fileName = fileName;
    }

    public void ReadTheFile() throws IOException {
        FileReader fileReader=new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line=bufferedReader.readLine())!=null){
            System.out.println(line);
        }
    }
}
