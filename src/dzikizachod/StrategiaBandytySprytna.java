package dzikizachod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class StrategiaBandytySprytna extends StrategiaBandyty
{
    private boolean zabitoOstatnioBandyte;

    @Override
    public Ruch podajRuchStrzelania(Stol stol)
    {
        Ruch strzelDoSzeryfa = super.podajRuchStrzelania(stol);

        if (!strzelDoSzeryfa.czyPusty())
        {
            return strzelDoSzeryfa;
        }

        List<Gracz> graczeWZasiegu = stol.podajGraczyWZasiegu(super.bandyta());


        if (zabitoOstatnioBandyte)
        {
            List<Gracz> graczeWZasieguNieBedacyBandytami = new ArrayList<>();

            for (Gracz g : graczeWZasiegu)
            {
                if (!super.bandyta().bandyci().contains(g))
                {
                    graczeWZasieguNieBedacyBandytami.add(g);
                }
            }

            if (!graczeWZasieguNieBedacyBandytami.isEmpty())
            {
                zabitoOstatnioBandyte = false;
                return new Ruch(Akcja.STRZEL, super.bandyta().wylosujDoStrzalu(graczeWZasieguNieBedacyBandytami));
            }
        }
        else
        {
            int liczbaAkcjiStrzel=super.bandyta().liczbaAkcjiStrzel();

            List<Gracz> graczeWZasieguBedacyBandytami = new ArrayList<>();

            for (Gracz g : graczeWZasiegu)
            {
                if (super.bandyta().bandyci().contains(g))
                {
                    graczeWZasieguBedacyBandytami.add(g);
                }
            }

            for(Gracz g: graczeWZasieguBedacyBandytami)
            {
                if(g.aktualnePunktyZycia()<=liczbaAkcjiStrzel)
                {
                    if(g.aktualnePunktyZycia()==1)
                    {
                        zabitoOstatnioBandyte=true;
                    }
                    return new Ruch(Akcja.STRZEL,g);
                }
            }

        }
        return new Ruch();
    }

}
