package tsp;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private Tour[] mTours;

    public Population(int size, boolean initialise) {
        mTours = new Tour[size];
        if (initialise) {
            for (int i = 0; i < mTours.length; i++) {
                Tour tour = new Tour();
                tour.generateIndividual();
                saveTour(i, tour);
            }
        }
    }

    public void saveTour(int index, Tour tour){
        mTours[index] = tour;
    }

    public Tour[] getTours() {
        return mTours;
    }

    public Tour getBestTour() {
        Tour bestTour = mTours[0];

        for (int i = 1; i < mTours.length; i++) {
            if (bestTour.getFitness() < mTours[i].getFitness()) {
                bestTour = mTours[i];
            }
        }
        return bestTour;
    }
}
