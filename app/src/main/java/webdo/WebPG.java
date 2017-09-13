package webdo;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;
import java.lang.Object;

/**
 * Created by Maxson Jordan on 21/01/2017.
 */

public class WebPG {


    public static String get(String urlEnter)
    {   URL url = null;
        String resposta = "";

        try {
            url = new URL(urlEnter);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            resposta  = getStringFromInputStream(in);
            urlConnection.disconnect();

        } catch (java.io.IOException e) {
            resposta = e.toString();
        }
        return  resposta;

    }
    public static String getEcoded(String urlEnter)
    {   URL url = null;
        String resposta = "";

        try {
           urlEnter = URLEncoder.encode(urlEnter, java.nio.charset.StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        try {
            url = new URL(urlEnter);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            resposta  = getStringFromInputStream(in);
            urlConnection.disconnect();

        } catch (java.io.IOException e) {
            resposta = e.toString();
        }
        return  resposta;

    }

    public static String post(String açao, List<NameValuePair> nameValuePairs) throws IOException {
        String result = "fail";
        String url = "http://clinica28dejulho.com.br/app/babadigital/"+açao+".php";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
        HttpResponse httpResponse = httpClient.execute(httpPost);


        result = getStringFromInputStream(httpResponse.getEntity().getContent());

        return result;
    }

    private static String getStringFromInputStream(InputStream stream) throws IOException
    {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, "UTF8");
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }

    public static boolean isConnected()
    {
        String result =  get("http://clinica28dejulho.com.br/app/babadigital/status.php");
        if(result.contains("{\"status\":\"Online\",\"server\":\"ok\"}"))
        {
            return true;
        }
        else{
            return false;
        }
    }

}