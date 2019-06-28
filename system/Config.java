package system;
import java.time.LocalDate;

public class Config {
    public final static LocalDate CURRENT_DATE = LocalDate.now();
    public final static String STORAGE_PATH = "contactBookData.json";
    public final static String NEXT_ID_PATH = "nextID.json";
    public final static int CONTACT_BOOK_CAPACITY = 100;
}

