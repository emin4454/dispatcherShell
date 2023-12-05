import java.util.LinkedList;
import java.util.Queue;

public abstract class Scheduler {
    protected final Queue<ExecutableProcess> readyQueue = new LinkedList<>(); // Prosesler icin hazir kuyrugu
    protected int priorityLevel;
    protected Device device;
    abstract void executeOneIteration();

    Scheduler(Device device){
        this.device = device;
    }
    public void addToQueue(ExecutableProcess process) { //Hazir kuyruguna proses ekleme
        readyQueue.add(process);
    }

    protected void printSchedulerInfo() {
        String sch = priorityLevel == 0 ? "Gercek Zamanli (FCFS) " : priorityLevel + "(. Seviye Geri Beslemeli(Round Robin)";
        System.out.println("Executelanan Prosesin Gorevlendirici Duzeyi: " + sch);
        System.out.println(device.getAvaivableMemRR());
    }
}
