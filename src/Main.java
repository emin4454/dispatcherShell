import java.util.LinkedList;
import java.util.Queue;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Device device = new Device();   // cihaz olusturuluyor

        LinkedList<ExecutableProcess> processes = new LinkedList<>();
        processes.add(new ExecutableProcess(0, 1, 2, 574, 2, 0, 1, 2));
        processes.add(new ExecutableProcess(1, 0, 1, 50, 0, 0, 0, 10));
        processes.add(new ExecutableProcess(1, 0, 3, 65, 0, 0, 0, 0));

        Scheduler[] scheduler = new Scheduler[4];
        scheduler[0] = new FCFS(device, 0);
        scheduler[1] = new RoundRobin(device, 1);       // 4 tane gorevlendirici olusturuluyor
        scheduler[2] = new RoundRobin(device, 2);
        scheduler[3] = new RoundRobin(device, 3);

        Queue<ExecutableProcess> insufficientSouceQueue = new LinkedList<>();

        int time = 0; // zaman tanimlaniyor
        while (true) {
            for (ExecutableProcess process : insufficientSouceQueue) {
                if (device.tryAllocateForProcess(process)) {
                    scheduler[process.getPriority()].addToQueue(process);// Kaynak yetmezliginden dolayı sirada olan processler
                    System.out.println(process.getPriority() + ". priority sirasina eklendi ama yedekten");
                }                                                        // Tekrardan Gorevlendirici sirasina yerlestirilmeye calisiliyor
            }
            for (ExecutableProcess process : processes) {  //tum prosesler dolasiliyor
                if (process.getArriveTime() == time) {
                    if (device.tryAllocateForProcess(process)) {
                        scheduler[process.getPriority()].addToQueue(process);
                        System.out.println(process.getPriority() + ". priority sirasina eklendi "); //zamani gelen proses var ise queuya ekleniyor
                    } else
                        insufficientSouceQueue.add(process);
                }
            }                       // PROSESLERIN EKLENME KISIMI
            System.out.println("Kalan Kullanilabilir RR ALANI :" +device.getAvailableMemRR());
            System.out.println("Kalan Kullanilabilir FCFS ALANI :" +device.getAvailableMemFCFS());
            time++;
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}