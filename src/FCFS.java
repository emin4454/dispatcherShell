import java.util.LinkedList;
import java.util.Queue;

public class FCFS extends Scheduler {
    FCFS(Device device, int priorityLevel) { //FCFS constructor
        super(device);
        this.priorityLevel = priorityLevel;
    }

    public void executeOneIteration(Scheduler[] schedulers) {
        ExecutableProcess aboutToExecuteProcess = readyQueue.peek(); //Sıradaki proses alınıyor
        if (aboutToExecuteProcess.executeOneTimeUnit()) {
            readyQueue.poll();// Eger true donerse proses bitmis demektir
            device.releaseResources(aboutToExecuteProcess); //Kaynaklar serbest bırakılıyor
            aboutToExecuteProcess.setProcessStatus("TAMAMLANDI");
        }
        else{
            aboutToExecuteProcess.setProcessStatus("CALISIYOR");
        }
    }
}
