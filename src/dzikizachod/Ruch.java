package dzikizachod;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class Ruch
{
    private Akcja akcja;

    public Akcja akcja()
    {
        return akcja;
    }

    public Gracz cel()
    {
        return cel;
    }

    private Gracz cel;
    private boolean pusty;

    public Ruch()
    {
        this.pusty=true;
    }

    public Ruch(Akcja akcja,Gracz cel) //TODO podeklarowac to co omijamy do testow tak jest lepiej
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


    public boolean czyPusty()
    {
        return pusty;
    }



}
