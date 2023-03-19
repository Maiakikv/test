package Backend.Utils;

public class Endpoints {

    private static final String BOOK_STORE_API = "https://bookstore.toolsqa.com/";

    public static class Accounts {

        public static final String endpointAccounts = BOOK_STORE_API + "Account/v1/";

        public static String authorized = endpointAccounts + "Authorized";

        public static String generateToken = endpointAccounts + "GenerateToken";

        public static String user = endpointAccounts + "User";
    }

    public static class Books {
        public static final String endPointBooks = BOOK_STORE_API + "BookStore/v1/";

        public static String books = endPointBooks + "Books";
    }
}
