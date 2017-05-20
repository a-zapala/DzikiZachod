package dzikizachod;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Andrzej on 18.05.2017.
 */
public abstract class Gracz
{
    private int aktualnePunktyZycia;
    private int maksymalnePunktyZycia;
    private int zasieg;

    private static Random los;
    private Strategia strategia;
    private List<Akcja> pulaAkcjiGracza;

    private int bilansZabojstw; //gdy dodatni wiecej zabito pomocnikow szeryfa

    public Gracz()
    {
        los = new Random();
        pulaAkcjiGracza = new ArrayList<>();
        zasieg = 1;
        maksymalnePunktyZycia = los.nextInt(2) + 3; //TODO STALE
        aktualnePunktyZycia = maksymalnePunktyZycia;
        bilansZabojstw=0;
    }

    public boolean czyChceszAkcje()
    {
        return pulaAkcjiGracza.size()<5;
    }

    public int bilansZabojstw()
    {
        return bilansZabojstw;
    }

    public boolean czyMoznaMnieLeczyc()
    {
        return maksymalnePunktyZycia>aktualnePunktyZycia;
    }

    public abstract boolean czyJestemSzeryfem();

    public void aktualnePunktyZycia(int aktualnePunktyZycia )
    {
        this.aktualnePunktyZycia=aktualnePunktyZycia;
    }

    public int aktualnePunktyZycia()
    {
        return aktualnePunktyZycia;
    }

    public void  maksymalnePunktyZycia(int maksymalnePunktyZycia)
    {
        this.maksymalnePunktyZycia=maksymalnePunktyZycia;
    }

    public int zasieg(){return zasieg;}

    public boolean odbierzStrzal(Gracz gracz)
    {
        aktualnePunktyZycia--;
        return false;
    }

    public boolean odbierzDynamit()
    {
        aktualnePunktyZycia--;
        return false;
    }

    public void zabilBandyte()
    {
        bilansZabojstw--;
    }

    public void zabilPomocnikaSzeryfa()
    {
        bilansZabojstw++;
    }

    public void strategia(Strategia strategia)
    {
        this.strategia=strategia;
    }

    public Gracz wylosujDoStrzalu(List<Gracz> graczeDoStrzalu)
    {
        int losowa = los.nextInt(graczeDoStrzalu.size());
        return graczeDoStrzalu.get(losowa);
    }

    public void dobierzAkcje(Akcja akcja)
    {
        pulaAkcjiGracza.add(akcja);
    }

    public int liczbaAkcjiStrzel()
    {
        int i=0;

        for(Akcja a:pulaAkcjiGracza)
        {
            if(a==Akcja.STRZEL)
            {
                i++;
            }
        }
        return i;
    }

    public String wypiszSwojStan()
    {
        if (aktualnePunktyZycia > 0)
        {
            return (": " + this.toString() + " (liczba żyć: " + aktualnePunktyZycia + ")");
        }
        else
        {
            return (": X (" + toString() + ")");
        }
    }

    public void wypiszAkcje()
    {
        System.out.print("Akcje: [");

        int i;
        for (i = 0; i < pulaAkcjiGracza.size() - 1; i++)
        {

            System.out.print(pulaAkcjiGracza.get(i).toString() + ", ");
        }

        System.out.print(pulaAkcjiGracza.get(i).toString() + "]\n");
    }

    public boolean wykonajRuch(Stol stol,List<Ruch> ruchy)
    {
        Ruch ruch=strategia.podajRuch(pulaAkcjiGracza,stol);
        boolean czyKoniec=false;

        while(!ruch.czyPusty()&&!czyKoniec)
        {
            ruchy.add(ruch);

            switch (ruch.akcja())
            {
                case ULECZ:
                    ruch.cel().ulecz();
                    break;

                case STRZEL:
                    czyKoniec=ruch.cel().odbierzStrzal(this);
                    break;

                case ZASIEG_PLUS_JEDEN:
                    this.zwiekszZasieg(1);
                    break;

                case ZASIEG_PLUS_DWA:
                    this.zwiekszZasieg(2);
                    break;
                case DYNAMIT:
                    stol.lezyDynamit(ruch);
                    break;
                default:
                    break;
            }

            ruch=strategia.podajRuch(pulaAkcjiGracza,stol);
        }
        return czyKoniec;
    }

    public void ulecz()
    {
        aktualnePunktyZycia++;
    }

    public void zwiekszZasieg(int ile)
    {
        zasieg+=ile;
    }

    public boolean rzucKostka()
    {
        int losowa = los.nextInt(6);

        return losowa == 0;

    }



}
