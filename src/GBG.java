public class GBG extends Scheduler {
    GBG(Device device, int priorityLevel) {
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
            aboutToExecuteProcess.setProcessStatus("RUNNING");
            aboutToExecuteProcess.reducePriority();
            schedulers[aboutToExecuteProcess.getPriority()].addToQueue(aboutToExecuteProcess);
        }
    }
}
