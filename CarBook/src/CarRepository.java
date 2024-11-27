import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRepository {
    private List<Car> carStock;

    public CarRepository() {
        // Simulating initial car stock
        this.carStock = new ArrayList<>();
        carStock.add(new Car("1", "Sedan"));
        carStock.add(new Car("2", "SUV"));
        carStock.add(new Car("3", "Hatchback"));
    }

    // Get a list of available cars
    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : carStock) {
            if (car.isAvailable()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    // Book a car if available (Thread-safe method)
    public String bookCar(String carId) {
        Optional<Car> car = carStock.stream().filter(c -> c.getId().equals(carId) && c.isAvailable()).findFirst();
        if (car.isPresent()) {
            synchronized(this) {
                car.get().setAvailable(false);
            }
            return car.get().getId();
        }
        return "no car Id";
    }
    public void releaseCar(String carId){
        Optional<Car> car = carStock.stream().filter(c -> c.getId().equals(carId) && c.isAvailable()==false).findFirst();
        if (car.isPresent()) {
            synchronized(this) {
                car.get().setAvailable(true);
            }
        }
    }
}
