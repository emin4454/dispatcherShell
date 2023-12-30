import java.io.IOException;
import java.util.Iterator;
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


        ReadFile readfile = new ReadFile("giris.txt");
        Device device = new Device();   // cihaz olusturuluyor

        LinkedList<ExecutableProcess> processes = readfile.ReadTheFile();

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
//            System.out.println("---------------------------------------------------------------------------------------");
//            System.out.println("zaman : " + time + " - " + (time + 1) + " arasi");
//            Iterator<ExecutableProcess> iterator  = insufficientSourceList.iterator();
//            while(iterator.hasNext()){
//                ExecutableProcess process = iterator.next();                                      //PROGRAM CIKTISINDAN DOLAYI BU KODLARIN CALISMASINA GEREK KALMADI
//                if (device.tryAllocateForProcess(process)) {
//                    process.assignProcess();
//                    iterator.remove();
//                    scheduler[process.getPriority()].addToQueue(process);// Kaynak yetmezliginden dolayı sirada olan processler
//                    String sch = process.getPriority() == 0 ? "Gercek Zamanli (FCFS) " : process.getPriority() + ". Seviye Geri Beslemeli(Round Robin)";
//                    System.out.println("Bekleme sirasinda olan " +process.getPriority() + ". seviye öncelikli ve " + process.getBurstTime() +
//                            " islem suresine sahip olan prosesin IDsi " + process.getProcessID() + " olarak atandi ve " + sch + "İş sıralayıcıya yerleştirildi");
//                }   // Tekrardan Gorevlendirici sirasina yerlestirilmeye calisiliyor
//            }
            boolean isAdded = false;
            boolean skipToNextSecond = false;
            for (ExecutableProcess process : processes) {               //Tum prosesler dolasiliyor
                if (process.getArriveTime() == time) {                       // Eger prosesin zamani geldiyse
                    isAdded = true;
                    if(device.isDeviceEnoughForAllocate(process)) {         // eğer eklendiyse true
                        if (device.tryAllocateForProcess(process)) {        //eger proses yeterli alana sahipse
                            process.assignProcess();                        //prosese id atanir
                            if (process.getPriority() < lastIteratedPriority)      //eger onceligi daha yuksekse kesme geldigini belirtir
                                //System.out.println("Kesme Geldi (Daha yüksek öncelikli bir process geldi)");
                                scheduler[process.getPriority()].addToQueue(process);   //zamani gelen proses var ise queuya ekleniyor
                            } else {
                                System.out.println("HATA - Proses çok sayıda kaynak talep ediyor - proses silindi");
                                skipToNextSecond =true;
                                break;
                        }
                    }
                }
            }
            if(skipToNextSecond){
                time++;
                continue;
            }
            // PROSESLERIN SIRAYA YERLESTIRILDIGI ALGORITMA SONU
            for(int i = 0; i<4 ; i++){
                scheduler[i].increaseAliveTimeAllQueue(timeOutQueue);   //Processlerin yasadigi zaman 1 arttiriliyor
            }
            if(!timeOutQueue.isEmpty()){
                ExecutableProcess process = timeOutQueue.poll();
                device.releaseResources(process);
                System.out.println("20 saniye zaman asimini dolduran proses sona erdirilmeden 1 kez calismasina izin veriliyor");

            }
            else {              // PROSESLERIN EXECUTELANDIĞI YER
                for (int i = 0; i < 4; i++) {
                    if (!scheduler[i].isListEmpty()) {   // hazır listesi boş ise
                        scheduler[i].executeOneIteration(scheduler);        //En yuksek oncelikli process 1 iterasyon calistiriliyor
                        lastIteratedPriority = i;   // kesme kontrolü için  son itterasyona eşitlenir
                        break;
                    }
                }
            }
            System.out.println("---------------------------------------------------------------------------------------");
            time++;  //Zaman 1 arrtiriliyor
            try {
                Thread.sleep(500);         // 1 saniye bekleniliyor
            } catch (Exception e) {   //eğer hata var ise hatayı yazdır
                System.out.println(e.toString());
            }
        }
    }
}