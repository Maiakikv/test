package Data.Constants;
import org.apache.commons.lang3.RandomStringUtils;

public class ConstantData {
    public static final String userName = RandomStringUtils.random(10, true, false),
            password = "Maiko123!",
            authorizedApiUserNotFound = "User not found!",
            popupMessageUserDeleted = "User Deleted.",
            uiLoginErrorMessage = "Invalid username or password";
}
