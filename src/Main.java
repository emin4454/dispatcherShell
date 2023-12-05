import java.util.LinkedList;
import java.util.Queue;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
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

        Queue<ExecutableProcess> waitingQueue = new LinkedList<>();

        int time = 0; // zaman degeri ataniyor
        while (true) {
            for (ExecutableProcess process : waitingQueue) {

            }
            for (ExecutableProcess process : processes) {  //tum prosesler dolasiliyor
                if (process.getArriveTime() == time) {
                    if (device.tryAllocateForProcess(process)) {
                        scheduler[process.getPriority()].addToQueue(process);
                        System.out.println(process.getPriority() + ". priority sirasina eklendi "); //zamani gelen proses var ise queuya ekleniyor
                    } else
                        waitingQueue.add(process);
                }
            }                       // PROSESLERIN EKLENME KISIMI
            try {
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
            time++;
        }
    }
}