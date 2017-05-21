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
        for (int i = 0; i < 2; i++)
            gracze.add(new PomocnikSzeryfa());
        for (int i = 0; i < 5; i++)
            gracze.add(new Bandyta());

        PulaAkcji pulaAkcji = new PulaAkcji();
        pulaAkcji.dodaj(Akcja.ULECZ, 35);
        pulaAkcji.dodaj(Akcja.STRZEL, 200);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_JEDEN, 20);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_DWA, 0);
        pulaAkcji.dodaj(Akcja.DYNAMIT, 1);

        Gra gra = new Gra();
        gra.rozgrywka(gracze, pulaAkcji);
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
                koniecGry = rozpatrzDynamit(aktualnyGracz, stol, drukarka);  //rozaptrywanie dynamitu

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
                    drukarka.informacjeODanejTurzePoRuchu(zuzyteRuchy, aktualnyGracz, gracze);
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
            if (stol.szeryf().aktualnePunktyZycia() == 0)
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

        zresetujGre();
    }



    private void rozdajAkcjeNaPoczatkuRozgrywki()
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

    private Gracz podajGraczaNastepnego(Gracz gracz)
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

    private boolean zwiekszNumerTury(Gracz aktualnyGracz)
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

    private void oddajAkcjeDoPuli(List<Ruch> ruchy)
    {
        for (Ruch r : ruchy)
        {
            if (!r.czyPusty() && r.akcja() != Akcja.DYNAMIT)
            {
                pulaAkcji.odbierzAkcje(r.akcja());
            }
        }
    }

    private boolean rozpatrzDynamit(Gracz aktualnyGracz, Stol stol, DrukarkaInfomacji drukarka)
    {
        boolean koniecGry = false;
        if (stol.lezyDynamit())
        {
            if (aktualnyGracz.rzucKostka())
            {
                koniecGry = aktualnyGracz.odbierzDynamit();


                if (aktualnyGracz.aktualnePunktyZycia() == 0) //jesli dynamit zabił
                {
                    drukarka.dynamit(3);
                }
                else
                {
                    drukarka.dynamit(2);
                }

                stol.lezyDynamit(false);
            }
            else
            {
                drukarka.dynamit(1);
            }

        }

        return koniecGry;
    }

    private void zresetujGre() //na koniec powraca graczy do stanu pierwotnego rowniez pule akcji
    {
        for(Gracz g:gracze)
        {
            List<Akcja> akcjeGracza=g.oddajWszystkieAkcje();
            g.zresetujGracza();
            for(Akcja a: akcjeGracza)
            {
                pulaAkcji.odbierzAkcje(a);
            }
        }

        pulaAkcji.zresetujPuleAkcji();

    }
}