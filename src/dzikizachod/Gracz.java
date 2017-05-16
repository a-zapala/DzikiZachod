package dzikizachod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Andrzej on 15.05.2017.
 */
public abstract class Gracz
{
    protected Gracz szeryf;
    protected int id;
    protected int mojePunktyZycia;
    private int zasieg;
    private final static Random los;
    private ArrayList<Akcja> reka;
    private Rola rola;
    static HashMap<Gracz, Integer> punktyZyciaGraczy;


    protected Gracz()
    {
        los = new Random();
        reka = new ArrayList<>();
        zasieg = 1;
    }

    public int getPunktyZycia()
    {
        return mojePunktyZycia;
    }

    public int ileAkcjiDobierasz()
    {
        return 5 - reka.size(); //TODO zienic 5
    }

    public void dobierzAkcje(ArrayList<Akcja> akcje)
    {
        reka.addAll(akcje);
    }

    public Rola dajRole()
    {
        return rola;
    }

    public void wypiszSwojStan()
    {
        if (mojePunktyZycia > 0)
        {
            System.out.println(id + ": " + this.toString() + "(liczba żyć:" + mojePunktyZycia + ")");
        }
        else
        {
            System.out.println(id + ": X (" + toString() + ")");
        }
    }

    public void ustawSzeryfa(Gracz szeryf)
    {
        this.szeryf=szeryf;
    }



}
