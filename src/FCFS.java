import java.util.LinkedList;
import java.util.Queue;

public class FCFS extends Scheduler {

    protected final Queue<ExecutableProcess> readyQueue = new LinkedList<>(); // Prosesler icin hazir kuyrugu

    FCFS(Device device, int priorityLevel) {
        super(device);
        this.priorityLevel = priorityLevel;
    }

    @Override
    public void addToList(ExecutableProcess process) {
        readyQueue.add(process);
    }

    @Override
    public boolean isListEmpty() {
        return readyQueue.isEmpty();
    }

    @Override
    public void executeOneIteration() {
        ExecutableProcess aboutToExecuteProcess = readyQueue.peek();
        if(aboutToExecuteProcess.executeOneTimeUnit()){
            readyQueue.poll();// Eger true donerse proses bitmis demektir
            device.releaseResources(aboutToExecuteProcess);
            System.out.println("Proses bitti");
        }
        System.out.println("1 saniye calisti");
        printSchedulerInfo();
    }   //BURADA KALDIN
}
