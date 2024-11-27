import java.util.List;
import java.util.concurrent.*;

//@RestController
//@RequestMapping("/api/cab")
public final class BookingController {
    private BookingService bookingService;
//    private ExecutorService executorService;

    public BookingController() {
        CarRepository carRepository = new CarRepository();
        this.bookingService = new BookingService(carRepository);
//        this.executorService = Executors.newFixedThreadPool(10);
    }

    // Endpoint to show available cars
//    @GetMapping("/available-cars")
    public List<Car> getAvailableCars() {
        return bookingService.getAvailableCars();
    }

    // Endpoint to book a car
//    @PostMapping("/book-car")
    public String bookCar(String carId) { //@RequestParam
        String id = bookingService.bookCar(carId);
        if (!id.equals("no car Id")) {
            return "Car "+ id +  " booked successfully!";
        } else {
            return "Car "+ id + " is either not available or does not exist!";
        }
    }
    public void releaseCar(String carId) { //@RequestParam
        bookingService.releaseCar(carId);
    }

    public void bookcarByMultithreading(String carId){

        CompletableFuture.runAsync(() -> {
                    // Simulate a task
                    String id = bookingService.bookCar(carId);
                    if (!id.equals("no car Id")) {
                        System.out.println("Car booked successfully!");
                    } else {
                        System.out.println("Car is either not available or does not exist!") ;
                    }
                });


/*
        Future<String> result = executorService.submit(() -> {
            return bookingService.bookCar(carId);
        });

        try {
            if (!result.get().equals("no car Id")) {
                    System.out.println("Car "+ result.get() +  " booked successfully!");
            } else {
                System.out.println( "Car "+ result.get() + " is either not available or does not exist!");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        */
    }
//    @PreDestroy
    public void shutdown() {
       // executorService.shutdown();
    }

    public static void main(String[] args) {
        BookingController bookingController = new BookingController();
        new Thread(()-> {
            bookingController.bookcarByMultithreading("2");
        }).start();
        new Thread(()-> {
            bookingController.bookcarByMultithreading("2");
        }).start();
        new Thread(()-> {
            bookingController.bookcarByMultithreading("1");
        }).start();
        try {
            Thread.sleep(50);
            new Thread(()-> {
                List<Car> availableCars = bookingController.getAvailableCars();
                for (Car car: availableCars)
                    System.out.println(car.getId());
            }).start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(()-> {
            bookingController.releaseCar("1");
        }).start();
        try {
            Thread.sleep(10);
            new Thread(()-> {
                List<Car> availableCars = bookingController.getAvailableCars();
                for (Car car: availableCars)
                    System.out.println(car.getId());
            }).start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}