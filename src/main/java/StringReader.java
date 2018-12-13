import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReader {
    public static List<String> getStringFromData(String data) {
        List<String> sentences = new ArrayList<>();
        sentences.add(data);
        Pattern p = Pattern.compile("(?<=nif:isString(    )\")(.*?)(?=\"( *)\\^\\^xsd:string)");
        Matcher m = p.matcher(data);
        while (m.find()) {
            sentences.add(m.group(0));
            break;
        }

        return sentences;
    }

}
