package tsp;

import java.util.Arrays;
import java.util.Random;

public class GA {
    private static final double MUTATION_RATE = 0.015;
    private static final boolean KEEP_BEST = true;

    public static Population evolvePopulation(Population pop) {
        Population evolPopulation = new Population(pop.getTours().length, false);

        int offset = 0;
        if (KEEP_BEST) {
            evolPopulation.saveTour(0, pop.getBestTour());
            offset = 1;
        }

        for (int i = offset; i < evolPopulation.getTours().length; i++) {
            Tour parent1 = rouletteWheelSelection(pop);
            Tour parent2 = rouletteWheelSelection(pop);

            Tour child = crossover(parent1, parent2);
            evolPopulation.saveTour(i, child);
        }

        for (int i = offset; i < evolPopulation.getTours().length; i++) {
            mutate(evolPopulation.getTours()[i]);
        }

        return evolPopulation;
    }

    private static void mutate(Tour tour) {
        for (int pos1 = 0; pos1 < TourManager.numberOfCities(); pos1++) {
            if (Math.random() < MUTATION_RATE) {
                int pos2 = (int) (TourManager.numberOfCities() * Math.random());

                City city1 = tour.getTour().get(pos1);
                City city2 = tour.getTour().get(pos2);

                tour.setCity(pos1, city2);
                tour.setCity(pos2, city1);
            }
        }
    }

    private static Tour crossover(Tour parent1, Tour parent2) {
        Tour child = new Tour();

        int startPos = (int) (Math.random() * TourManager.numberOfCities());
        int endPos = (int) (Math.random() * TourManager.numberOfCities());
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getTour().get(i));
            } else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getTour().get(i));
                }
            }
        }

        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            if (!child.containsCity(parent2.getTour().get(i))) {
                for (int ii = 0; ii < TourManager.numberOfCities(); ii++) {
                    if (child.getTour().get(ii) == null) {
                        child.setCity(ii, parent2.getTour().get(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    private static Tour rouletteWheelSelection(Population pop) {
        double[] cumulativeFitness = new double[pop.getTours().length];
        cumulativeFitness[0] = pop.getTours()[0].getFitness();

        for (int i = 1; i < pop.getTours().length; i++) {
            double fitness = pop.getTours()[i].getFitness();
            cumulativeFitness[i] = cumulativeFitness[i - 1] + fitness;
        }

        double randFit = Math.random() * cumulativeFitness[cumulativeFitness.length - 1];
        int index = Arrays.binarySearch(cumulativeFitness, randFit);
        if (index < 0) {
            index = Math.abs(index + 1);
        }
        return pop.getTours()[index];
    }
}
