package Persistence.WebService;

import Model.Currency;
import Model.CurrencySet;
import Model.ExchangeRate;
import Model.ExchangeRateSet;
import Persistence.ExchangeRateLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import org.json.JSONObject;

public class ExchangeRateLoaderWebService implements ExchangeRateLoader {

    private final String URL;

    public ExchangeRateLoaderWebService(String URL) {
        this.URL = URL;
    }

    
    private ExchangeRate get(Currency from, Currency to) {
        String url = String.format(URL, from.getCode().toLowerCase(), to.getCode().toLowerCase());
        
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return new ExchangeRate(json.getDouble(to.getCode().toLowerCase()), to, from);
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to read Json from URL", ex);
        }
    }
    
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }

        return sb.toString();
    }
    
    
    @Override
    public ExchangeRateSet load(CurrencySet c) throws IOException, ParseException {
        ExchangeRateSet set = new ExchangeRateSet();
        for (int i = 0; i < c.size(); i++) {
            for (int j = 0; j < c.size(); j++) {
                if (c.get(i).equals(c.get(j))) {
                    set.add(new ExchangeRate(1, c.get(i), c.get(i)));
                } else {
                    
                    set.add(get(c.get(i), c.get(j)));
                    
                }
            }
        }

        return set;
  
    }
}
