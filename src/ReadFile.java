import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ReadFile {
    private final String fileName;

    public ReadFile(String fileName){
        this.fileName = fileName;
    }

    public LinkedList<ExecutableProcess> ReadTheFile() throws IOException {
        LinkedList<ExecutableProcess> processList = new LinkedList<>();
        FileReader fileReader=new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        int totalTime= 0;
        while((line=bufferedReader.readLine())!=null){
            line = line.replaceAll("\\s" , "");
            String[] props = line.split(",");
            int[] intProps = new int[props.length];
            for(int i =0 ; i< intProps.length;i++){
                intProps[i] = Integer.valueOf(props[i]);
            }
            totalTime=totalTime+intProps[2];
            ExecutableProcess process = new ExecutableProcess(intProps[0],intProps[1],intProps[2],intProps[3],intProps[4],intProps[5],intProps[6],intProps[7]);
            processList.add(process);
        }
        System.out.println("\u001B[31mBu metin kırmızı renkte yazdırılıyor."+totalTime+"\u001B[0m");
        return processList;
    }
}
