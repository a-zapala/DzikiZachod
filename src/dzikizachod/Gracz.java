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
        maksymalnePunktyZycia = los.nextInt(2) + 3;
        aktualnePunktyZycia = maksymalnePunktyZycia;
        bilansZabojstw=0;
    }

    /*
    Gettery i setery
     */
    protected int bilansZabojstw()
    {
        return bilansZabojstw;
    }

    protected void aktualnePunktyZycia(int aktualnePunktyZycia )
    {
        this.aktualnePunktyZycia=aktualnePunktyZycia;
    }

    protected int aktualnePunktyZycia()
    {
        return aktualnePunktyZycia;
    }

    protected void  maksymalnePunktyZycia(int maksymalnePunktyZycia)
    {
        this.maksymalnePunktyZycia=maksymalnePunktyZycia;
    }

    protected int zasieg(){return zasieg;}

    protected void strategia(Strategia strategia)
    {
        this.strategia=strategia;
    }




    /*
    metody odnoszace sie do rozgrywki
     */

    protected boolean czyChceszAkcje()
    {
        return pulaAkcjiGracza.size()<5;
    }

    protected void dobierzAkcje(Akcja akcja)
    {
        pulaAkcjiGracza.add(akcja);
    }

    protected boolean czyMoznaMnieLeczyc()
    {
        return maksymalnePunktyZycia>aktualnePunktyZycia;
    }

    protected abstract boolean czyJestemSzeryfem();

    protected boolean odbierzStrzal(Gracz gracz)
    {
        aktualnePunktyZycia--;
        return false;
    }

    protected boolean odbierzDynamit()
    {
        aktualnePunktyZycia--;
        return false;
    }

    protected void zabilBandyte()
    {
        bilansZabojstw--;
    }

    protected void zabilPomocnikaSzeryfa()
    {
        bilansZabojstw++;
    }

    protected int liczbaAkcjiStrzel() //potrzebne do strategi sprytnej
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

    protected boolean wykonajRuch(Stol stol,List<Ruch> ruchy)
    {
        Ruch ruch=strategia.podajRuch(pulaAkcjiGracza,stol); //strategia podaje konkretny ruch, nastepnie gracz go wykonuje
        boolean czyKoniec=false;                                //zmienna informuje o koncu gry jest modyfikowana przez szeryfa/bandytow

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
                    stol.lezyDynamit(true);
                    break;
                default:
                    break;
            }

            ruch=strategia.podajRuch(pulaAkcjiGracza,stol);
        }
        return czyKoniec;
    }

    private void zwiekszZasieg(int ile)
    {
        zasieg+=ile;
    }

    protected void ulecz()
    {
        aktualnePunktyZycia++;
    }

    protected void zresetujGracza()
    {
        aktualnePunktyZycia=maksymalnePunktyZycia;
        zasieg=1;
    }

    protected List<Akcja> oddajWszystkieAkcje()
    {
        List<Akcja> doOddania=new ArrayList<>();
        doOddania.addAll(pulaAkcjiGracza);
        pulaAkcjiGracza.clear();
        return doOddania;
    }

    /*
    metody losowe
     */

    protected Gracz wylosujDoStrzalu(List<Gracz> graczeDoStrzalu)
    {
        int losowa = los.nextInt(graczeDoStrzalu.size());
        return graczeDoStrzalu.get(losowa);
    }

    protected boolean rzucKostka()
    {
        int losowa = los.nextInt(6);

        return losowa == 0;

    }


    /*
    wypisywanie informacji
     */
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

    protected void wypiszAkcje()
    {
        System.out.print("Akcje: [");

        int i;
        for (i = 0; i < pulaAkcjiGracza.size() - 1; i++)
        {

            System.out.print(pulaAkcjiGracza.get(i).toString() + ", ");
        }

        System.out.print(pulaAkcjiGracza.get(i).toString() + "]\n");
    }

}
