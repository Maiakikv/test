package Data.Constants;
import org.apache.commons.lang3.RandomStringUtils;

public class ConstantData {
    public static final String name = RandomStringUtils.random(10, true, false),
            password = "Maiko123!",
            userNotFound = "User not found!",
            popupMessageUserDeleted = "User Deleted",
            errorText = "Invalid username or password";
}
