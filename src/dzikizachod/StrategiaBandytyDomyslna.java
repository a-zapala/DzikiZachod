package dzikizachod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class StrategiaBandytyDomyslna extends StrategiaBandyty
{
    @Override
    public Ruch podajRuchStrzelania(Stol stol)
    {
        Ruch strzelDoSzeryfa = super.podajRuchStrzelania(stol);

        if (!strzelDoSzeryfa.czyPusty())
        {
            return strzelDoSzeryfa;
        }

        List<Gracz> graczeWZasieguSkracajacyDystansDoSzeryfa = stol.podajGraczyWZasieguSkracajacyDystansDoSzeryfa(super.bandyta());

        List<Gracz> graczeWZasieguNieBedacyBandytami = new ArrayList<>();

        for (Gracz g : graczeWZasieguSkracajacyDystansDoSzeryfa)
        {
            if (!super.bandyta().bandyci().contains(g))
            {
                graczeWZasieguNieBedacyBandytami.add(g);
            }
        }

        if (!graczeWZasieguNieBedacyBandytami.isEmpty())
        {
            Gracz cel = super.bandyta().wylosujDoStrzalu(graczeWZasieguNieBedacyBandytami);
            return new Ruch(Akcja.STRZEL, cel);
        }

        return new Ruch();

    }
}
