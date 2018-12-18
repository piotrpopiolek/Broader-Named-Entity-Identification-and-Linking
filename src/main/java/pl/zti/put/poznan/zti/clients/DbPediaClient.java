package pl.zti.put.poznan.zti.clients;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public abstract class DbPediaClient {

    private static final String URI = "https://api.dbpedia-spotlight.org/en/annotate";
    private static final String encoding = "UTF-8";

    public static String getResponse(String data) throws IOException {
        HttpGet get = null;
        try {
            URIBuilder builder = new URIBuilder(URI);
            builder.setParameter("text", data);
            get = new HttpGet(builder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse chr = null;
        try {
            chr =  (CloseableHttpResponse) httpClient.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = chr.getEntity();
        InputStream is = entity.getContent();
        String result = IOUtils.toString(is, encoding);
        return result;
    }
}
