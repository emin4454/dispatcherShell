import java.util.LinkedList;

public class Device {
    private int availableMemRR = 960;    // Makinenin sahip oldugu bellek miktari
    private int availableMemFCFS = 64;  //Makinenin sahip olduğu FCFS alani
    private int availablePrinter = 2;// Makinenin sahip oldugu yazici sayisi
    private int availableScanner = 1;//  Makinenin sahip oldugu tarayici sayisi
    private int availableRouter = 1;//  Makinenin sahip oldugu Modem sayisi
    private int availableCDROM = 2;// Makinenin sahip oldugu cd/dvd sayisi

    private LinkedList<ExecutableProcess> allArrivedProcessList = new LinkedList<>();
    Device() {

    }
    boolean tryAllocateForProcess(ExecutableProcess process) {
        if (process.getPriority() == 0 ) {
            if(process.getRequiredMem() <= availableMemFCFS && process.getRequiredPrinter() <= 0 && process.getRequiredScanner() <= 0 && process.getRequiredRouter() <= 0 && process.getRequiredCDROM() <= 0) {
/*                allocateMemoryFCFS(process.getRequiredMem());*/                   //ORNEK CIKTISINDAN DOLAYI BELLEK TAHSISI DEGISTI !!!
                return true;
            }else return false;
        } else {
            if (process.getRequiredMem() <= availableMemRR && process.getRequiredPrinter() <= availablePrinter && process.getRequiredScanner() <= availableScanner && process.getRequiredRouter() <= availableRouter && process.getRequiredCDROM() <= availableCDROM) {
/*                allocateMemoryRR(process.getRequiredMem());
                usePrinter(process.getRequiredPrinter());
                useScanner(process.getRequiredScanner());                           //ORNEK CIKTISINDAN DOLAYI BELLEK TAHSISI DEGISTI !!!
                useCDROM(process.getRequiredCDROM());*/                             //Ornek ciktinin hatali oldugunu dusunuyorum.
                return true;
            } else return false;
        }
    }
    //Yayınlanan kaynak fonksiyonu
    void releaseResources(ExecutableProcess process){
        if(process.getPriority()==0)
            freeMemoryFCFS(process.getRequiredMem());
        else {
            freeMemoryRR(process.getRequiredMem());
            releasePrinter(process.getRequiredPrinter());
            releaseScanner(process.getRequiredScanner());
            releaseRouter(process.getRequiredRouter());
            releaseCDROM(process.getRequiredCDROM());
        }
    }//Ulaşan Prosesleri Yazdırma
    public  void printAllArrivedProcesses(int time) {
        System.out.println("ZAMAN = " + time);
        System.out.printf("| %3s | %5s | %7s | %6s | %3s | %3s | %3s | %3s | %3s | %6s | %11s |%n", "pid", "varış", "öncelik" , "kzaman", "mem" , "prn","scn","mdm","cd","Yzaman","status");
        System.out.println("================================================================================================");
        for (ExecutableProcess process : allArrivedProcessList) {
            if (process.getProcessStatus() == "ERROR") {
                System.out.printf(process.colorStringArray[0], process.colorStringArray[1], "| %-3d |", process.getProcessID(), "\u001B[0m");
                System.out.println(process.colorStringArray[0] + process.colorStringArray[1] + process.getProcessString() + "\u001B[0m");
            } else {
                System.out.printf("%s%s| %-3d | %-5d | %-7d | %-6d | %-3d | %-3d | %-3d | %-3d | %-3d | %-6d | %-11s |%n%s",
                        process.colorStringArray[0], process.colorStringArray[1],
                        process.getProcessID(),process.getArriveTime(), process.getPriortiyOnInitial(), process.getBurstTime(), process.getRequiredMem(),process.getRequiredPrinter(), process.getRequiredScanner(), process.getRequiredRouter(), process.getRequiredCDROM(),process.getAliveTime(), process.getProcessStatus(), "\u001B[0m");
            }
        }
    }
    public void addToArrivedProcessList(ExecutableProcess process){
        allArrivedProcessList.add(process);
    }
    public void printResources(){
        System.out.println("Kalan Kullanilabilir RR alani :" + this.getAvailableMemRR() + " Mbyte");
        System.out.println("Kalan Kullanilabilir FCFS alani :" + this.getAvailableMemFCFS() +" Mbyte");
        System.out.println("Kalan Kullanilabilir Yazici Sayisi :" +this.getAvailablePrinter());
        System.out.println("Kalan Kullanilabilir Tarayici Sayisi :" +this.getAvailableScanner());
        System.out.println("Kalan Kullanilabilir Modem Sayisi :" +this.getAvailableRouter());
        System.out.println("Kalan Kullanilabilir CD/DVD Sayisi :" +this.getAvailableCDROM());
    }
    private void freeMemoryRR(int mem) {
        availableMemRR +=mem;
    }
    private void freeMemoryFCFS(int mem){
        availableMemFCFS+=mem;
    }
    private void releasePrinter(int printerAmount) {
        availablePrinter+=printerAmount;
    }

    private void releaseScanner(int scannerAmount) {
        availableScanner+=scannerAmount;
    }

    private void releaseRouter(int routerAmount) {
        availableRouter+=routerAmount;
    }

    private void releaseCDROM(int cdromAmount) {
        availableCDROM+=cdromAmount;
    }

    private void allocateMemoryRR(int reqMem) {
        availableMemRR = availableMemRR - reqMem;
    }

    private void allocateMemoryFCFS(int reqMem) {
        availableMemFCFS = availableMemFCFS - reqMem;
    }

    private void usePrinter(int reqPrinter) {
        availablePrinter = availablePrinter - reqPrinter;
    }

    private void useScanner(int reqScanner) {
        availableScanner = availableScanner - reqScanner;
    }

    private void useRouter(int reqRouter) {
        availableRouter = availableRouter - reqRouter;
    }

    private void useCDROM(int reqCDROM) {
        availableCDROM = availableCDROM - reqCDROM;
    }

    public int getAvailableMemRR() {
        return availableMemRR;
    }

    public int getAvailableMemFCFS() {
        return availableMemFCFS;
    }

    public int getAvailablePrinter() {
        return availablePrinter;
    }

    public int getAvailableScanner() {
        return availableScanner;
    }

    public int getAvailableRouter() {
        return availableRouter;
    }

    public int getAvailableCDROM() {
        return availableCDROM;
    }
}


