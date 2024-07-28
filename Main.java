package Project;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
        RocketLaunchSimulator simulator = new RocketLaunchSimulator();
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Welcome to the Rocket Launch Simulator!\nAvailable commands: start_checks | launch | fast_forward D | reset | exit");
        while (true) {
            System.out.print("> ");
            input = scanner.nextLine();

            try {
                if (input.equals("start_checks")) {
                    simulator.startChecks();
                } else if (input.equals("launch")) {
                    simulator.launch();
                } else if (input.startsWith("fast_forward ")) {
                    String[] parts = input.split(" ");
                    int seconds = Integer.parseInt(parts[1]);
                    simulator.simulate(seconds);
                } else if (input.equals("reset")) {
                    simulator.reset();
                } else if (input.equals("exit")) {
                    System.out.println("Exiting the simulator. Goodbye!");
                    break;
                } else {
                    System.out.println("Unknown command. Available commands: start_checks | launch | fast_forward D | reset | exit ");
                }
            } catch (PreLaunchException | LaunchException | SimulationException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }

}
