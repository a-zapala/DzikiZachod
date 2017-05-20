package dzikizachod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class Szeryf extends Gracz
{
    private List<Gracz> graczeKtorzyStrzeliliDoSzeryfa;

    public Szeryf()
    {
        this(new StrategiaSzeryfaDomyslna());
    }

    public Szeryf(StrategiaSzeryfa strategia)
    {
        super();
        super.aktualnePunktyZycia(5);
        super.maksymalnePunktyZycia(5);
        strategia.szeryf(this);
        super.strategia(strategia);
        graczeKtorzyStrzeliliDoSzeryfa=new ArrayList<>();
    }

    public List<Gracz> graczeKtorzyStrzeliliDoSzeryfa()
    {
        return graczeKtorzyStrzeliliDoSzeryfa;
    }

    @Override
    public boolean odbierzStrzal(Gracz gracz)
    {
        super.odbierzStrzal(gracz);

        if(!graczeKtorzyStrzeliliDoSzeryfa.contains(gracz))
        {
            graczeKtorzyStrzeliliDoSzeryfa.add(gracz);
        }

        if (this.aktualnePunktyZycia() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean czyJestemSzeryfem()
    {
        return true;
    }

    @Override
    public String toString()
    {
        return "Szeryf";
    }
}
