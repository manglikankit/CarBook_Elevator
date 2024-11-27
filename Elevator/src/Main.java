public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        LiftRepository liftRepository = new LiftRepository();

        // Initialize lifts
        liftRepository.saveLift(new Lift(1));
        liftRepository.saveLift(new Lift(2));
        liftRepository.saveLift(new Lift(3));

        LiftService liftService = new LiftService(liftRepository);
        LiftController liftController = new LiftController(liftService);

        // Start the controller
        liftController.start();

    }
}