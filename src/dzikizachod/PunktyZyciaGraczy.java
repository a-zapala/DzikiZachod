package dzikizachod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrzej on 15.05.2017.
 */
public class PunktyZyciaGraczy {

    private Map<Gracz, Integer> mapaZycGraczy;

    public int podajPunktyZyciaGracza(Gracz gracz) {

        return mapaZycGraczy.get(gracz);
    }

    public void zmiejszPunktyZycia(Gracz gracz) {
        int punktyZycia = mapaZycGraczy.get(gracz);
        punktyZycia--;
        mapaZycGraczy.put(gracz, punktyZycia);
    }

    public PunktyZyciaGraczy(ArrayList<Gracz> gracze) {

        mapaZycGraczy = new HashMap<>();

        for (Gracz g : gracze) {

            int punktyZycia = g.getPunktyZycia();
            mapaZycGraczy.put(g,punktyZycia);

        }
    }
}
