import java.util.LinkedList;


public class ExecutableProcess {

    private static int assignedProcessCounter = 0; //Proses Id için proses sayıcı
    private int processid;  //Atanan proses Id
    private int arriveTime; //Prosesin gelme zamani
    private int priority; //Prosesin onceligi
    private int burstTime; //Prosesin ne kadar zaman aldigi
    private int requiredMem; //Prosesin ihtiyac duydugu memory
    private int requiredPrinter;// Prosesin ihtiyac duydugu yazici sayisi
    private int requiredScanner;// Prosesin ihtiyac duydugu tarayici sayisi
    private int requiredRouter;// Prosesin ihtiyac duydugu Modem sayisi
    private int requiredCDROM;//Prosesin ihtiyac duydugu cd/dvd sayisi
    private int aliveTime;   //Prosesin yaşadığı süre
    private String processStatus = "";

    public String colorStringArray[] = {"", ""};

    public int getAliveTime() {
        return aliveTime;
    }

    private String processString = "";



    public ExecutableProcess(int arriveTime, int priority, int burstTime, int requiredMem, int requiredPrinter, int requiredScanner, int requiredRouter, int requiredCD) {
        this.arriveTime = arriveTime;
        this.priority = priority;
        this.burstTime = burstTime;
        this.requiredMem = requiredMem;
        this.requiredPrinter = requiredPrinter;
        this.requiredScanner = requiredScanner;
        this.requiredRouter = requiredRouter;
        this.requiredCDROM = requiredCD;
        setColorStringArray();
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public void setProcessString(String processString) {
        this.processString = processString;
    }

    public void assignProcess(Device device) {
        processid = assignedProcessCounter;
        assignedProcessCounter++;
        device.addToArrivedProcessList(this);
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public String getProcessString() {
        return processString;
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

    public boolean increaseAliveTime() {
        aliveTime++;
        return aliveTime >= 20;
    }

    public boolean executeOneTimeUnit() {
        burstTime--;
        return burstTime <= 0;
    }

    public void reducePriority() {
        priority++;
    }



    @Override
    public String toString() {
        return
                processid +
                        " " + arriveTime +
                        " " + priority +
                        " " + burstTime +
                        " " + requiredMem +
                        " " + requiredPrinter +
                        " " + requiredScanner +
                        " " + requiredRouter +
                        " " + requiredCDROM +
                        " " + aliveTime;

    }

    private void setColorStringArray()
    {
        colorStringArray =  Color.setTextColor();
    }
}
