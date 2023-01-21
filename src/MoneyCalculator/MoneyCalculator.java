package MoneyCalculator;

import Persistence.Archive.CurrencyLoaderArchive;
import Persistence.CurrencyLoader;
import Persistence.WebService.ExchangeRateLoaderWebService;
import View.MoneyCalculatorApp;
import java.io.IOException;
import java.text.ParseException;

public class MoneyCalculator {

    public static void main(String[] args) throws IOException, ParseException {
        
        CurrencyLoader currencyLoader = new CurrencyLoaderArchive("./currencies.txt");
       
        ExchangeRateLoaderWebService loader;
        loader = new ExchangeRateLoaderWebService("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/%s/%s.json");
        
        new MoneyCalculatorApp(currencyLoader.loadAllCurrencies(), 
            loader.load(currencyLoader.loadAllCurrencies()));
       
        
        
    }
    
}
