package dzikizachod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class StrategiaPomocnikaSzeryfaDomyslna extends StrategiaPomocnikaSzeryfa
{
    @Override
    protected Ruch podajRuchStrzelania(Stol stol)
    {
        List<Gracz> graczeWZasiegu=stol.podajGraczyWZasiegu(super.pomocnikSzeryfa());

        if(!graczeWZasiegu.isEmpty())
        {
            Gracz cel = super.pomocnikSzeryfa().wylosujDoStrzalu(graczeWZasiegu);
            return new Ruch(Akcja.STRZEL,cel);
        }

        for(Gracz g: graczeWZasiegu)
        {
            if(g.czyJestemSzeryfem())
            {
                graczeWZasiegu.remove(g);
            }
        }
        return new Ruch();
    }
}
