import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ReadFile {
    private final String fileName;
    private int lastArriveTime = 0;
    public ReadFile(String fileName){
        this.fileName = fileName;
    }

    public int getLastArriveTime() {
        return lastArriveTime;
    }

    public LinkedList<ExecutableProcess> ReadTheFile() throws IOException {
        LinkedList<ExecutableProcess> processList = new LinkedList<>();
        FileReader fileReader=new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line=bufferedReader.readLine())!=null){
            line = line.replaceAll("\\s" , "");
            String[] props = line.split(",");
            int[] intProps = new int[props.length];
            for(int i =0 ; i< intProps.length;i++){
                intProps[i] = Integer.valueOf(props[i]);
            }
            lastArriveTime = Math.max(intProps[0], lastArriveTime);
            ExecutableProcess process = new ExecutableProcess(intProps[0],intProps[1],intProps[2],intProps[3],intProps[4],intProps[5],intProps[6],intProps[7]);
            processList.add(process);
        }
        return processList;
    }
}
