package system;


class ContactDataFormatter {








    public static String formatPhoneNumber(String phoneNumber) {
        String formattedNumber = removeNonDigits(phoneNumber);
        return formattedNumber;
    }

    public static String formatPhoneNumber(int phoneNumber) {
        String formattedNumber = Integer.toString(phoneNumber);
        return formattedNumber;
    }


    
    private static String removeNonDigits(String number) {
        String result = "";
        for (int i = 0; i < number.length(); i++) {
            if(isDigit(number.charAt(i))) {
                result += number.charAt(i);
            }
        }
        return result;
    }

    private static String charRemoveAt(String string, int index) {
        return string.substring(0, index) + string.substring(index);
    }

    private static boolean isDigit(char character) {
        for (int i = 0; i < Constants.DIGITS.length(); i++) {
            if (character == Constants.DIGITS.charAt(i)) {
                return true;
            }
        }
        return false;
    }




    public static String formatProperName(String string) {
        String formattedProperName = removeNonLetters(string);
        formattedProperName = capitaliseString(formattedProperName);
        return formattedProperName;
    }


    private static String removeNonLetters(String string) {
        String result = "";
        for (int i = 0; i < string.length(); i++) {
            if (isLetter(string.charAt(i))) {
                result += string.charAt(i);
            }
        }
        return result;
    }

    private static boolean isLetter(char character) {
        for (int i = 0; i < Constants.LETTERS.length(); i++) {
            if (character == Constants.LETTERS.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static String capitaliseString(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
    }

}





