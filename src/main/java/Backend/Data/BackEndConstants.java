package Backend.Data;
import org.apache.commons.lang3.RandomStringUtils;

public class BackEndConstants {
    public static final String userName = RandomStringUtils.random(10, true, false),
            password = "Maiko123!",
            authorizedApiUserNotFound = "User not found!";
}
