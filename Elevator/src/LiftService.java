import java.util.List;

public class LiftService {
    private final LiftRepository liftRepository;

    public LiftService(LiftRepository liftRepository) {
        this.liftRepository = liftRepository;
    }

    public String requestLift(int sourceFloor, int targetFloor) {
        List<Lift> lifts = (List<Lift>) liftRepository.getAllLifts();

        // Find the best lift
        Lift bestLift = findBestLift(lifts, sourceFloor);

        if (bestLift == null) {
            return "No lifts available at the moment!";
        }

        // Add request to the best lift
        Request request = new Request(sourceFloor, targetFloor);
        bestLift.addRequest(request);

        // Start processing if the lift is idle
        if (!bestLift.isMoving()) {
            processLiftRequests(bestLift);
        }

        return "Request assigned to Lift " + bestLift.getId();
    }

    private Lift findBestLift(List<Lift> lifts, int sourceFloor) {
        Lift bestLift = null;
        int minDistance = Integer.MAX_VALUE;

        for (Lift lift : lifts) {
            if (lift.isMoving() && !canServeRequest(lift, sourceFloor)) {
                continue;
            }

            int distance = Math.abs(lift.getCurrentFloor() - sourceFloor);
            if (distance < minDistance) {
                minDistance = distance;
                bestLift = lift;
            }
        }

        return bestLift;
    }

    private boolean canServeRequest(Lift lift, int sourceFloor) {
        return (lift.getDirection().equals("UP") && sourceFloor >= lift.getCurrentFloor()) ||
                (lift.getDirection().equals("DOWN") && sourceFloor <= lift.getCurrentFloor());
    }

    private void processLiftRequests(Lift lift) {
        new Thread(() -> {
            while (!lift.getRequestQueue().isEmpty()) {
                Request request = lift.getRequestQueue().poll();
                moveLift(lift, request.getSourceFloor());
                moveLift(lift, request.getTargetFloor());
            }
            lift.setMoving(false);
            lift.setDirection("IDLE");
        }).start();
    }

    private void moveLift(Lift lift, int targetFloor) {
        lift.setMoving(true);
        System.out.println("Lift " + lift.getId() + " moving to floor " + targetFloor);

        while (lift.getCurrentFloor() != targetFloor) {
            if (lift.getCurrentFloor() < targetFloor) {
                lift.setCurrentFloor(lift.getCurrentFloor() + 1);
                lift.setDirection("UP");
            } else {
                lift.setCurrentFloor(lift.getCurrentFloor() - 1);
                lift.setDirection("DOWN");
            }
            System.out.println("Lift " + lift.getId() + " at floor " + lift.getCurrentFloor());
        }

        System.out.println("Lift " + lift.getId() + " reached floor " + targetFloor);
    }
}
