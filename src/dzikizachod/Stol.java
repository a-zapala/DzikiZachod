package dzikizachod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 18.05.2017.
 */
public class Stol
{
    private List<Gracz> graczeAktywni;
    private Ruch lezyDynamit;


    public Stol(List<Gracz> gracze)
    {
        graczeAktywni = new ArrayList<>();
        graczeAktywni.addAll(gracze);
        lezyDynamit=new Ruch();
    }

    public Gracz szeryf()
    {
        for (Gracz g : graczeAktywni)
        {
            if (g.czyJestemSzeryfem())
            {
                return g;
            }
        }
        return null;
    }


    public Gracz podajAktywnegoGraczaNastepnego(Gracz gracz)
    {
        int index = graczeAktywni.indexOf(gracz);
        index++;

        if (index < graczeAktywni.size())
        {
            return graczeAktywni.get(index);
        }
        else
        {
            return graczeAktywni.get(0);
        }
    }

    public Ruch lezyDynamit()
    {
        return lezyDynamit;
    }

    public void lezyDynamit(Ruch lezyDynamit)
    {
        this.lezyDynamit = lezyDynamit;
    }

    public List<Gracz> podajSasiadow(Gracz gracz)
    {
        List<Gracz> sasiedzi = new ArrayList<>();

        int indexGracza = graczeAktywni.indexOf(gracz);

        if (indexGracza > 0)
        {
            sasiedzi.add(graczeAktywni.get(indexGracza - 1));
        }
        else
        {
            sasiedzi.add(graczeAktywni.get(graczeAktywni.size()-1));
        }

        if (indexGracza < graczeAktywni.size()-1)
        {
            sasiedzi.add(graczeAktywni.get(indexGracza + 1));
        }
        else
        {
            sasiedzi.add(graczeAktywni.get(0));
        }

        return sasiedzi;
    }

    public List<Gracz> podajGraczyWZasiegu(Gracz gracz)
    {

        List<Gracz> graczeWZasiegu = new ArrayList<>();

        if (gracz.zasieg() * 2 + 1 >= graczeAktywni.size()) //jezeli wszyscy gracze sa w zasiegu
        {
            graczeWZasiegu.addAll(graczeAktywni);
            graczeWZasiegu.remove(gracz);

            return graczeWZasiegu;
        }
        else
        {
           ;

            graczeWZasiegu.addAll(graczeWZasieguZgodnieZtura(gracz));
            graczeWZasiegu.addAll(graczeWZasieguPrzeciwnieDoTury(gracz));

            return graczeWZasiegu;
        }
    }

    public List<Gracz> graczeWZasieguZgodnieZtura(Gracz gracz)
    {

        if (gracz.zasieg() < graczeAktywni.size())
        {
            int indexGracza = graczeAktywni.indexOf(gracz);
            List<Gracz> graczeWZasiegu = new ArrayList<>();
            int indexPierwszegoSasiada = indexGracza + 1;

            int i = 0;
            while (i < gracz.zasieg())
            {
                if (indexPierwszegoSasiada + i < graczeAktywni.size())
                {
                    graczeWZasiegu.add(graczeAktywni.get(indexPierwszegoSasiada + i));
                }
                else
                {
                    graczeWZasiegu.add(graczeAktywni.get(indexPierwszegoSasiada + i - graczeAktywni.size()));
                }
                i++;
            }
            return graczeWZasiegu;
        }
        else
        {
            return graczeAktywni;
        }
    }

    public List<Gracz> graczeWZasieguPrzeciwnieDoTury(Gracz gracz)
    {
        if (gracz.zasieg() < graczeAktywni.size())
        {
            int indexGracza = graczeAktywni.indexOf(gracz);
            ArrayList<Gracz> graczeWZasiegu = new ArrayList<>();
            int i = 0;
            int indexPierwszegoSasiada = indexGracza - 1;

            while (i < gracz.zasieg())
            {
                if (indexPierwszegoSasiada - i >= 0)
                {
                    graczeWZasiegu.add(graczeAktywni.get(indexPierwszegoSasiada - i));
                }
                else
                {
                    graczeWZasiegu.add(graczeAktywni.get(graczeAktywni.size() + (indexPierwszegoSasiada - i)));
                }
                i++;
            }

            return graczeWZasiegu;
        }
        else
        {
            return graczeAktywni;
        }
    }

    public List<Gracz> podajGraczyWZasieguSkracajacyDystansDoSzeryfa(Gracz gracz)
    {
        int indexSzeryfa = graczeAktywni.indexOf(szeryf());
        int indexGracza = graczeAktywni.indexOf(gracz);

        if (indexSzeryfa > indexGracza)
        {
            if (2 * (indexSzeryfa - indexGracza) < graczeAktywni.size())
            {
                return graczeWZasieguZgodnieZtura(gracz);
            }
            else if (2 * (indexSzeryfa - indexGracza) == graczeAktywni.size())
            {
                return podajGraczyWZasiegu(gracz);
            }
            else
            {
                return graczeWZasieguPrzeciwnieDoTury(gracz);
            }
        }
        else
        {
            if (2 * (indexGracza - indexSzeryfa) <graczeAktywni.size())
            {
                return graczeWZasieguPrzeciwnieDoTury(gracz);
            }
            else if(2 * (indexGracza - indexSzeryfa) ==graczeAktywni.size())
            {
                return podajGraczyWZasiegu(gracz);
            }
            else
            {
                return graczeWZasieguZgodnieZtura(gracz);
            }
        }
    }

    public List<Gracz> podajGraczyMiedzyGraczemASzeryfem(Gracz gracz)
    {
        List<Gracz> graczeMiedzyGraczemASzeryfem = new ArrayList<>();

        Gracz pom = gracz;

        while (!pom.czyJestemSzeryfem())
        {
            graczeMiedzyGraczemASzeryfem.add(pom);
            pom = podajAktywnegoGraczaNastepnego(pom);
        }

        return graczeMiedzyGraczemASzeryfem;

    }


}
