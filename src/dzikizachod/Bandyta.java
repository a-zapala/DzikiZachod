package dzikizachod;

import java.util.ArrayList;
import java.util.List;

public class Bandyta extends Gracz
{
    private static List<Bandyta> bandyci;


    public Bandyta()
    {
        this(new StrategiaBandytyDomyslna());
    }

    public Bandyta(StrategiaBandyty strategia)
    {
        super();
        super.strategia(strategia);
        if (bandyci == null)
        {
            bandyci = new ArrayList<>();
            bandyci.add(this);
        }
        else
        {
            bandyci.add(this);
        }
        strategia.bandyta(this);
    }

    @Override
    public boolean odbierzStrzal(Gracz gracz)
    {
        super.odbierzStrzal(gracz);

        if (this.aktualnePunktyZycia() == 0)
        {
            bandyci.remove(this);           //usuwam gracza z listy bandytow
            gracz.zabilBandyte();
        }

        if (bandyci.isEmpty())               //sprawdzam czy została zakonczona gra
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
        return false;
    }

    public static List<Bandyta> bandyci()
    {
        return bandyci;
    }

    @Override
    public String toString()
    {
        return "Bandyta";
    }

}
