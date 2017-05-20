package dzikizachod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class StrategiaBandyty extends Strategia
{
    private Bandyta bandyta;

    public Bandyta bandyta()
    {
        return bandyta;
    }

    public void bandyta(Bandyta bandyta)
    {
        this.bandyta = bandyta;
    }

    @Override
    public Ruch podajRuchLeczenie(Stol stol)
    {
        if (bandyta.czyMoznaMnieLeczyc())
        {
            return new Ruch(Akcja.ULECZ,bandyta);
        }
        else
        {
            return new Ruch();
        }
    }

    @Override
    public Ruch podajRuchStrzelania(Stol stol)
    {
        List<Gracz> graczeWZasiegu = stol.podajGraczyWZasiegu(bandyta);

        for (Gracz g : graczeWZasiegu)
        {
            if (g.czyJestemSzeryfem())
            {
                return new Ruch(Akcja.STRZEL, g);
            }
        }

        return new Ruch();
    }

    @Override
    public Ruch podajRuchDynamit(Stol stol)
    {
        List<Gracz> graczeZgodnieZTura=new ArrayList<>();

        Gracz pom=bandyta;
        pom=stol.podajAktywnegoGraczaNastepnego(pom);

        for(int i=0;i<3;i++)
        {
            graczeZgodnieZTura.add(pom);
        }

        for(Gracz g:graczeZgodnieZTura)
        {
            if(g.czyJestemSzeryfem())
            {
                Ruch ruch=new Ruch(Akcja.DYNAMIT,bandyta);
                stol.lezyDynamit(ruch);
                return ruch;
            }
        }

        return new Ruch();
    }
}
