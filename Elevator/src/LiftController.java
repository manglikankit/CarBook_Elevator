import java.util.Scanner;

public class LiftController {
    private final LiftService liftService;

    public LiftController(LiftService liftService) {
        this.liftService = liftService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Lift System!");
        while (true) {
            System.out.println("\n1. Request Lift");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            if (option == 2) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter Source Floor: ");
            int sourceFloor = scanner.nextInt();

            System.out.print("Enter Target Floor: ");
            int targetFloor = scanner.nextInt();

            String result = liftService.requestLift(sourceFloor, targetFloor);
            System.out.println(result);
        }

        scanner.close();
    }
}
