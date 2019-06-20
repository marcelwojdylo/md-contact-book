package system;
import org.json.simple.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings("unchecked")

public class JSONController {
    
    public static JSONObject makeJSONFromContact (Contact contact) {

        JSONObject data = new JSONObject();
        data.put("contactID", Integer.toString(contact.getContactID()));
        data.put("firstName", contact.getFirstName());
        data.put("lastName", contact.getLastName());
        data.put("addressStreet", contact.getAddressStreet());
        data.put("addressHouse", contact.getAddressHouse());
        data.put("addressFlat", contact.getAddressFlat());
        data.put("addressPostcode", contact.getAddressPostcode());
        data.put("addressCity", contact.getAddressCity());
        data.put("addressCountry", contact.getAddressCountry());
        data.put("phoneNumber", contact.getPhoneNumber());
        data.put("email", contact.getEmail());
        data.put("dateOfBirth", contact.getDateOfBirth());


        return data;
    }

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
        try (FileWriter file = new FileWriter("contactBookData.json")) {
 
            file.write(json.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeJSON (JSONArray json) {
        try (FileWriter file = new FileWriter("contactBookData.json")) {
 
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
            jsonArray = (JSONArray) parser.parse(new FileReader("contactBookData.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static Contact[] makeContactArrayFromJSONArray(JSONArray array) {
        Contact[] contactArray = new Contact[ContactBook.getContactBookCapacity()];
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