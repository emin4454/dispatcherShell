public class ExecutableProcess {

    private static int assignedProcessCounter = 0;
    private int processid;
    private int arriveTime; //Prosesin gelme zamani
    private int priority; //Prosesin onceligi
    private int burstTime; //Prosesin ne kadar zaman aldigi
    private int requiredMem; //Prosesin ihtiyac duydugu memory
    private int requiredPrinter;// Prosesin ihtiyac duydugu yazici sayisi
    private int requiredScanner;// Prosesin ihtiyac duydugu tarayici sayisi
    private int requiredRouter;// Prosesin ihtiyac duydugu Modem sayisi
    private int requiredCDROM;//Prosesin ihtiyac duydugu cd/dvd sayisi
    private int aliveTime;

    public ExecutableProcess(int arriveTime, int priority, int burstTime, int requiredMem, int requiredPrinter, int requiredScanner, int requiredRouter, int requiredCD) {
        this.arriveTime = arriveTime;
        this.priority = priority;
        this.burstTime = burstTime;
        this.requiredMem = requiredMem;
        this.requiredPrinter = requiredPrinter;
        this.requiredScanner = requiredScanner;
        this.requiredRouter = requiredRouter;
        this.requiredCDROM = requiredCD;
    }
    public void assignProcess(){
        processid = assignedProcessCounter;
        assignedProcessCounter++;
    }
    public int getPriority() {
        return priority;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getRequiredMem() {
        return requiredMem;
    }

    public int getRequiredPrinter() {
        return requiredPrinter;
    }

    public int getRequiredScanner() {
        return requiredScanner;
    }

    public int getRequiredRouter() {
        return requiredRouter;
    }

    public int getRequiredCDROM() {
        return requiredCDROM;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getProcessID() {
        return processid;
    }

    public boolean executeOneTimeUnit() {
        this.burstTime--;
        return this.burstTime == 0;
    }

    @Override
    public String toString() {
        return "Prosesin Bilgileri  " +
                "ID=" + processid +
                ", Gelis Zamani=" + arriveTime +
                ", Oncelik=" + priority +
                ", Islem Suresi=" + burstTime +
                ", Gerekli Hafiza=" + requiredMem +
                ", Gerekli Yazici=" + requiredPrinter +
                ", Gerekli Tarayici=" + requiredScanner +
                ", Gerekli Modem=" + requiredRouter +
                ", Gerekli CD/DVD=" + requiredCDROM;
    }
}
