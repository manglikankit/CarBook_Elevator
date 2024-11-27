import java.util.LinkedList;
import java.util.Queue;

//Entity
public class Lift {
    private int id;
    private int currentFloor;
    private boolean isMoving;
    private String direction; // "UP" or "DOWN"
    private Queue<Request> requestQueue;

    public Lift(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.isMoving = false;
        this.direction = "IDLE";
        this.requestQueue = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Queue<Request> getRequestQueue() {
        return requestQueue;
    }

    public void addRequest(Request request) {
        this.requestQueue.add(request);
    }

    @Override
    public String toString() {
        return "Lift{" +
                "id=" + id +
                ", currentFloor=" + currentFloor +
                ", isMoving=" + isMoving +
                ", direction='" + direction + '\'' +
                ", requestQueue=" + requestQueue +
                '}';
    }
}
