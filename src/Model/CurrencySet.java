package Model;

import java.util.ArrayList;
import java.util.List;

public class CurrencySet {

    private final ArrayList<Currency> list;
    private static CurrencySet currencySet;
    
    private CurrencySet() {
        
        this.list = new ArrayList<>();
        
    }
    
    public static CurrencySet getInstance() {
        
        if (currencySet == null) {
            CurrencySet.currencySet = new CurrencySet();
        }
        
        return currencySet;
        
    }
  

    public boolean add(Currency currency) {
        
        for (Currency currency1 : list) {
            if (currency1.equals(currency)) return false;
        }
        
        list.add(currency);
        
        return true;
        
    }

    public Currency get(int index) {
        return list.get(index);
    }
    
    public int size() {
        return list.size();
    }

    public ArrayList<Currency> getList() {
        return list;
    }
    
}
