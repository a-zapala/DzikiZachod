package dzikizachod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class StrategiaSzeryfaZliczajaca extends StrategiaSzeryfa
{
    @Override
    public Ruch podajRuchStrzelania(Stol stol)
    {
        List<Gracz> graczeWZasiegu = stol.podajGraczyWZasiegu(super.szeryf());
        List<Gracz> graczeKtorzyStrzeliliDoSzeryfa = super.szeryf().graczeKtorzyStrzeliliDoSzeryfa();

        if(!graczeKtorzyStrzeliliDoSzeryfa.isEmpty())
        {
            List<Gracz> graczeWZasieguKtorzyStrzeliliDoSzeryfa = new ArrayList<>();

            for (Gracz g : graczeWZasiegu)
            {
                if (graczeKtorzyStrzeliliDoSzeryfa.contains(g))
                {
                    graczeWZasieguKtorzyStrzeliliDoSzeryfa.add(g);
                }
            }
            if(!graczeKtorzyStrzeliliDoSzeryfa.isEmpty())
            {
                Gracz cel = super.szeryf().wylosujDoStrzalu(graczeWZasieguKtorzyStrzeliliDoSzeryfa);
                return new Ruch(Akcja.STRZEL, cel);
            }
        }

        for(Gracz g:graczeWZasiegu)
        {
            if(g.bilansZabojstw()>0)
            {
                return new Ruch(Akcja.STRZEL,g);
            }
        }

        return new Ruch();
    }
}
