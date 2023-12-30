public class RoundRobin extends Scheduler {
    RoundRobin(Device device, int priorityLevel) {
        super(device);
        this.priorityLevel = priorityLevel;
    }
    @Override
    public void executeOneIteration(Scheduler[] schedulers) {
        ExecutableProcess aboutToExecuteProcess = readyQueue.poll();
        if (aboutToExecuteProcess.executeOneTimeUnit()) {
            aboutToExecuteProcess.setProcessStatus("COMPLETED");
            device.releaseResources(aboutToExecuteProcess);
        } else {
                readyQueue.add(aboutToExecuteProcess);
                aboutToExecuteProcess.setProcessStatus("RUNNING");
        }
    }
}
