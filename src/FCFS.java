public class FCFS extends Scheduler {
    FCFS(Device device , int priorityLevel) {
        super(device);
        this.priorityLevel = priorityLevel;
    }

    @Override
    void executeOneIteration() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("1 saniye calisti");
        printSchedulerInfo();
    }
}
