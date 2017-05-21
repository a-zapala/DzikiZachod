package dzikizachod;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class Ruch               //klasa trzymajaca informacje o ruchu
{
    private Akcja akcja;
    private Gracz cel;
    private boolean pusty;

    public Ruch()               //tworzenie pustego ruchu
    {
        this.pusty=true;
    }

    public Ruch(Akcja akcja,Gracz cel)
    {
        this.akcja=akcja;
        this.cel=cel;
        this.pusty=false;
    }

    public Ruch(Akcja akcja)
    {
        this.akcja=akcja;
        this.pusty=false;
    }


    protected Akcja akcja()
    {
        return akcja;
    }

    protected Gracz cel()
    {
        return cel;
    }


    public boolean czyPusty()
    {
        return pusty;
    }

}
