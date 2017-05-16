package dzikizachod;

/**
 * Created by Andrzej on 15.05.2017.
 */
public class Ruch
{
    private boolean czyJest;
    private Gracz kto;
    private Akcja akcja;
    private Gracz kogo;

    public Gracz dajKto()
    {
        return kto;
    }

    public Akcja dajAkcja()
    {
        return akcja;
    }

    public Gracz dajKogo()
    {
        return kogo;
    }

    public Ruch(Gracz kto, Akcja akcja, Gracz kogo)
    {
        this.kto = kto;
        this.akcja = akcja;
        this.kogo = kogo;
        this.czyJest = true;
    }

    public
}
