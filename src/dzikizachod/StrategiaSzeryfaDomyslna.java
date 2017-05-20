package dzikizachod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class StrategiaSzeryfaDomyslna extends StrategiaSzeryfa
{
    @Override
    public Ruch podajRuchStrzelania(Stol stol)
    {
        List<Gracz> graczeWZasiegu = stol.podajGraczyWZasiegu(super.szeryf());
        List<Gracz> graczeKtorzyStrzeliliDoSzeryfa = super.szeryf().graczeKtorzyStrzeliliDoSzeryfa();


        if (graczeKtorzyStrzeliliDoSzeryfa.isEmpty()) //losujemy gracza w zasiegu
        {
            Gracz cel = super.szeryf().wylosujDoStrzalu(graczeWZasiegu);
            return new Ruch(Akcja.STRZEL,cel);
        }
        else
        {
            List<Gracz> graczeWZasieguKtorzyStrzeliliDoSzeryfa = new ArrayList<>();

            for (Gracz g : graczeWZasiegu)
            {
                if (graczeKtorzyStrzeliliDoSzeryfa.contains(g))
                {
                    graczeWZasieguKtorzyStrzeliliDoSzeryfa.add(g);
                }
            }
            if(graczeWZasieguKtorzyStrzeliliDoSzeryfa.isEmpty())
            {
                return new Ruch();
            }
            else
            {
                Gracz cel = super.szeryf().wylosujDoStrzalu(graczeWZasieguKtorzyStrzeliliDoSzeryfa);
                return new Ruch(Akcja.STRZEL, cel);
            }
        }

    }
}
