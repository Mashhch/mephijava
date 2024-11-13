package hw3;

// Sealed class для транспортных средств
public sealed class TransportVehicle extends Transport permits Car, Plane, Ship, Bicycle {

    public TransportVehicle(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Transport: " + name + ", Engine: " + getEngineType());
    }

    @Override
    public void drive() {
        System.out.println("driveeeeeee ");
    }

    public void info() {
        System.out.println("Класс, который могут наследовать только Car, Plane, Ship, Bicycle.");
    }
}
