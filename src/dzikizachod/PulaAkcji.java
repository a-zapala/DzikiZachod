package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrzej on 15.05.2017.
 */
public class PulaAkcji
{

    private ArrayList<Akcja> pulaAktualnychAkcji;

    private ArrayList<Akcja> pulaZuzytychAkcji;

    public void dodaj(Akcja typ, int liczba)
    {
        for (int i = 0; i < liczba; i++) pulaAktualnychAkcji.add(typ);
    }

    public ArrayList<Akcja> dajKonkretnaLiczbeAkcji(int ilosc) //TODO nie uwzglednia, że ilosc akcji moze byc mniejsza niz zadanie
    {
        ArrayList<Akcja> akcjeDoOddania = new ArrayList<>();

        while (!pulaAktualnychAkcji.isEmpty() || akcjeDoOddania.size() < ilosc)
        {
            akcjeDoOddania.add(pulaAktualnychAkcji.remove(0));
        }

        if (pulaAktualnychAkcji.isEmpty())
        {
            pulaAktualnychAkcji.addAll(pulaZuzytychAkcji);
            pulaZuzytychAkcji.clear();
        }

        Collections.shuffle(pulaAktualnychAkcji);

        while (akcjeDoOddania.size() < ilosc)
        {
            akcjeDoOddania.add(pulaAktualnychAkcji.remove(0));
        }

        return akcjeDoOddania;
    }

    public PulaAkcji()
    {
        pulaAktualnychAkcji = new ArrayList<Akcja>();
        pulaZuzytychAkcji = new ArrayList<Akcja>();
    }

    public void odbierzAkcje(ArrayList<Akcja> zuzyteAkcje)
    {
        pulaZuzytychAkcji.addAll(zuzyteAkcje);
    }
}
