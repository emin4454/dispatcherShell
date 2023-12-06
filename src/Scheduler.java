import java.util.LinkedList;
import java.util.Queue;

public abstract class Scheduler {

    protected int priorityLevel;
    protected Device device;
    public abstract void executeOneIteration();

    Scheduler(Device device){
        this.device = device;
    }
    public abstract void addToList(ExecutableProcess process);

    public abstract boolean isListEmpty(ExecutableProcess process);
    protected void printSchedulerInfo() {
        String sch = priorityLevel == 0 ? "Gercek Zamanli (FCFS) " : priorityLevel + "(. Seviye Geri Beslemeli(Round Robin)";
        System.out.println("Executelanan Prosesin Gorevlendirici Duzeyi: " + sch);
        System.out.println("avaivable ram for rr "+device.getAvailableMemRR());
    }
}
