import java.util.List;

public class BookingService {
    private CarRepository carRepository;

    public BookingService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Show all available cars
    public List<Car> getAvailableCars() {
        return carRepository.getAvailableCars();
    }

    // Book a car
    public String bookCar(String carId) {
        return carRepository.bookCar(carId);
    }
    public void releaseCar(String carId){
        carRepository.releaseCar(carId);
    }

}
