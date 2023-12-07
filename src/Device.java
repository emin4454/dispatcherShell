public class Device {
    private int availableMemRR = 960;    // Makinenin sahip oldugu bellek miktari
    private int availableMemFCFS = 64;
    private int availablePrinter = 2;// Makinenin sahip oldugu yazici sayisi
    private int availableScanner = 1;//  Makinenin sahip oldugu tarayici sayisi
    private int availableRouter = 1;//  Makinenin sahip oldugu Modem sayisi
    private int availableCDROM = 2;// Makinenin sahip oldugu cd/dvd sayisi

    Device() {

    }

    boolean tryAllocateForProcess(ExecutableProcess process) {
        if (process.getPriority() == 0 ) {
            if(process.getRequiredMem() <= availableMemFCFS) {
                allocateMemoryFCFS(process.getRequiredMem());
                return true;
            }else return false;
        } else {
            if (process.getRequiredMem() <= availableMemRR && process.getRequiredPrinter() <= availablePrinter && process.getRequiredScanner() <= availableScanner && process.getRequiredRouter() <= availableRouter && process.getRequiredCDROM() <= availableCDROM) {
                allocateMemoryRR(process.getRequiredMem());
                usePrinter(process.getRequiredPrinter());
                useScanner(process.getRequiredScanner());
                useCDROM(process.getRequiredCDROM());
                return true;
            } else return false;
        }
    }
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


