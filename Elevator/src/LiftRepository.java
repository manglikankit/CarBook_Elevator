import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Repo
public class LiftRepository {
    private final List<Lift> lifts = new ArrayList<>();

    public void saveLift(Lift lift) {
        lifts.add(lift);
    }

    public List<Lift> getAllLifts() {
        return lifts;
    }
}