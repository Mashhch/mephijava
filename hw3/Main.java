package hw3;

interface Drivable {
    void drive();
}

abstract class Transport implements Drivable{
    protected String name;
    protected Engine engine;

    public Transport(String name, String engineType) {
        this.name = name;
        this.engine = createEngine(engineType);
    }

    protected enum EngineType {
        CASUAL, ELECTRIC, NONE
    }

    protected static class Engine {
        protected EngineType engineType;

        public Engine(EngineType engineType) {
            this.engineType = engineType;
        }

        public EngineType getEngineType() {
            return engineType;
        }
    }

    protected Engine createEngine(String engineType) {
        return switch (engineType.toUpperCase()) {
            case "ELECTRIC" -> new Engine(EngineType.ELECTRIC);
            case "CASUAL" -> new Engine(EngineType.CASUAL);
            case "NONE" -> new Engine(EngineType.NONE);
            default -> throw new IllegalArgumentException("Unknown engine type: " + engineType);
        };
    }

    public abstract void displayInfo();

    public EngineType getEngineType() {
        return this.engine.engineType;
    }
}

final class Car extends TransportVehicle implements Drivable {
    public Car(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void drive() {
        System.out.println(name + " is driving.");
    }

    @Override
    public void displayInfo() {
        System.out.println("Car: " + name + ", Engine: " + getEngineType());
    }
}

final class Plane extends TransportVehicle {
    public Plane(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Plane: " + name + ", Engine: " + getEngineType());
    }
}

final class Ship extends TransportVehicle {
    public Ship(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Ship: " + name + ", Engine: " + getEngineType());
    }
}

final class Bicycle extends TransportVehicle {
    public Bicycle(String name, String engineType) {
        super(name, "NONE");
    }

    @Override
    public void displayInfo() {
        System.out.println("Bicycle: " + name + ", Engine: " + getEngineType());
    }
}

public class Main {
    public static void main(String[] args) {
        TransportVehicle car = new Car("Car", "ELECTRIC");
        car.displayInfo();

        TransportVehicle plane = new Plane("Plane", "ELECTRIC");
        plane.displayInfo();

        TransportVehicle ship = new Ship("Ship", "CASUAL");
        ship.displayInfo();

        TransportVehicle bicycle = new Bicycle("Bicycle", "NONE");
        bicycle.displayInfo();
    }
}
