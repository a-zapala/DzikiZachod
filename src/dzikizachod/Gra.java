package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class Gra
{
    private PulaAkcji pulaAkcji;
    private List<Gracz> gracze;


    public static void main(String[] args)
    {
        List<Gracz> gracze = new ArrayList<>();
        gracze.add(new Szeryf());
        for (int i = 0; i < 5; i++)
            gracze.add(new PomocnikSzeryfa());
        for (int i = 0; i < 3 ; i++)
            gracze.add(new Bandyta(new StrategiaBandytySprytna()));

        PulaAkcji pulaAkcji = new PulaAkcji();
        pulaAkcji.dodaj(Akcja.ULECZ, 20);
        pulaAkcji.dodaj(Akcja.STRZEL, 50);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_JEDEN, 1);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_DWA, 2);
        pulaAkcji.dodaj(Akcja.DYNAMIT, 1);

        Gra gra = new Gra();
        gra.rozgrywka(gracze, pulaAkcji);
    }


    public Gra()
    {

    }

    public void rozgrywka(List<Gracz> gracze, PulaAkcji pulaAkcji)
    {
        this.gracze = gracze;
        this.pulaAkcji = pulaAkcji;

        Collections.shuffle(gracze);
        Stol stol = new Stol(gracze);
        DrukarkaInfomacji drukarka = new DrukarkaInfomacji();

        rozdajAkcjeNaPoczatkuRozgrywki();
        drukarka.infomacjeStart(gracze);

        boolean koniecGry = false;
        int numerTury = 1;
        Gracz aktualnyGracz = stol.szeryf();

        while (numerTury < 42 && !koniecGry)
        {
            drukarka.informacjeODanajRuchuPrzedRuchem(gracze, aktualnyGracz);

            if (aktualnyGracz.aktualnePunktyZycia() > 0)
            {
                koniecGry = rozpatrzDynamit(aktualnyGracz, stol, drukarka);

                if (!koniecGry)
                {
                    while (aktualnyGracz.czyChceszAkcje())  //dobieranie karty
                    {
                        aktualnyGracz.dobierzAkcje(pulaAkcji.dajAkcje());
                    }
                    aktualnyGracz.wypiszAkcje();

                    List<Ruch> zuzyteRuchy = new ArrayList<>();
                    koniecGry = aktualnyGracz.wykonajRuch(stol, zuzyteRuchy);
                    oddajAkcjeDoPuli(zuzyteRuchy);
                    drukarka.informacjeODanejTurzePoRuchu(zuzyteRuchy, aktualnyGracz, gracze); //Todo oddawanie kart
                }
            }
            aktualnyGracz = podajGraczaNastepnego(aktualnyGracz);
            if (zwiekszNumerTury(aktualnyGracz))
            {
                numerTury++;
                drukarka.informacjaODanejTurze(numerTury);
            }
        }


        if (koniecGry)

        {
            if (stol.szeryf().aktualnePunktyZycia()==0)
            {
                drukarka.wygraliBandyci();
            }
            else
            {
                drukarka.wygralSzeryf();
            }
        }
        else

        {
            drukarka.remis();
        }

    }

    public void rozdajAkcjeNaPoczatkuRozgrywki()
    {
        pulaAkcji.potasujPuleAkcji();

        for (Gracz g : gracze)
        {
            while (g.czyChceszAkcje())
            {
                g.dobierzAkcje(pulaAkcji.dajAkcje());
            }
        }
    }

    public Gracz podajGraczaNastepnego(Gracz gracz)
    {
        int index = gracze.indexOf(gracz);
        index++;

        if (index < gracze.size())
        {
            return gracze.get(index);
        }
        else
        {
            return gracze.get(0);
        }
    }

    public boolean zwiekszNumerTury(Gracz aktualnyGracz)
    {
        if (aktualnyGracz.czyJestemSzeryfem())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void oddajAkcjeDoPuli(List<Ruch> ruchy)
    {
        for (Ruch r : ruchy)
        {
            if (!r.czyPusty() && r.akcja() != Akcja.DYNAMIT)
            {
                pulaAkcji.odbierzAkcje(r.akcja());
            }
        }
    }

    public boolean rozpatrzDynamit(Gracz aktualnyGracz, Stol stol, DrukarkaInfomacji drukarka)
    {
        boolean koniecGry = false;
        if (!stol.lezyDynamit().czyPusty())
        {
            if (aktualnyGracz.rzucKostka())
            {
                koniecGry = aktualnyGracz.odbierzStrzal(stol.lezyDynamit().cel());

                if (aktualnyGracz.aktualnePunktyZycia() == 0) //jesli dynamit zabił
                {
                    drukarka.dynamit(3);
                }
                else
                {
                    drukarka.dynamit(2);
                }
            }
            else
            {
                drukarka.dynamit(1);
            }
        }

        return koniecGry;
    }
}