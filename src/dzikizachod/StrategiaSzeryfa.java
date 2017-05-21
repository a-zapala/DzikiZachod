package dzikizachod;

import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public abstract class StrategiaSzeryfa extends Strategia
{
    private Szeryf szeryf;


    protected void szeryf(Szeryf szeryf)
    {
        this.szeryf=szeryf;
    }

    protected Szeryf szeryf()
    {
        return szeryf;
    }


    protected Ruch podajRuchLeczenie(Stol stol)
    {
        if(szeryf.czyMoznaMnieLeczyc())
        {
            return new Ruch(Akcja.ULECZ,szeryf);
        }
        else
        {
            return new Ruch();
        }
    }

    @Override
    protected Ruch podajRuchDynamit(Stol stol)
    {
        return new Ruch();
    }
}
