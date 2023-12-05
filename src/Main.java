import java.util.LinkedList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        Device device = new Device();

        LinkedList<ExecutableProcess> processes = new LinkedList<>();
        processes.add(new ExecutableProcess(0, 1, 2, 574, 2, 0, 1, 3));
        processes.add(new ExecutableProcess(1, 0, 1, 50, 0, 0, 0, 10));
        processes.add(new ExecutableProcess(1, 0, 3, 65, 0, 0, 0, 0));

        Scheduler[] scheduler = new Scheduler[4];
        scheduler[0] = new FCFS(device ,0);
        scheduler[1] = new RoundRobin(device ,1);
        scheduler[2] = new RoundRobin(device ,2);
        scheduler[3] = new RoundRobin(device,3);

        int time = 0;

        for (int i = 0; i < 10; i++) {
            scheduler[0].executeOneIteration();
            time++;
        }
    }
}