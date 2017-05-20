package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class PulaAkcji
{
    private List<Akcja> pulaAktualnychAkcji;
    private List<Akcja> pulaZuzytychAkcji;

    public PulaAkcji()
    {
        pulaAktualnychAkcji = new ArrayList<>();
        pulaZuzytychAkcji = new ArrayList<>();
    }

    public void dodaj(Akcja typ, int liczba)
    {
        for (int i = 0; i < liczba; i++)
        {
            pulaAktualnychAkcji.add(typ);

        }
    }

    public Akcja dajAkcje()
    {
        Akcja doOdania = pulaAktualnychAkcji.remove(0); //TODO mozna zrobic wyjatki

        if (pulaAktualnychAkcji.isEmpty())
        {
            pulaAktualnychAkcji.addAll(pulaZuzytychAkcji);
            pulaZuzytychAkcji.clear();
            potasujPuleAkcji();
        }

        return doOdania;
    }

    public void potasujPuleAkcji()
    {
        Collections.shuffle(pulaAktualnychAkcji);
    }

    public void odbierzAkcje(Akcja akcja)
    {
        if(akcja!=Akcja.DYNAMIT)
        {
            pulaZuzytychAkcji.add(akcja);
        }
    }

}