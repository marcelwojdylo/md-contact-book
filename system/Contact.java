package system;

import java.time.LocalDate;

public class Contact {

    private static int numberOfContacts = 0;
    private static final int DAY_OF_BIRTH_INDEX = 0;
    private static final int MONTH_OF_BIRTH_INDEX = 1;
    private static final int YEAR_OF_BIRTH_INDEX = 2;
    private ContactData contactData = new ContactData();



    public static int getNumberOfContacts () {
        return numberOfContacts;
    }  



    private class ContactData {

	    int contactID;
	    String firstName;
	    String lastName;
	    String addressStreet;
	    String addressHouse;
	    String addressFlat;
	    String addressPostcode; 
	    String addressCity;
	    String addressCountry;
	    String phoneNumber;
	    String email;
	    LocalDate dateOfBirth;

    }    

    public int getContactID() {
    	return contactData.contactID;
    }

    public String getFirstName() {
    	return contactData.firstName;
    }

    public String getLastName() {
    	return contactData.lastName;
    }

    public String getAddressStreet() {
    	return contactData.addressStreet;
    }

    public String getAddressHouse() {
    	return contactData.addressHouse;
    }

    public String getAddressFlat() {
    	return contactData.addressFlat;
    }

    public String getAddressPostcode() {
    	return contactData.addressPostcode;
    }

    public String getAddressCity() {
    	return contactData.addressCity;
    }

    public String getAddressCountry() {
    	return contactData.addressCountry;
    }

    public String getPhoneNumber() {
    	return contactData.phoneNumber;
    }

    public String getEmail() {
    	return contactData.email;
    }

    public LocalDate getDateOfBirth() {
    	return contactData.dateOfBirth;
    }



    public void setFirstName(String string) {
        contactData.firstName = string;
    }

    public void setLastName(String string) {
        contactData.lastName = string;
    }

    public void setAddressStreet(String string) {
        contactData.addressStreet = string;
    }

    public void setAddressHouse(String string) {
        contactData.addressStreet = string;
    }

    public void setAddressFlat(String string) {
        contactData.addressStreet = string;
    }

    public void setAddressPostcode(String string) {
        contactData.addressPostcode = string;
    }

    public void setAddressCity(String string) {
        contactData.addressCity = string;
    }

    public void setAddressCountry(String string) {
        contactData.addressCountry = string;
    }

    public void setPhoneNumber(String string) {
        contactData.phoneNumber = string;
    }

    public void setEmail(String string) {
        contactData.email = string;
    }

    public void setDateOfBirth(LocalDate date) {
        contactData.dateOfBirth = date;
    }





    public static class Builder {

        private final int contactID;

        private String firstName = "";
        private String lastName = "";
        private LocalDate dateOfBirth;
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
            firstName = value;
            return this;
        }

        public Builder lastName (String value) {
            lastName = value;
            return this;
        }

        public Builder dateOfBirth (LocalDate date) {
            dateOfBirth = date;
            return this;
        }

        public Builder addressStreet (String value) {
            addressStreet = value;
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
            addressCity = value;
            return this;
        }

        public Builder addressCountry (String value) {
            addressCountry = value;
            return this;
        }

        public Builder phoneNumber (String value) {
            phoneNumber = value;
            return this;
        }

        public Builder phoneNumber (int value) {
            String string = Integer.toString(value);
            phoneNumber = string;
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
        contactData.contactID = builder.contactID;
        contactData.firstName = builder.firstName;
        contactData.lastName = builder.lastName;
        contactData.dateOfBirth = builder.dateOfBirth;
        contactData.addressStreet = builder.addressStreet;
        contactData.addressHouse = builder.addressHouse;
        contactData.addressFlat = builder.addressFlat;
        contactData.addressPostcode = builder.addressPostcode; 
        contactData.addressCity = builder.addressCity;
        contactData.addressCountry = builder.addressCountry;
        contactData.phoneNumber = builder.phoneNumber;
        contactData.email = builder.email;
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

        return 
            "\nContact ID: " + contactData.contactID + ",\n" +
            "First Name: " + contactData.firstName + ",\n" +
            "Last Name: " + contactData.lastName + ",\n" +
            "Date of Birth: " + contactData.dateOfBirth + ",\n" +
            "Address:" + contactData.addressStreet + " " + contactData.addressHouse + "/" + contactData.addressFlat + ",\n" +
            "         " + contactData.addressPostcode + " " + contactData.addressCity + ", " + contactData.addressCountry + ",\n" +
            "Phone number: " + contactData.phoneNumber + ",\n" +
            "E-mail: " + contactData.email;
    }
}



