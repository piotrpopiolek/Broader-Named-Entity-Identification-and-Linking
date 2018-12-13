package conventers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {
    private static String link;

    private static final String template = "<<pre_linking>>\n" +
            "        a                     nif:RFC5147String , nif:String , nif:Phrase ;\n" +
            "        nif:anchorOf          \"<pre_anchorof>\"^^xsd:string ;\n" +
            "        nif:beginIndex        \"<pre_beginIndex>\"^^xsd:nonNegativeInteger ;\n" +
            "        nif:endIndex          \"<pre_endIndex>\"^^xsd:nonNegativeInteger ;\n" +
            "        nif:referenceContext  <<pre_referenceContext>> ;\n" +
            "        itsrdf:taIdentRef     <<pre_taIdentRef>> .\n";

    public static List<String> convertToRdf(String input, String data) {
        link = getLink(input);
        data = concateData(data);
        Map<String, List<String>> hrefs = new HashMap<>();
        Pattern p = Pattern.compile("(<a href=\"(.*?)</a>)");
        Matcher m = p.matcher(data);
        List<String> finds = new ArrayList<>();
        while (m.find()) {
            finds.add(m.group(0));
        }

        for (String find : finds) {
            String url = getUrl(find);
            String name = getObjectName(find);
            int firstIndex = data.indexOf(find);
            int lastIndex = firstIndex + name.length();
            data = data.replaceFirst(find, name);
            String individualLink = getIndividialLink(firstIndex, lastIndex);

            List<String> params = new ArrayList<>();
            params.add(url);
            params.add(Integer.toString(firstIndex));
            params.add(Integer.toString(lastIndex));
            params.add(individualLink);
            hrefs.put(name, params);
        }

        List<String> templates = new ArrayList<>();
        templates.add(input);
        for (Map.Entry<String, List<String>> entry : hrefs.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            String result = prepereTemplate(key, values);
            templates.add(result);
        }

        return templates;
    }

    private static String getLink(String link) {
        Pattern p = Pattern.compile("<http://(.*?)>");
        Matcher m = p.matcher(link);
        m.find();
        String result = m.group(0);
        return result;
    }

    private static String concateData(String data) {
        Pattern p = Pattern.compile("(<div>\n(.*?)\n</div>)");
        Matcher m = p.matcher(data);
        m.find();
        String concated = m.group(0);
        concated = concated.substring(6, concated.length() - 7);
        return concated;
    }

    private static String getUrl(String data) {
        Pattern p = Pattern.compile("(http://(.*?)\")");
        Matcher m = p.matcher(data);
        m.find();
        String url = m.group(0);
        url = url.substring(0, url.length() - 1);
        return url;
    }

    private static String getObjectName(String data) {
        Pattern p = Pattern.compile("(>(.*?)</a>)");
        Matcher m = p.matcher(data);
        m.find();
        String name = m.group(0);
        name = name.substring(1, name.length() - 4);
        return name;
    }

    private static String getIndividialLink(int firstIndex, int lastIndex) {
        Pattern p = Pattern.compile("(http://(.*?)char=)");
        Matcher m = p.matcher(link);
        m.find();
        String individualLink = m.group(0);
        individualLink += firstIndex + "," + lastIndex;

        return individualLink;
    }

    private static String prepereTemplate(String key, List<String> values) {
        String tempTemplate = template;
        tempTemplate = replaceTag("<pre_anchorof>", key, tempTemplate);
        tempTemplate = replaceTag("<pre_taIdentRef>", values.get(0), tempTemplate);
        tempTemplate = replaceTag("<pre_beginIndex>", values.get(1), tempTemplate);
        tempTemplate = replaceTag("<pre_endIndex>", values.get(2), tempTemplate);
        tempTemplate = replaceTag("<pre_referenceContext>", values.get(3), tempTemplate);
        tempTemplate = replaceTag("<<pre_linking>>", link, tempTemplate);
        return tempTemplate;
    }

    private static String replaceTag(String tag, String replacement, String template) {
        template = template.replaceAll(tag, replacement);
        return template;
    }
}
