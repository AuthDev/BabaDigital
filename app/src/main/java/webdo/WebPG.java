package webdo;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.lang.Object;

/**
 * Created by Maxson Jordan on 21/01/2017.
 */

public class WebPG {


    public static String get(String urlEnter)
    {   URL url = null;
        String resposta = "";
        Log.d("Url texto = ",urlEnter);

        try {
            url = new URL(urlEnter);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            resposta  = getStringFromInputStream(in);
            urlConnection.disconnect();

        } catch (java.io.IOException e) {
            resposta = e.toString();
        }
        Log.d("Resposta do Server =", resposta);
        return  resposta;
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
        Log.d("Result: ",result);
        if(result.contains("on"))
        {
            return true;
        }
        else{
            return false;
        }
    }

}