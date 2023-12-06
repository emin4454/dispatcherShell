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
    public boolean isListEmpty(ExecutableProcess process) {
        return readyQueue.isEmpty();
    }

    @Override
    public void executeOneIteration() {
        ExecutableProcess aboutToExecuteProcess = readyQueue.peek();
        System.out.println("1 saniye calisti");
        printSchedulerInfo();
    }   //BURADA KALDIN
}
