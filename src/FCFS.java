import java.util.LinkedList;
import java.util.Queue;

public class FCFS extends Scheduler {
    FCFS(Device device, int priorityLevel) { //FCFS constructor
        super(device);
        this.priorityLevel = priorityLevel;
    }

    public void executeOneIteration(Scheduler[] schedulers) {
        ExecutableProcess aboutToExecuteProcess = readyQueue.peek(); //Sıradaki proses alınıyor
        printSchedulerInfo();  //Proses bilgileri yazdırılıyor
        System.out.println("Process 1 saniye calisti");
        System.out.println("Calisan " + aboutToExecuteProcess.toString());
        if (aboutToExecuteProcess.executeOneTimeUnit()) {
            readyQueue.poll();// Eger true donerse proses bitmis demektir
            device.releaseResources(aboutToExecuteProcess); //Kaynaklar serbest bırakılıyor
            System.out.println("Proses bitti");
        }
    }
}
