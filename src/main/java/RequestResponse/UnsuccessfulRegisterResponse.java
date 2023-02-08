package RequestResponse;

public class UnsuccessfulRegisterResponse {
    private String error;

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "UnsuccessfulRegisterResponse{" +
                "error='" + error + '\'' +
                '}';
    }
}
