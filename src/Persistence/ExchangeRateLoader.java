package Persistence;

import Model.CurrencySet;
import Model.ExchangeRateSet;
import java.io.IOException;
import java.text.ParseException;

public interface ExchangeRateLoader {
    public ExchangeRateSet load(CurrencySet c) throws IOException, ParseException;
}
