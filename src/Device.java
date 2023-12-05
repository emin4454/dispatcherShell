public  class Device {
    private int availableMemRR = 960;    // Makinenin sahip oldugu bellek miktari
    private int availableMemFCFS = 64;
    private int availablePrinter = 2;// Makinenin sahip oldugu yazici sayisi
    private int availableScanner = 1;//  Makinenin sahip oldugu tarayici sayisi
    private int availableRouter = 1;//  Makinenin sahip oldugu Modem sayisi
    private int availableCDROM = 2;// Makinenin sahip oldugu cd/dvd sayisi

    Device() {

    }

    boolean tryAllocateForProcess(ExecutableProcess process) {
        if (process.getPriority() == 0 && process) {
            allocateMemoryFCFS(process.getRequiredMem());
            return true;
        } else {
            if (process.getRequiredMem() <= availableMemRR &&
                    process.getRequiredPrinter() <= availablePrinter &&
                    process.getRequiredScanner() <= availableScanner &&
                    process.getRequiredRouter() <= availableRouter &&
                    process.getRequiredCDROM() <= availableCDROM){
                allocateMemoryRR(process.getRequiredMem());
                usePrinter(process.getRequiredPrinter());
                useScanner(process.getRequiredScanner());
                useCDROM(process.getRequiredCDROM());
                return true;
            }
            else return false;
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

    private void allocateMemoryRR(int reqMem) {
            availableMemRR = availableMemRR - reqMem;
    }
    private void allocateMemoryFCFS(int reqMem) {
        availableMemFCFS = availableMemFCFS - reqMem;}
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
            System.out.println(availableCDROM);
    }

    public int getAvailableMemRR() {
        return availableMemRR;
    }
}
