package dzikizachod;

import java.util.ArrayList;
import java.util.List;

public class Bandyta extends Gracz
{
    private static List<Bandyta> bandyci;
    private static List<Bandyta> bandyciAktywni;

    public Bandyta()
    {
        this(new StrategiaBandytyDomyslna());
    }

    protected List<Bandyta> bandyciAktywni()
    {
        return bandyciAktywni;
    }


    public Bandyta(StrategiaBandyty strategia)
    {
        super();
        super.strategia(strategia);
        if (bandyci == null)
        {
            bandyci = new ArrayList<>();
            bandyciAktywni=new ArrayList<>();
            bandyci.add(this);
            bandyciAktywni.add(this);
        }
        else
        {
            bandyci.add(this);
            bandyciAktywni.add(this);
        }
        strategia.bandyta(this);
    }

    @Override
    protected boolean odbierzStrzal(Gracz gracz)
    {
        super.odbierzStrzal(gracz);

        if (this.aktualnePunktyZycia() == 0)
        {
            bandyciAktywni.remove(this);           //usuwam gracza z listy bandytow
            gracz.zabilBandyte();
        }

        if (bandyciAktywni.isEmpty())               //sprawdzam czy została zakonczona gra
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected boolean odbierzDynamit() //analogicznie do odbierz strzał tylko bez zaliczania killa
    {
        super.odbierzDynamit();

        if (this.aktualnePunktyZycia() == 0)
        {
            bandyciAktywni.remove(this);           //usuwam gracza z listy bandytow
        }

        if (bandyciAktywni.isEmpty())               //sprawdzam czy została zakonczona gra
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void zresetujGracza()
    {
        super.zresetujGracza();
        bandyci.addAll(bandyciAktywni);
    }

    @Override
    public String toString()
    {
        return "Bandyta";
    }

    @Override
    protected boolean czyJestemSzeryfem()
    {
        return false;
    }



}
