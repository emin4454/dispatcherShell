public class RoundRobin extends Scheduler {
    RoundRobin(Device device, int priorityLevel) {
        super(device);
        this.priorityLevel = priorityLevel;
    }
    @Override
    public void executeOneIteration(Scheduler[] schedulers) {
        ExecutableProcess aboutToExecuteProcess = readyQueue.poll();
        printSchedulerInfo();
        System.out.println("Process 1 saniye calisti");
        System.out.println("Calisan " + aboutToExecuteProcess.toString());
        if (aboutToExecuteProcess.executeOneTimeUnit()) {
            System.out.println("Proses bitti");
            device.releaseResources(aboutToExecuteProcess);
        } else {
            if(aboutToExecuteProcess.getPriority()==1 || aboutToExecuteProcess.getPriority()==2) {
                aboutToExecuteProcess.reducePriority();
                schedulers[aboutToExecuteProcess.getPriority()].addToQueue(aboutToExecuteProcess);
                System.out.println("Proses bir düşük öncelikli Geri Beslemeli seviyeye atandı(Yaşlandırıldı)");
            }
            else {
                readyQueue.add(aboutToExecuteProcess);
            }
        }
    }
}
