package hw4;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(StringBuilder stringBuilder, String nameObservable);
}

interface Observable {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}

class StringBuilderObserver implements Observer {
    private final String nameObserver;

    public StringBuilderObserver(String name, Observable o) {
        this.nameObserver = name;
        o.registerObserver(this);
    }

    public StringBuilderObserver(String name) {
        this.nameObserver = name;
    }

    @Override
    public void update(StringBuilder stringBuilder, String nameObservable) {
        System.out.println("Имя наблюдателя: " + nameObserver + " Имя того, за кем наблюдают: " + nameObservable + " Строка изменилась: " + stringBuilder);
    }
}


class StringBuilderObservable implements Observable {
    private List<Observer> observers = new ArrayList<Observer>();
    private final StringBuilder stringBuilder;
    private final String nameObservable;

    public StringBuilderObservable(String nameObservable) {
        this.stringBuilder = new StringBuilder();
        this.nameObservable = nameObservable;
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers)
            o.update(stringBuilder, nameObservable);
    }

    public void append(String str) {
        stringBuilder.append(str);
        notifyObservers();
    }

    public void delete(int start, int end) {
        stringBuilder.delete(start, end);
        notifyObservers();
    }

    public void insert(int offset, String str) {
        stringBuilder.insert(offset, str);
        notifyObservers();
    }

    public void replace(int start, int end, String str) {
        stringBuilder.replace(start, end, str);
        notifyObservers();
    }

    public String toString() {
        return stringBuilder.toString();
    }
}

class Main {
    public static void main(String[] args) {
        StringBuilderObservable builder1 = new StringBuilderObservable("first");
        StringBuilderObservable builder2 = new StringBuilderObservable("second");

        StringBuilderObserver observer1 = new StringBuilderObserver("Fedor", builder1);
        StringBuilderObserver observer2 = new StringBuilderObserver("Nikolya");
        builder2.registerObserver(observer1);
        builder1.registerObserver(observer2);
        builder1.append("1234567");
        builder2.append("89101112");
        builder1.delete(5, 6);
        builder1.removeObserver(observer1);
        builder1.insert(5, " 55555");
    }
}

/* Вывод
Имя наблюдателя: Fedor Имя того, за кем наблюдают: first Строка изменилась: 1234567
Имя наблюдателя: Nikolya Имя того, за кем наблюдают: first Строка изменилась: 1234567
Имя наблюдателя: Fedor Имя того, за кем наблюдают: second Строка изменилась: 89101112
Имя наблюдателя: Fedor Имя того, за кем наблюдают: first Строка изменилась: 123457
Имя наблюдателя: Nikolya Имя того, за кем наблюдают: first Строка изменилась: 123457
Имя наблюдателя: Nikolya Имя того, за кем наблюдают: first Строка изменилась: 12345 555557

 */