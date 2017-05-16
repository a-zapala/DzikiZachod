package dzikizachod;

import java.util.ArrayList;

/**
 * Created by Andrzej on 15.05.2017.
 */
public abstract class Strategia {

    public Ruch podajRuch(ArrayList<Akcja> akcje, Gracz gracz, ArrayList<Gracz> sasiedzi)
    {
        if(akcje.remove(Akcja.ZASIEG_PLUS_JEDEN))
        {
           return new Ruch(gracz,Akcja.ZASIEG_PLUS_JEDEN,gracz);
        }
        else if(akcje.remove(Akcja.ZASIEG_PLUS_DWA))
        {
            return new Ruch(gracz,Akcja.ZASIEG_PLUS_DWA,gracz);
        }

    }



}
