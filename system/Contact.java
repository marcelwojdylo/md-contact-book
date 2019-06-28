package system;

public class Contact {

    private static int nextID = 0;
    public static int getNextID() {
        return nextID;
    }

    private ContactData contactData = new ContactData();
    private class ContactData {

        int contactID;
        String contactLabel;
	    String firstName;
	    String lastName;
        String addressLine1;
        String addressLine2;
	    String phoneNumber;
	    String email;
	    String dateOfBirth;

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

    public String getAddressLine1() {
    	return contactData.addressLine1;
    }

    public String getAddressLine2() {
    	return contactData.addressLine2;
    }

    public String getPhoneNumber() {
    	return contactData.phoneNumber;
    }

    public String getEmail() {
    	return contactData.email;
    }

    public String getDateOfBirth() {
    	return contactData.dateOfBirth;
    }

    public String getContactLabel() {
        return contactData.contactLabel;
    }



    public void setFirstName(String string) {
        contactData.firstName = string;
    }

    public void setLastName(String string) {
        contactData.lastName = string;
    }

    public void setAddressLine1(String string) {
        contactData.addressLine1 = string;
    }

    public void setAddressLine2(String string) {
        contactData.addressLine2 = string;
    }

    public void setPhoneNumber(String string) {
        contactData.phoneNumber = string;
    }

    public void setEmail(String string) {
        contactData.email = string;
    }

    public void setDateOfBirth(String date) {
        contactData.dateOfBirth = date;
    }

    public void updateContactLabel() {
        contactData.contactLabel = contactData.firstName + " " + contactData.lastName;
    }





    public static class Builder {

        private int contactID;
        private String firstName = "";
        private String lastName = "";
        private String dateOfBirth;
        private String addressLine1 = "";
        private String addressLine2 = "";
        private String phoneNumber = "";
        private String email = "";

        public Builder () {
            contactID = Contact.nextID;
            Contact.nextID++;
        }

        public Builder contactID (int value) {
            contactID = value;
            if (contactID > nextID) {
                nextID = contactID + 1;
            }
            return this;
        }

        public Builder firstName (String value) {
            firstName = value;
            return this;
        }

        public Builder lastName (String value) {
            lastName = value;
            return this;
        }

        public Builder dateOfBirth (String date) {
            dateOfBirth = date;
            return this;
        }

        public Builder addressLine1 (String value) {
            addressLine1 = value;
            return this;
        }

        public Builder addressLine2 (String value) {
            addressLine2 = value;
            return this;
        }

        public Builder phoneNumber (String value) {
            phoneNumber = value;
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
        contactData.phoneNumber = builder.phoneNumber;
        contactData.addressLine1 = builder.addressLine1;
        contactData.addressLine2 = builder.addressLine2;
        contactData.email = builder.email;
        contactData.contactLabel = builder.firstName + " " + builder.lastName;
    }  

    public String toString() {
        return contactData.contactLabel;
    }
}



