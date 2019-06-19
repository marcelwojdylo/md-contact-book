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

        try (FileWriter file = new FileWriter("obj.json")) {
 
            file.write(contactBook.getJSON().toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("obj.json"));
            JSONArray jsonObject = (JSONArray) obj;
            System.out.println(jsonObject.get(0));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}




























































