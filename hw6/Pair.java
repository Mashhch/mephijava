package hw6;

public class Pair<T, U> {

    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "{" + first +
                ", " + second +
                '}';
    }

    @Override
    public boolean equals(Object anotherPair) {
        if (this == anotherPair) return true;
        if (anotherPair == null || getClass() != anotherPair.getClass()) return false;
        return first.equals(((Pair<?, ?>) anotherPair).first) && second.equals(((Pair<?, ?>) anotherPair).second);
    }

    // используется 31 по классике, т.к. это простое + нечетное число, дешевая операция сдвига для машинного кода.
    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        return result;
    }

//    public static void main(String[] args) {
//        Pair<String, Integer> pair1 = new Pair<>("a", 1);
//        Pair<String, Integer> pair2 = new Pair<>("a",1);
//        System.out.println(pair1.equals(pair2));
//    }
}