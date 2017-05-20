package dzikizachod;

import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class StrategiaPomocnikaSzeryfaZliczajaca extends StrategiaPomocnikaSzeryfa
{
    @Override
    public Ruch podajRuchStrzelania(Stol stol)
    {
        List<Gracz> graczeWZasiegu=stol.podajGraczyWZasiegu(super.pomocnikSzeryfa());
        List<Gracz> graczeKtorzyStrzeliliDoSzeryfa=((Szeryf) stol.szeryf()).graczeKtorzyStrzeliliDoSzeryfa();

        for(Gracz g:graczeWZasiegu)
        {
            if(graczeKtorzyStrzeliliDoSzeryfa.contains(g))
                return new Ruch(Akcja.STRZEL,g);
        }

        for(Gracz g: graczeWZasiegu)
        {
            if (g.bilansZabojstw()>0)
                return new Ruch(Akcja.STRZEL, g);
        }

        return new Ruch();
    }
}
