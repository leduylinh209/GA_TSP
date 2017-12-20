package tsp;

import java.util.ArrayList;
import java.util.List;

public class TourManager {
    private static List<City> mAllCities = new ArrayList();

    public static void addCity(City city) {
        mAllCities.add(city);
    }

    public static City getCity(int index) {
        return mAllCities.get(index);
    }

    public static int numberOfCities() {
        return mAllCities.size();
    }
}
