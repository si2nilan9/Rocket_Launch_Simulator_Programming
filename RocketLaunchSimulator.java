package Project;
import java.util.Scanner;

public class RocketLaunchSimulator {

	private String stage = "Pre-Launch";
    private double fuel = 100.0;
    private int altitude = 0;
    private int speed = 0;
    private boolean checksComplete = false;
    private boolean launched = false;

    public void startChecks() throws PreLaunchException {
        if (checksComplete) {
            throw new PreLaunchException("Pre-launch checks have already been completed.");
        }
        System.out.println("All systems are 'Go' for launch.");
        checksComplete = true;
    }

    public void launch() throws LaunchException {
        if (!checksComplete) {
            throw new LaunchException("Cannot launch. Perform pre-launch checks first.");
        }
        if (launched) {
            throw new LaunchException("Rocket has already been launched.");
        }
        System.out.println("Launching the rocket!");
        stage = "1";
        launched = true;
        updateParameters();
        displayStatus();
    }

    public void simulate(int seconds) throws SimulationException {
        if (!launched) {
            throw new SimulationException("Rocket has not been launched yet.");
        }
        for (int i = 0; i < seconds; i++) {
            updateParameters();
            System.out.println("Stage: " + stage + ", Fuel: " + String.format("%.1f", fuel) + "%, Altitude: " + altitude + " km, Speed: " + speed + " km/h");
            if (fuel <= 0) {
                System.out.println("Mission Failed due to insufficient fuel.");
                break;
            }
            if (stage.equals("2") && altitude >= 1000) {
                System.out.println("Orbit achieved! Mission Successful.");
                break;
            }
            if (stage.equals("1") && fuel < 50) {
                System.out.println("Stage 1 complete. Separating stage. Entering Stage 2.");
                stage = "2";
                fuel = 50;
                altitude += 400;
                speed += 40000;
            }
        }
    }

    private void updateParameters() {
        fuel -= 1;
        altitude += 10;
        speed += 1000;
    }
    
    private void displayStatus() {
        System.out.println("Stage: " + stage + ", Fuel: " + String.format("%.1f", fuel) + "%, Altitude: " + altitude + " km, Speed: " + speed + " km/h");
    }


    public void reset() {
        stage = "Pre-Launch";
        fuel = 100.0;
        altitude = 0;
        speed = 0;
        checksComplete = false;
        launched = false;
        System.out.println("Simulator reset to initial state.");
    }
   
}
