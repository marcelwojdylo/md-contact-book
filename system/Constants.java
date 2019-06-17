package system;
import java.time.*;

public class Constants {
    public final static String DIGITS = "1234567890";
    public final static String LETTERS = "aąbcćdeęfghijklłmnoópqrsśtuvwxyzżźAĄBCĆDEĘFGHIJKLŁMNOÓPQRSŚTUVWXYZŻŹ";
    public final static String EMAIL_CHECK_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    public final static String POSTCODE_CHECK_REGEX = "^[\\d][\\d]\\-[\\d][\\d][\\d]";
    public final static String DATE_OF_BIRTH_CHECK_REGEX = "^[\\d][\\d][\\d][\\d]\\-[\\d][\\d]\\-[\\d][\\d]";
    public final static int[] MONTH_LENGTHS = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public final static int MONTHS_IN_A_YEAR = 12;
    public final static LocalDate CURRENT_DATE = LocalDate.now();
}

