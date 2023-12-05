public  class Device {
    private int avaivableMemRR = 960;    // Makinenin sahip oldugu bellek miktari
    private int availeMemFCFS = 64;
    private int avaivablePrinter = 2;// Makinenin sahip oldugu yazici sayisi
    private int avaivableScanner = 1;//  Makinenin sahip oldugu tarayici sayisi
    private int avaivableRouter = 1;//  Makinenin sahip oldugu Modem sayisi
    private int avaivableCDROM = 2;// Makinenin sahip oldugu cd/dvd sayisi

    Device() {

    }

    void tryAllocateForProcess(ExecutableProcess process) {
        if (process.getPriority() == 0) {
            return ;
        } else {
            if (process.getRequiredMem() <= avaivableMemRR &&
                    process.getRequiredPrinter() <= avaivablePrinter &&
                    process.getRequiredScanner() <= avaivableScanner&&
                    process.getRequiredRouter() <= avaivableRouter &&
                    process.getRequiredCDROM() <= avaivableCDROM ){
                allocateMemory(process.getRequiredMem());
                usePrinter(process.getRequiredPrinter());
                useScanner(process.getRequiredScanner());
                useCDROM(process.getRequiredCDROM());
                return;
            }
        }
    }

    void freeMemory() {
    }

    void releasePrinter() {
    }

    void releaseScanner() {
    }

    void releaseRouter() {

    }

    void releaseCDROM() {

    }

    void allocateMemory(int reqMem) {
            avaivableMemRR = avaivableMemRR - reqMem;
    }

    public void usePrinter(int reqPrinter) {
            avaivablePrinter = avaivablePrinter - reqPrinter;
    }

    public void useScanner(int reqScanner) {
            avaivableScanner = avaivableScanner - reqScanner;
    }


    public void useRouter(int reqRouter) {
            avaivableRouter = avaivableRouter - reqRouter;
    }

    public void useCDROM(int reqCDROM) {
            avaivableCDROM = avaivableCDROM - reqCDROM;
    }

    public int getAvaivableMemRR() {
        return avaivableMemRR;
    }
}
