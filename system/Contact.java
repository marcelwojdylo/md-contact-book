package system;


class Contact {

    private static int numberOfContacts = 0;
    private final int contactID;
    public final String firstName;
    public final String lastName;
    public final String addressStreet;
    public final String addressHouse;
    public final String addressFlat;
    public final String addressPostcode; 
    public final String addressCity;
    public final String addressCountry;
    public final String phoneNumber;
    public final String email;
    public final int[] dateOfBirth;
    private static final int DAY_OF_BIRTH_INDEX = 0;
    private static final int MONTH_OF_BIRTH_INDEX = 1;
    private static final int YEAR_OF_BIRTH_INDEX = 2;



    public static int getNumberOfContacts () {
        return numberOfContacts;
    }

    public int getContactID() {
        return contactID;
    }



    public static class Builder {

        private final int contactID;

        private String firstName = "";
        private String lastName = "";
        private int[] dateOfBirth = new int[3];
        private String addressStreet = "";
        private String addressHouse = "";
        private String addressFlat = "";
        private String addressPostcode = "";
        private String addressCity = "";
        private String addressCountry = "";
        private String phoneNumber = "";
        private String email = "";

        public Builder () {
            contactID = Contact.numberOfContacts;
            Contact.numberOfContacts++;
        }

        public Builder firstName (String value) {
            firstName = ContactDataFormatter.formatProperName(value);
            return this;
        }

        public Builder lastName (String value) {
            lastName = ContactDataFormatter.formatProperName(value);
            return this;
        }

        public Builder dateOfBirth (int day, int month, int year) {
            dateOfBirth[DAY_OF_BIRTH_INDEX] = day;
            dateOfBirth[MONTH_OF_BIRTH_INDEX] = month;
            dateOfBirth[YEAR_OF_BIRTH_INDEX] = year;
            return this;
        }

        public Builder addressStreet (String value) {
            addressStreet = ContactDataFormatter.formatProperName(value);
            return this;
        }

        public Builder addressHouse (String value) {
            addressHouse = value;
            return this;
        }

        public Builder addressHouse (int value) {
            String string = Integer.toString(value);
            addressHouse = string;
            return this;
        }

        public Builder addressFlat (String value) {
            addressFlat = value;
            return this;
        }

        public Builder addressFlat (int value) {
            String string = Integer.toString(value);
            addressFlat = string;
            return this;
        }

        public Builder addressPostcode (String value) {
            addressPostcode = value;
            return this;
        }

        public Builder addressCity (String value) {
            addressCity = ContactDataFormatter.formatProperName(value);
            return this;
        }

        public Builder addressCountry (String value) {
            addressCountry = ContactDataFormatter.formatProperName(value);
            return this;
        }

        public Builder phoneNumber (String value) {
            phoneNumber = ContactDataFormatter.formatPhoneNumber(value);
            return this;
        }

        public Builder phoneNumber (int value) {
            String string = Integer.toString(value);
            phoneNumber = ContactDataFormatter.formatPhoneNumber(string);
            return this;
        }
        

        public Builder email (String value) {
            email = value;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }

    private Contact (Builder builder) {
        contactID = builder.contactID;
        firstName = builder.firstName;
        lastName = builder.lastName;
        dateOfBirth = builder.dateOfBirth;
        addressStreet = builder.addressStreet;
        addressHouse = builder.addressHouse;
        addressFlat = builder.addressFlat;
        addressPostcode = builder.addressPostcode; 
        addressCity = builder.addressCity;
        addressCountry = builder.addressCountry;
        phoneNumber = builder.phoneNumber;
        email = builder.email;
    }



    private static boolean contains(final int[] array, final int v) {
        boolean result = false;
        for (int i : array) {
            if (i == v) {
                result = true;
                break;
            }
        }
        return result;
    }



    public String toString() {
        String result = "";


        return 
            "Contact ID: " + contactID + ",\n" +
            "First Name: " + firstName + ",\n" +
            "Last Name: " + lastName + ",\n" +
            "Date of Birth: " + dateOfBirth[DAY_OF_BIRTH_INDEX] + "/" + dateOfBirth[MONTH_OF_BIRTH_INDEX] + "/" + dateOfBirth[YEAR_OF_BIRTH_INDEX] + ",\n" +
            "Address:" + addressStreet + " " + addressHouse + "/" + addressFlat + ",\n" +
            "         " + addressPostcode + " " + addressCity + ", " + addressCountry + ",\n" +
            "Phone number: " + phoneNumber + ",\n" +
            "E-mail: " + email + "\n";
    }
}



