package tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tour {
    private List<City> mTour = new ArrayList();

    private double mFitness = 0;
    private int mDistance = 0;

    public Tour() {
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            mTour.add(null);
        }
    }

    public Tour(ArrayList mTour) {
        this.mTour = mTour;
    }

    public void generateIndividual() {
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            mTour.set(i, TourManager.getCity(i));
        }

        Collections.shuffle(mTour);
    }

    public List<City> getTour() {
        return mTour;
    }

    public void setCity(int index, City city) {
        mTour.set(index, city);

        mFitness = 0;
        mDistance = 0;

    }

    public double getFitness() {
        if (mFitness == 0) {
            mFitness = 1 / (double)getDistance();
        }

        return mFitness;
    }

    public int getDistance(){
        if (mDistance == 0) {
            int tourDistance = 0;
            for (int cityIndex=0; cityIndex < mTour.size(); cityIndex++) {
                City fromCity = mTour.get(cityIndex);
                City destinationCity;
                if(cityIndex+1 < mTour.size()){
                    destinationCity = mTour.get(cityIndex+1);
                }
                else{
                    destinationCity = mTour.get(0);
                }
                tourDistance += fromCity.distance(destinationCity);
            }
            mDistance = tourDistance;
        }
        return mDistance;
    }

    public boolean containsCity(City city) {
        return mTour.contains(city);
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < mTour.size(); i++) {
            geneString += mTour.get(i)+"|";
        }
        return geneString;
    }
}
