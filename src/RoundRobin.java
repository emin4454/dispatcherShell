import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobin extends Scheduler {
    protected final List<ExecutableProcess> readyList = new LinkedList<>(); // Prosesler icin hazir kuyrugu
    private int quantumTime = 2; // kuantum suresi
    private int lastExecutedIndex = 0;

    RoundRobin(Device device, int priorityLevel) {
        super(device);
        this.priorityLevel = priorityLevel;
    }

    @Override
    public boolean isListEmpty(ExecutableProcess process) {
        return readyList.isEmpty();
    }

    @Override
    public void addToList(ExecutableProcess process) {
        readyList.add(process);
    }

    @Override
    public void executeOneIteration() {

    }
}
