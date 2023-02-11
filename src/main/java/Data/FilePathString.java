package Data;

import java.io.IOException;

import static Utils.StringFromResource.generateStringFromResource;

public class FilePathString {
    public static String myRequest;
    //მთლიანი path-ის გარეშე ჩემთან არ მუშაობს, არც მეორე მეთოდით გამოდის
    static {
        try {
            myRequest = generateStringFromResource("C:\\Users\\I3 - 9100\\IdeaProjects\\restAssured-objectMapping\\src\\main\\resources\\findPerson.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
