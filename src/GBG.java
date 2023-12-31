public class GBG extends Scheduler {
    GBG(Device device, int priorityLevel) {
        super(device);
        this.priorityLevel = priorityLevel;
    }

    @Override
    public void executeOneIteration(Scheduler[] schedulers) {
        ExecutableProcess aboutToExecuteProcess = readyQueue.poll();
        if (aboutToExecuteProcess.executeOneTimeUnit()) {
            aboutToExecuteProcess.setProcessStatus("TAMAMLANDI");
            device.releaseResources(aboutToExecuteProcess);
        } else {
            aboutToExecuteProcess.setProcessStatus("CALISIYOR");
            aboutToExecuteProcess.reducePriority();
            schedulers[aboutToExecuteProcess.getPriority()].addToQueue(aboutToExecuteProcess);
        }
    }
}
