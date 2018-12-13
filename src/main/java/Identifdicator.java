import clients.DbPediaClient;
import conventers.Converter;

import java.util.List;

public class Identifdicator {
    public static String identificate(String input) {
        try {
            List<String> data = StringReader.getStringFromData(input);
            String result = DbPediaClient.getResponse(data.get(1));
            List<String> results = Converter.convertToRdf(data.get(0), result);
            return String.join("\n", results);

        } catch (Exception e) {
            return "";
        }
    }
}