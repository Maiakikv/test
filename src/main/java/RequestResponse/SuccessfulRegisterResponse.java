package RequestResponse;

public class SuccessfulRegisterResponse {
    private int id;
    private String token;

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "SuccessfulRegisterResponse{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }
}
