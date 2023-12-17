import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

/*
* Text dosyasindan processleri okuma sinifi yapilacak
* Printler renkli yapilacak ve düzenlenecek
* Kod Test edilecek
* bellek konumu
* */
public class Main {
    public static void main(String[] args) throws IOException {


        ReadFile readtxt = new ReadFile("giris.txt");
        readtxt.ReadTheFile();
        Device device = new Device();   // cihaz olusturuluyor

        LinkedList<ExecutableProcess> processes = new LinkedList<>();
        processes.add(new ExecutableProcess(4, 2, 7, 10, 0, 0, 0, 0));
        processes.add(new ExecutableProcess(0, 1, 7, 574, 2, 0, 1, 2));
        processes.add(new ExecutableProcess(1, 0, 3, 50, 0, 0, 0, 10));
        processes.add(new ExecutableProcess(2, 0, 5, 62, 0, 0, 0, 0));

        Scheduler[] scheduler = new Scheduler[4];       // Cihaz icin 4 tane gorevlendirici olusturuluyor
        scheduler[0] = new FCFS(device, 0);             //FCFS
        scheduler[1] = new RoundRobin(device, 1);       // roundrobinler
        scheduler[2] = new RoundRobin(device, 2);
        scheduler[3] = new RoundRobin(device, 3);

        List<ExecutableProcess> insufficientSourceList = new LinkedList<>(); //yetersiz kaynak listesi

        Queue<ExecutableProcess> timeOutQueue = new LinkedList<>();

        int time = 0; // zaman tanimlaniyor
        int lastIteratedPriority = 0; //Kesme geldigini anlamak icin tanimlaniyor

        while (true) {
            //PROSESLERIN SIRALARA YERLESTIRILDIGI ALGORITMA BASLANGICI
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("zaman : " + time + " - " + (time + 1) + " arasi");
            for (ExecutableProcess process : insufficientSourceList) {
                if (device.tryAllocateForProcess(process)) {
                    process.assignProcess();
                    scheduler[process.getPriority()].addToQueue(process);// Kaynak yetmezliginden dolayı sirada olan processler
                    insufficientSourceList.remove(process);
                    String sch = process.getPriority() == 0 ? "Gercek Zamanli (FCFS) " : process.getPriority() + ". Seviye Geri Beslemeli(Round Robin)";
                    System.out.println("Bekleme sirasinda olan " +process.getPriority() + ". seviye öncelikli ve " + process.getBurstTime() +
                            " islem suresine sahip olan prosesin IDsi " + process.getProcessID() + " olarak atandi ve " + sch + "İş sıralayıcıya yerleştirildi");
                }   // Tekrardan Gorevlendirici sirasina yerlestirilmeye calisiliyor
            }
            boolean isAdded = false;
            for (ExecutableProcess process : processes) {               //Tum prosesler dolasiliyor
                if (process.getArriveTime() == time) {                  // Eger prosesin zamani geldiyse
                    isAdded = true;                                     // eğer eklendiyse true
                    if (device.tryAllocateForProcess(process)) {        //eger proses yeterli alana sahipse
                        process.assignProcess();                        //prosese id atanir
                        if (process.getPriority() < lastIteratedPriority)      //eger onceligi daha yuksekse kesme geldigini belirtir
                            System.out.println("Kesme Geldi (Daha yüksek öncelikli bir process geldi)");
                        scheduler[process.getPriority()].addToQueue(process);   //zamani gelen proses var ise queuya ekleniyor
                        String sch = process.getPriority() == 0 ? "Gercek Zamanli (FCFS) " : process.getPriority() + ". Seviye Geri Beslemeli(Round Robin)";
                        System.out.println(process.getPriority() + ". seviye öncelikli ve " + process.getBurstTime() +
                                " islem suresine sahip olan prosesin IDsi " + process.getProcessID() + " olarak atandi ve " + sch + "İş siralayiciya yerlestirildi");
                    } else {
                        insufficientSourceList.add(process);   
                        System.out.println(process.getPriority() + " öncelikli " + process.getBurstTime() +    //Eger yeterli alan yoksa bekleme sirasina aliniyor
                                " islem suresi olan proses geldi ama yeterli kaynak olmadigindan bekleme sirasina alindi");
                    }
                }
            }
            if (!isAdded)
                System.out.println("Bu zaman diliminde hic yeni process gelmedi");
            device.printResources();    // cihazın kalan kaynakları yazılıyor
            System.out.println("--------Is siralayiciya yerlestirme asamasi bitti------");
            // PROSESLERIN SIRAYA YERLESTIRILDIGI ALGORITMA SONU
            for(int i = 0; i<4 ; i++){
                scheduler[i].increaseAliveTimeAllQueue(timeOutQueue);   //Processlerin yasadigi zaman 1 arttiriliyor
            }
            // PROSESLERIN EXECUTELANDIĞI YER
            for (int i = 0; i < 4; i++) {
                if (!scheduler[i].isListEmpty()) {   // hazır listesi boş ise
                    scheduler[i].executeOneIteration(scheduler);        //En yuksek oncelikli process 1 iterasyon calistiriliyor
                    lastIteratedPriority = i;   // kesme kontrolü için  son itterasyona eşitlenir 
                    break;
                }
            }
            System.out.println("---------------------------------------------------------------------------------------");
            time++;  //Zaman 1 arrtiriliyor
            try {
                Thread.sleep(3000);         // 1 saniye bekleniliyor
            } catch (Exception e) {   //eğer hata var ise hatayı yazdır
                System.out.println(e.toString());
            }
        }
    }
}