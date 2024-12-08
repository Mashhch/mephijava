package hw6;

public class Currency {

    private final String code;    // Код валюты (например, USD)
    private final String name;    // Название валюты (например, доллар США)

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
