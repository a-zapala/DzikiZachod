package dzikizachod;

import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public abstract class StrategiaPomocnikaSzeryfa extends Strategia
{
    private PomocnikSzeryfa pomocnikSzeryfa;


    @Override

    protected Ruch podajRuchLeczenie(Stol stol)
    {
        List<Gracz> sasiedzi=stol.podajSasiadow(pomocnikSzeryfa);

        for(Gracz g:sasiedzi)
        {
            if(g.czyJestemSzeryfem() && g.czyMoznaMnieLeczyc())
                return new Ruch(Akcja.ULECZ,g);
        }

        if(pomocnikSzeryfa.czyMoznaMnieLeczyc())
        {
            return new Ruch(Akcja.ULECZ,pomocnikSzeryfa);
        }

        return new Ruch();
    }

    @Override

    protected Ruch podajRuchDynamit(Stol stol)
    {
        List<Gracz> graczeDoSzeryfa = stol.podajGraczyMiedzyGraczemASzeryfem(pomocnikSzeryfa);

        int liczbaPotencjalnychBandytow=0;

        for(Gracz g: graczeDoSzeryfa)
        {
            if(g.bilansZabojstw()>0)
            {
                liczbaPotencjalnychBandytow++;
            }
        }

        if(3*liczbaPotencjalnychBandytow>2*graczeDoSzeryfa.size())
        {
            Ruch ruch=new Ruch(Akcja.DYNAMIT,pomocnikSzeryfa);
            stol.lezyDynamit(true);
            return ruch;
        }
        else
        {
            return new Ruch();
        }
    }

    protected PomocnikSzeryfa pomocnikSzeryfa()
    {
        return pomocnikSzeryfa;
    }


    protected void pomocnikSzeryfa(PomocnikSzeryfa pomocnikSzeryfa)
    {
        this.pomocnikSzeryfa = pomocnikSzeryfa;
    }
}
