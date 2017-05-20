package dzikizachod;

/**
 * Created by Andrzej on 15.05.2017.
 */
public class PomocnikSzeryfa extends Gracz
{
    public PomocnikSzeryfa()
    {
        this(new StrategiaPomocnikaSzeryfaDomyslna());
    }

    public PomocnikSzeryfa(StrategiaPomocnikaSzeryfa strategia)
    {
        super();
        super.strategia(strategia);
        strategia.pomocnikSzeryfa(this);
    }

    @Override
    public boolean odbierzStrzal(Gracz gracz)
    {
        super.odbierzStrzal(gracz);

        if (this.aktualnePunktyZycia() == 0)
        {
            gracz.zabilPomocnikaSzeryfa();
        }

        return false;
    }

    @Override
    public boolean czyJestemSzeryfem()
    {
        return false;
    }


    @Override
    public String toString()
    {
        return "Pomocnik Szeryfa";
    }

}
