//Liel van der Hoeven
package bitcoin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;

import org.json.JSONException;
import org.json.JSONObject;

public class BitCoin 
{

    public static void main(String[] args) throws IOException, JSONException 
    {
        while(true)
        {
            JSONObject json = readJsonFromUrl("https://www.bitstamp.net/api/v2/ticker/btcusd/");
            //System.out.println(json.toString());

            //Parsing "last" to be 2 decimal places
            DecimalFormat df = new DecimalFormat("#.00");
            double last = json.getDouble("last");
            String bitLast = df.format(last);
            System.out.println(bitLast);
            
            try 
            {
                Thread.sleep(10000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) 
            {
                Thread.currentThread().interrupt();
            }
        }
        
    }
    
    private static String readAll(Reader rd) throws IOException 
    {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) 
        {
          sb.append((char) cp);
        }
        return sb.toString();
    }
    
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException 
    {
        InputStream is = new URL(url).openStream();
        try 
        {
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
          JSONObject json = new JSONObject(jsonText);
          return json;
        } 
        finally 
        {
          is.close();
        }
    }
}