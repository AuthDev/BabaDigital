package bbgetset;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import webdo.WebPG;

/**
 * Created by evar on 22/03/17.
 */

public class BDFabGet {

    private String url = "http://clinica28dejulho.com.br/app/babadigital/";
    private String php;

    public String sentGet(String action, String valorGet)
    {
        String result  = "Sem retorno";
        if(action.length() > 1)
        {
            WebPG webPG = new WebPG();
            valorGet = valorGet.replace(" ","%");
            result = webPG.get(url+action+".php?"+valorGet);
            return result;
        }
        return result;

    }
    public void setPost()
    {

    }
    public void setURL(String url)
    {
        if(url.endsWith("/"))
        {
            this.url = url;
        }
        else{
            this.url = url+"/";
        }
    }

    public String talk(String action, String[] Params)
    {
        String parametodo = "http://clinica28dejulho.com.br/app/babadigital/"+action+".php?";
        for (int i = 0;i < Params.length;i++)
        {
            parametodo += Params[i]+"="+Params[i+1];
            i++;
            if(i<Params.length-1)
            {
                parametodo+="&";
            }
        }
        String resposta = "";
        URL url = null;
        try {
            url = new URL(parametodo);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            resposta  = getStringFromInputStream(in);

        } catch (java.io.IOException e) {
            resposta = e.toString();
        }

        return  resposta;
    }
    public String getStringFromInputStream(InputStream stream) throws IOException
    {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, "UTF8");
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }


}
