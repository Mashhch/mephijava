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

final class Car extends Transport implements Drivable {
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

final class Plane extends Transport {
    public Plane(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Plane: " + name + ", Engine: " + getEngineType());
    }

    @Override
    public void drive() {
        //
    }
}

final class Ship extends Transport {
    public Ship(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Ship: " + name + ", Engine: " + getEngineType());
    }

    @Override
    public void drive() {

    }
}

final class Bicycle extends Transport {
    public Bicycle(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Bicycle: " + name + ", Engine: " + getEngineType());
    }

    @Override
    public void drive() {

    }
}

sealed abstract class LuxuryTransport extends Transport permits Limousine, Yacht, PrivateJet {

    public LuxuryTransport(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void drive() {
        System.out.println("Поездка с комфортом.");
    }
}

final class Limousine extends LuxuryTransport {
    public Limousine(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Limousine: " + name + ", Engine: " + getEngineType());
    }
}

final class Yacht extends LuxuryTransport {
    public Yacht(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Yacht: " + name + ", Engine: " + getEngineType());
    }
}

final class PrivateJet extends LuxuryTransport {
    public PrivateJet(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Private Jet: " + name + ", Engine: " + getEngineType());
    }
}


public class Main {
    public static void main(String[] args) {
        Transport car = new Car("Car", "ELECTRIC");
        car.displayInfo();

        Transport plane = new Plane("Plane", "ELECTRIC");
        plane.displayInfo();

        Transport ship = new Ship("Ship", "CASUAL");
        ship.displayInfo();

        Transport bicycle = new Bicycle("Bicycle", "NONE");
        bicycle.displayInfo();

        Transport limousine = new Limousine("Limousine", "CASUAL");
        limousine.displayInfo();

        Transport yacht = new Yacht("Yacht", "CASUAL");
        yacht.displayInfo();

        Transport privateJet = new PrivateJet("Private Jet", "CASUAL");
        privateJet.displayInfo();
    }

}
