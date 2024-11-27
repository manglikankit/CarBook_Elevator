public class Request {

    private final int sourceFloor;
    private final int targetFloor;

    public Request(int sourceFloor, int targetFloor) {
        this.sourceFloor = sourceFloor;
        this.targetFloor = targetFloor;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    @Override
    public String toString() {
        return "Request{" +
                "sourceFloor=" + sourceFloor +
                ", targetFloor=" + targetFloor +
                '}';
    }
}
