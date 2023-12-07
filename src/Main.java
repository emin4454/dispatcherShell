import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Device device = new Device();   // cihaz olusturuluyor

        LinkedList<ExecutableProcess> processes = new LinkedList<>();
        processes.add(new ExecutableProcess(1, 1, 2, 574, 2, 0, 1, 2));
        processes.add(new ExecutableProcess(1, 0, 3, 50, 0, 0, 0, 10));
        processes.add(new ExecutableProcess(1, 0, 3, 62, 0, 0, 0, 0));

        Scheduler[] scheduler = new Scheduler[4];
        scheduler[0] = new FCFS(device, 0);
        scheduler[1] = new RoundRobin(device, 1);       // Cihaz icin 4 tane gorevlendirici olusturuluyor
        scheduler[2] = new RoundRobin(device, 2);
        scheduler[3] = new RoundRobin(device, 3);

        List<ExecutableProcess> insufficientSourceList = new LinkedList<>();

        int time = 0; // zaman tanimlaniyor
        int lastIteratedPriority = 0; //Kesme geldigini anlamak icin tanimlaniyor

        while (true) {
            //PROSESLERIN SIRALARA YERLESTIRILDIGI ALGORITMA BASLANGICI
            System.out.println("-----------------------------------------------");
            System.out.println("zaman : " + time);
            for (ExecutableProcess process : insufficientSourceList) {
                if (device.tryAllocateForProcess(process)) {
                    scheduler[process.getPriority()].addToList(process);// Kaynak yetmezliginden dolayı sirada olan processler
                    insufficientSourceList.remove(process);
                    System.out.println(process.getPriority() + ". priority sirasina eklendi ama yedekten");
                }   // Tekrardan Gorevlendirici sirasina yerlestirilmeye calisiliyor
            }
            for (ExecutableProcess process : processes) {  //tum prosesler dolasiliyor
                if (process.getArriveTime() == time) {
                    if (device.tryAllocateForProcess(process)) {        //eger proses yeterli alana sahipse
                        process.assignProcess();
                        if(process.getPriority()<lastIteratedPriority)
                            System.out.println("Kesme Geldi (Daha yüksek öncelikli bir process geldi)");
                        scheduler[process.getPriority()].addToList(process); //zamani gelen proses var ise queuya ekleniyor
                        System.out.println(process.getPriority() + ". priority sirasina sahip " + process.getBurstTime() +
                                " islem suresine sahip islemin processIDsi " + process.getProcessID() + " olarak atandi ");
                    } else
                        insufficientSourceList.add(process);
                }
            }
            System.out.println("-----------------Gorevlendiriciye yerlestirme asamasi bitti--------------");
            // PROSESLERIN SIRAYA YERLESTIRILDIGI ALGORITMA SONU

            // PROSESLERIN EXECUTELANACAGI YER
            for (int i = 0; i < 4; i++) {
                if (!scheduler[i].isListEmpty()) {
                    scheduler[i].executeOneIteration();
                    lastIteratedPriority = i;
                    break;
                }
            }

            System.out.println("Kalan Kullanilabilir RR ALANI :" + device.getAvailableMemRR());
            System.out.println("Kalan Kullanilabilir FCFS ALANI :" + device.getAvailableMemFCFS());
            time++;
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}