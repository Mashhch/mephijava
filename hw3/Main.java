package hw3;

import java.util.HashMap;
import java.util.Map;

import static hw3.Transport.Engine.registerEngineFactory;

interface Drivable {
    void drive();
}

abstract class Transport implements Drivable {
    protected String name;
    protected Engine engine;

    protected Transport(String name, String engineType) {
        this.name = name;
        this.engine = Engine.createEngine(engineType);
    }

    protected interface EngineFactory {
        Engine create();
    }

    protected static EngineFactory createEngineFactory(String engineType) {
        return () -> {
            Engine engine = new Engine();
            engine.engineType = engineType;
            return engine;
        };
    }

    protected static class Engine {
        protected String engineType;

        private static final Map<String, EngineFactory> engineFactories = new HashMap<>();


        public String getEngineType() {
            return engineType;
        }

        public static void registerEngineFactory(String engineType, EngineFactory factory) {
            engineFactories.put(engineType.toUpperCase(), factory);
        }

        public static void unregisterEngineFactory(String engineType) {
            engineFactories.remove(engineType.toUpperCase());
        }

        public static boolean isEngineTypeRegistered(String engineType) {
            return engineFactories.containsKey(engineType.toUpperCase());
        }

        protected static Engine createEngine(String engineType) {
            EngineFactory factory = engineFactories.get(engineType.toUpperCase());
            if (factory == null) {
                throw new IllegalArgumentException("Unknown engine type: " + engineType);
            }
            return factory.create();
        }
    }

    public abstract void displayInfo();

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
        System.out.println("Car: " + name + ", Engine: " + engine.getEngineType());
    }
}

final class Plane extends Transport {
    public Plane(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Plane: " + name + ", Engine: " + engine.getEngineType());
    }

    @Override
    public void drive() {
        System.out.println(name + " is driving.");
    }
}

final class Ship extends Transport {
    public Ship(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Ship: " + name + ", Engine: " + engine.getEngineType());
    }

    @Override
    public void drive() {
        System.out.println(name + " is driving.");
    }
}

final class Bicycle extends Transport {
    public Bicycle(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Bicycle: " + name + ", Engine: " + engine.getEngineType());
    }

    @Override
    public void drive() {
        System.out.println(name + " is driving.");
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
        System.out.println("Limousine: " + name + ", Engine: " + engine.getEngineType());
    }
}

final class Yacht extends LuxuryTransport {
    public Yacht(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Yacht: " + name + ", Engine: " + engine.getEngineType());
    }
}

final class PrivateJet extends LuxuryTransport {
    public PrivateJet(String name, String engineType) {
        super(name, engineType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Private Jet: " + name + ", Engine: " + engine.getEngineType());
    }
}

public class Main {
    public static void main(String[] args) {

        Transport.Engine.registerEngineFactory("CASUAL", Transport.createEngineFactory("CASUAL"));
        Transport.Engine.registerEngineFactory("ELECTRIC", Transport.createEngineFactory("ELECTRIC"));
        Transport.Engine.registerEngineFactory("HYBRID", Transport.createEngineFactory("HYBRID"));
        Transport.Engine.registerEngineFactory("NONE", Transport.createEngineFactory("NONE"));

        Transport car = new Car("Car", "ELECTRIC");
        car.displayInfo();

        Transport plane = new Plane("Plane", "ELECTRIC");
        plane.displayInfo();

        Transport ship = new Ship("Ship", "CASUAL");
        ship.displayInfo();

        Transport bicycle = new Bicycle("Bicycle", "NONE");
        bicycle.displayInfo();

        Transport limousine = new Limousine("Limousine", "HYBRID");
        limousine.displayInfo();

        Transport yacht = new Yacht("Yacht", "CASUAL");
        yacht.displayInfo();

        Transport privateJet = new PrivateJet("Private Jet", "HYBRID");
        privateJet.displayInfo();
    }

}
