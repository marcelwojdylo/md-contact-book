import system.*;
import commandlineinterface.*;
import gui.*;

import org.json.simple.*;
import java.io.FileWriter;
import java.io.IOException;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings("unchecked")

public class ContactBookApp {
    public static void main(String[] arg) {

        ContactBook contactBook = new ContactBook();
        JSONController.writeJSON(JSONController.makeJSONFromContactBook(contactBook));

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("contactBookData.json"));
            System.out.println(obj);
            JSONArray jsonArray = (JSONArray) obj;
            System.out.println(jsonArray.get(0));
            JSONObject contact = (JSONObject) jsonArray.get(0);
            System.out.println(contact.get("firstName"));
            String firstname = (String) contact.get("firstName");
            System.out.println(firstname);
            Contact contactFromJSON = JSONController.makeContactFromJSON(contact);
            System.out.println(contactFromJSON);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}




























































