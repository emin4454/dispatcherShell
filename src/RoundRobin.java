public class RoundRobin extends Scheduler {
    private int quantumTime = 2; // kuantum suresi

    RoundRobin(Device device,int priorityLevel) {
        super(device);
        this.priorityLevel = priorityLevel;
    }

    @Override
    void executeOneIteration() {

    }
}
