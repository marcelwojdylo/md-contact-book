package system;
import org.json.simple.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONController {

    @SuppressWarnings("unchecked")
    public static JSONObject makeJSONFromContact (Contact contact) {
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contactID", Integer.toString(contact.getContactID()));
        jsonObject.put("firstName", contact.getFirstName());
        jsonObject.put("lastName", contact.getLastName());
        jsonObject.put("addressStreet", contact.getAddressStreet());
        jsonObject.put("addressHouse", contact.getAddressHouse());
        jsonObject.put("addressFlat", contact.getAddressFlat());
        jsonObject.put("addressPostcode", contact.getAddressPostcode());
        jsonObject.put("addressCity", contact.getAddressCity());
        jsonObject.put("addressCountry", contact.getAddressCountry());
        jsonObject.put("phoneNumber", contact.getPhoneNumber());
        jsonObject.put("email", contact.getEmail());
        jsonObject.put("dateOfBirth", contact.getDateOfBirth());

        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public static JSONArray makeJSONFromContactBook (ContactBook contactBook) {
        JSONArray array = new JSONArray();
        for (int i = 0; i < contactBook.getContactBookCapacity(); i++) {
            if (contactBook.getContacts()[i] != null) {
                array.add(makeJSONFromContact(contactBook.getContacts()[i]));
            }
        }
        return array;
    }

    public static void writeJSON (JSONObject json) {
        try (FileWriter file = new FileWriter(Config.STORAGE_PATH)) {
 
            file.write(json.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeJSON (JSONArray json) {
        try (FileWriter file = new FileWriter(Config.STORAGE_PATH)) {
 
            file.write(json.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Contact makeContactFromJSON (JSONObject json) {
        return new Contact.Builder()
            .contactID(Integer.parseInt((String) json.get("contactID")))
            .lastName((String) json.get("lastName"))
            .firstName((String) json.get("firstName"))
            .phoneNumber((String) json.get("phoneNumber"))
            .dateOfBirth((String) json.get("dateOfBirth"))
            .addressStreet((String) json.get("addressStreet"))
            .addressHouse((String) json.get("addressHouse"))
            .addressFlat((String) json.get("addressFlat"))
            .addressPostcode((String) json.get("addressPostcode"))
            .addressCity((String) json.get("addressCity"))
            .addressCountry((String) json.get("addressCountry"))
            .email((String) json.get("email"))
            .build();
    }

    public static JSONArray readJSONArrayFromFile() {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(Config.STORAGE_PATH));
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load contact book from JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static Contact[] makeContactArrayFromJSONArray(JSONArray array) {
        Contact[] contactArray = new Contact[Config.CONTACT_BOOK_CAPACITY];
        boolean finished = false;
        int i = 0;
        while (!finished) {
            try {
                JSONObject contactJSON = (JSONObject) array.get(i);
                Contact contactFromJSON = JSONController.makeContactFromJSON(contactJSON);
                contactArray[i] = contactFromJSON;
                i++;
            } catch (IndexOutOfBoundsException e) {
                finished = true;
            }
        }
        return contactArray;
    }
}