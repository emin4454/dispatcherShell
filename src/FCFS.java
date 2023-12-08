import java.util.LinkedList;
import java.util.Queue;

public class FCFS extends Scheduler {
    FCFS(Device device, int priorityLevel) {
        super(device);
        this.priorityLevel = priorityLevel;
    }

    public void executeOneIteration(Scheduler[] schedulers) {
        ExecutableProcess aboutToExecuteProcess = readyQueue.peek();
        printSchedulerInfo();
        System.out.println("Process 1 saniye calisti");
        System.out.println("Calisan " + aboutToExecuteProcess.toString());
        if (aboutToExecuteProcess.executeOneTimeUnit()) {
            readyQueue.poll();// Eger true donerse proses bitmis demektir
            device.releaseResources(aboutToExecuteProcess);
            System.out.println("Proses bitti");
        }
    }   //BURADA KALDIN
}
