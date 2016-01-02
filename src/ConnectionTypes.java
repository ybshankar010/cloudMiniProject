
// Stores the types of connection
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConnectionTypes {

    public static void getTypes(String file) {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj;

            // loop array to populate the hashmap of node info.

            JSONArray msg = (JSONArray) jsonObject.get("types");

            NodeInfoClass temp;
            JSONObject jsonTemp;
            for (int i = 0; i < msg.size(); i++) {
                jsonTemp = (JSONObject) msg.get(i);
                temp = new NodeInfoClass();
                temp.setTid((Long) jsonTemp.get("tid"));
                temp.setCpu((Long) jsonTemp.get("cpu"));
                temp.setRam((Long) jsonTemp.get("ram"));
                temp.setDisk((Long) jsonTemp.get("disk"));
                Helper.connectionTypes.put(temp.getTid(), temp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
