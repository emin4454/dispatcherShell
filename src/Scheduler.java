import java.util.LinkedList;
import java.util.Queue;

public abstract class Scheduler {

    protected final Queue<ExecutableProcess> readyQueue = new LinkedList<>(); // Prosesler icin hazir kuyrugu
    protected int priorityLevel;
    protected Device device;
    public abstract void executeOneIteration(Scheduler[] schedulers);

    Scheduler(Device device){
        this.device = device;
    }
    public void addToQueue(ExecutableProcess process) {
        readyQueue.add(process);
    }

    public boolean isListEmpty() {
        return readyQueue.isEmpty();
    }
    protected void printSchedulerInfo() {
        String sch = priorityLevel == 0 ? "Gercek Zamanli (FCFS) " : priorityLevel + ". Seviye Geri Beslemeli(Round Robin)";
        System.out.println("Executelanan Prosesin Gorevlendirici Duzeyi: " + sch);
    }
}
