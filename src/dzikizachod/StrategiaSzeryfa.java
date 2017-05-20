package dzikizachod;

import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public abstract class StrategiaSzeryfa extends Strategia
{
    private Szeryf szeryf;

    public Ruch podajRuchLeczenie(Stol stol)
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

    public void szeryf(Szeryf szeryf)
    {
        this.szeryf=szeryf;
    }

    public Szeryf szeryf()
    {
        return szeryf;
    }



    @Override

    public Ruch podajRuchDynamit(Stol stol)
    {
        return new Ruch();
    }
}
