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
    private boolean czyBylDynamit;

    public PulaAkcji()
    {
        pulaAktualnychAkcji = new ArrayList<>();
        pulaZuzytychAkcji = new ArrayList<>();
        czyBylDynamit=false;
    }

    protected void dodaj(Akcja typ, int liczba)
    {
        for (int i = 0; i < liczba; i++)
        {
            pulaAktualnychAkcji.add(typ);
        }
    }

    protected Akcja dajAkcje()
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

    protected void odbierzAkcje(Akcja akcja) //odbiera akcje i daje je do zuzytych akcji
    {
        if(akcja!=Akcja.DYNAMIT)
        {
            pulaZuzytychAkcji.add(akcja);
        }
        else
        {
            czyBylDynamit=true;
        }
    }


    protected void potasujPuleAkcji()
    {
        Collections.shuffle(pulaAktualnychAkcji);
    }

    protected void zresetujPuleAkcji()
    {

        pulaAktualnychAkcji.addAll(pulaZuzytychAkcji);
        pulaZuzytychAkcji.clear();

        if(czyBylDynamit)
        {
            dodaj(Akcja.DYNAMIT,1);
            czyBylDynamit=false;
        }

    }
}