package Enums;

public enum EndPoint {
    BASEURL ("https://bookstore.toolsqa.com");

    private final String value;

    EndPoint(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
