package system;
import java.time.LocalDate;

public class Config {
    public final static LocalDate CURRENT_DATE = LocalDate.now();
    public final static String STORAGE_PATH = "contactBookData.json";
    public final static String NEXT_ID_PATH = "nextID.json";
    public final static int CONTACT_BOOK_CAPACITY = 100;

    public class Constants {
        public final static String DIGITS = "1234567890";
        public final static String LETTERS = "AaĄąBbCcĆćDdEeĘęFfGgHhIiJjKkLlŁłMmNnOoÓóPpQqRrSsŚśTtUuVvWwXxYyZzŹżŻź";
        public final static String EMAIL_CHECK_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        public final static String POSTCODE_CHECK_REGEX = "^[\\d][\\d]\\-[\\d][\\d][\\d]";
        public final static String DATE_OF_BIRTH_CHECK_REGEX = "^[\\d][\\d][\\d][\\d]\\-[\\d][\\d]\\-[\\d][\\d]";
    }


}

