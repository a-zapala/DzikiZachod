package dzikizachod;

import java.util.ArrayList;

/**
 * Created by Andrzej on 15.05.2017.
 */
public class Gra {

    private ArrayList<Gracz> gracze;
    private PulaAkcji pulaAkcji;
    private Stol stol;

    public Gra(){

    }

    public void rozrywka(ArrayList<Gracz> gracze, PulaAkcji pulaAkcji)
    {
        this.gracze=gracze;
        this.pulaAkcji=pulaAkcji;
    }

    public void poinformujOTypachOsob()
    {
        Gracz szeryf;
        ArrayList<Gracz> bandyci = new ArrayList<Gracz>();

        for (Gracz g : gracze)
        {
            if (g.dajRole() == Rola.SZERYF) szeryf = g;
            if (g.dajRole() == Rola.BANDYTA) bandyci.add(g);
        }

        for(Gracz g:gracze)
        {
            g.ustawSzeryfa(szeryf);
            if(g.dajRole() ==Rola.BANDYTA) g.ustawBandytow(bandyci);
        }

    }


}
