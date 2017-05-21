package dzikizachod;


import java.util.List;

/**
 * Created by Andrzej on 15.05.2017.
 */
public abstract class Strategia
{

    protected Ruch podajRuch(List<Akcja> akcje, Stol stol)
    {
        if (akcje.contains(Akcja.ULECZ))
        {
            Ruch ruchLeczenia = podajRuchLeczenie(stol);

            if (!ruchLeczenia.czyPusty()) //iesli strategia nie moze uleczyc
            {
                akcje.remove(Akcja.ULECZ);
                return ruchLeczenia;
            }
        }

        if (akcje.contains(Akcja.ZASIEG_PLUS_JEDEN))
        {
            akcje.remove(Akcja.ZASIEG_PLUS_JEDEN);
            return new Ruch(Akcja.ZASIEG_PLUS_JEDEN);
        }

        if (akcje.contains(Akcja.ZASIEG_PLUS_DWA))
        {
            akcje.remove(Akcja.ZASIEG_PLUS_DWA);
            return new Ruch(Akcja.ZASIEG_PLUS_DWA);
        }

        if (akcje.contains(Akcja.STRZEL))
        {
            Ruch ruchStrzelania = podajRuchStrzelania(stol);

            if (!ruchStrzelania.czyPusty())
            {
                akcje.remove(Akcja.STRZEL);
                return ruchStrzelania;
            }
        }

        if (akcje.contains(Akcja.DYNAMIT))
        {
            Ruch ruchDynamit = podajRuchDynamit(stol);

            if (!ruchDynamit.czyPusty())
            {
                akcje.remove(Akcja.DYNAMIT);
                return ruchDynamit;
            }

        }

        return new Ruch(); //zwraca ruch pusty
    }

    protected abstract Ruch podajRuchLeczenie(Stol stol);

    protected abstract Ruch podajRuchStrzelania(Stol stol);

    protected abstract Ruch podajRuchDynamit(Stol stol);

}
