import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


public class Main {
    public static String clearScreenString = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    public static void main(String[] args) throws IOException, InterruptedException {


        ReadFile readfile = new ReadFile("giris.txt");
        Device device = new Device();   // cihaz olusturuluyor

        LinkedList<ExecutableProcess> processes = readfile.ReadTheFile();

        Scheduler[] scheduler = new Scheduler[4];       // Cihaz icin 4 tane gorevlendirici olusturuluyor
        scheduler[0] = new FCFS(device, 0);             //FCFS
        scheduler[1] = new GBG(device, 1);       // roundrobinler
        scheduler[2] = new GBG(device, 2);
        scheduler[3] = new RoundRobin(device, 3);

        Queue<ExecutableProcess> timeOutQueue = new LinkedList<>();

        int time = 0; // zaman tanimlaniyor
        int lastIteratedPriority = 0; //Kesme geldigini anlamak icin tanimlaniyor
        int lastArriveTime = readfile.getLastArriveTime();
        while (true) {
            for (ExecutableProcess process : processes) {               //Tum prosesler dolasiliyor
                if (process.getArriveTime() == time) {                  // Eger prosesin zamani geldiyse
                    process.assignProcess(device);
                    if (device.tryAllocateForProcess(process)) {
                        process.setProcessStatus("HAZIR");//eger proses yeterli alana sahipse//eger onceligi daha yuksekse kesme geldigini belirtir
                        scheduler[process.getPriority()].addToQueue(process);   //zamani gelen proses var ise queuya ekleniyor
                        if (process.getPriority() == 0) {
                            for (int i = 1; i < 4; i++) {
                                scheduler[i].suspendAllProcesses();
                            }
                        }
                    } else {
                        process.setProcessStatus("ERROR");
                    }
                }
            }
            // PROSESLERIN SIRAYA YERLESTIRILDIGI ALGORITMA SONU

            if (!timeOutQueue.isEmpty()) {
                ExecutableProcess process = timeOutQueue.poll();
                device.releaseResources(process);
                process.setProcessStatus("ERROR");
                process.setProcessString(" HATA - Proses zaman aşımı (20 sn de tamamlanamadı)");
            } else {              // PROSESLERIN EXECUTELANDIĞI YER
                for (int i = 0; i < 4; i++) {
                    if (!scheduler[i].isListEmpty()) {   // hazır listesi boş degil ise
                        scheduler[i].setReadyAllProcesses();
                        scheduler[i].executeOneIteration(scheduler);        //En yuksek oncelikli process 1 iterasyon calistiriliyor
                        lastIteratedPriority = i;   // kesme kontrolü için  son itterasyona eşitlenir
                        break;
                    }
                }
            }
            device.printAllArrivedProcesses(time);
            for (int i = 0; i < 4; i++) {
                scheduler[i].increaseAliveTimeAllQueue(timeOutQueue);//Proseslere kesme gelirse askıya al
            }
            time++;  //Zaman 1 arrtiriliyor
            sleep();
            clearScreen();
            if(time>lastArriveTime)
            {
                boolean endCheck = true;
                for (int i =0 ;i<4;i++){
                    if(!scheduler[i].isListEmpty()){
                        endCheck=false;
                    }
                }
                if(endCheck){
                    System.out.println("PROSESLER BITTI PROGRAM SONLANIYOR");
                    break;
                }
            }
        }
    }

    public static void clearScreen() {
        System.out.println(clearScreenString);
    }

    static void sleep() {
        try {
            Thread.sleep(1000);         // 1 saniye bekleniliyor
        } catch (Exception e) {   //eğer hata var ise hatayı yazdır
            System.out.println(e.toString());
        }
    }
}

