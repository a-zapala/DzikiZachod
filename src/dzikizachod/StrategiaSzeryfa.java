package dzikizachod;

import java.util.ArrayList;

/**
 * Created by Andrzej on 15.05.2017.
 */
public abstract class StrategiaSzeryfa extends Strategia {


    @Override
    public Ruch podajRuch(ArrayList<Akcja> akcje, Gracz gracz, ArrayList<Gracz> sasiedzi)
    {
        if(akcje.remove(Akcja.ULECZ))
        {
            return new Ruch(gracz,Akcja.ULECZ,gracz);
        }
    }
}
