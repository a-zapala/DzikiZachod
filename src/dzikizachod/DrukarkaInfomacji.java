package dzikizachod;

import java.util.List;

/**
 * Created by Andrzej on 16.05.2017.
 */
public class DrukarkaInfomacji
{
    private static final String start = ("** START ");
    private static final String nruchy = ("    Ruchy:");
    private static final String wciecie1 = ("  ");
    private static final String wciecie2 = ("    ");
    private static final String wciecie3 = ("      ");
    private static final String tura =("** TURA ");
    private static final String koniec=("**KONIEC");

    public void infomacjeStart(List<Gracz> gracze)
    {
        System.out.println(start);
        wypiszGraczy(gracze);
    }

    public void wypiszGraczy(List<Gracz> gracze)
    {
        System.out.println(wciecie2+"Gracze:");

        for(int i=0;i< gracze.size();i++)
        {
            System.out.println(wciecie3 +(i+1)+ gracze.get(i).wypiszSwojStan());
        }
        System.out.print("\n");

    }

    public void informacjaODanejTurze(int numerTury)
    {
        System.out.println(tura + numerTury);
    }

    public void informacjeODanajRuchuPrzedRuchem(List<Gracz> gracze,Gracz aktualnyGracz)
    {

        System.out.println(wciecie1+"Gracz " + (gracze.indexOf(aktualnyGracz)+1) + " (" + aktualnyGracz.toString() + "):"); //TODO wprowadzic karty umarłego gracza do tali

        if (aktualnyGracz.aktualnePunktyZycia() > 0)
        {
            System.out.print(wciecie2);

        }
        else
        {
            System.out.println(wciecie2+"MARTWY");
        }
    }

    public void informacjeODanejTurzePoRuchu(List<Ruch> ruchy,Gracz aktualnyGracz,List<Gracz> gracze)
    {
        System.out.println(nruchy);

        for (int i = 0; i < ruchy.size(); i++)
        {
            if (!ruchy.get(i).czyPusty())
            {
                System.out.print(wciecie3+ ruchy.get(i).akcja().toString());
                if(ruchy.get(i).akcja().equals(Akcja.STRZEL))
                {
                    System.out.print(" "+(gracze.indexOf(ruchy.get(i).cel())+1)+"\n");
                }
                else
                {
                    System.out.print("\n");
                }
            }

        }
        System.out.print("\n");
        wypiszGraczy(gracze);
    }


    public void dynamit(int zachowanieDynamitu)
    {
        switch (zachowanieDynamitu)
        {

            case (1):
                System.out.println("DYNAMIT: PRZECHODZI DALEJ");
                break;

            case (2):

                System.out.println("DYNAMIT: WYBUCHL");
                break;

            case (3):
                System.out.println("DYNAMIT: WYBUCHL");
                System.out.print("RUCHY:");
                System.out.print("MARTWY");
                break;
        }
    }

    public void wygralSzeryf()
    {
        System.out.println(koniec);
        System.out.println(wciecie1+"WYGRANA STRONA : szeryf i pomocnicy");
    }

    public void wygraliBandyci()
    {
        System.out.println(koniec);
        System.out.println(wciecie1+"WYGRANA STRONA : bandyci");
    }

    public void remis()
    {
        System.out.println(koniec);
        System.out.println(wciecie1+"REMIS - OSIAGNIETO LIMIT TUR");
    }



}
