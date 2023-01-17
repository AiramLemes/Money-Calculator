package Persistence.Archive;

import Model.Currency;
import Model.CurrencySet;
import Persistence.CurrencyLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CurrencyLoaderArchive implements CurrencyLoader{
    private final String filePathName;

    public CurrencyLoaderArchive(String filePathName) {
        this.filePathName = filePathName;
    }
    
    private Currency currencyOf(String lineText) {
        String[] splitedLine = lineText.split(",");
        return new Currency(splitedLine[0], splitedLine[1], splitedLine[2]);
    }
    
    @Override
    public CurrencySet loadAllCurrencies() {
        CurrencySet currencySet = CurrencySet.getInstance();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.filePathName)));
            while (true) {
                String lineText = reader.readLine();
                if (lineText == null) break;
                currencySet.add(currencyOf(lineText));
            }
        }
        catch (FileNotFoundException exception ) {
            System.out.println("ERROR CurrencyLoaderArchive::loadAllCurrencies FileNotFoundException, " + exception.getMessage());
        }
        catch (IOException exception) {
            System.out.println("ERROR CurrencyLoaderArchive::loadAllCurrencies IOException, " + exception.getMessage());        
        }
        return currencySet;
    }

    
}
