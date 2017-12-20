package tsp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TSP_GA {
    public static void main(String[] args) throws IOException {
        int cf = 0;
        BufferedReader br = new BufferedReader(new FileReader("/home/leduylinh209/IdeaProjects/GA_TSP/TSP/src/TSP_Instance/berlin52.tsp"));
        try {
            String line = br.readLine();
            while (line != null){
                  if (line.equals("NODE_COORD_SECTION")){
                      cf = 1;
                  }
                  if (cf == 0){
                      System.out.println(line);
                  } else if (!line.equals("NODE_COORD_SECTION") && !line.equals("EOF")){
                      String[] cityPart = line.split(" ");
                      int ident = (int) Double.parseDouble(cityPart[0]);
                      int x = (int) Double.parseDouble(cityPart[1]);
                      int y = (int) Double.parseDouble(cityPart[2]);
                      City city = new City(ident, x, y);
                      TourManager.addCity(city);
                  }
                  line = br.readLine();
            }
        } catch (IOException e) {
            br.close();
        }

        Population pop = new Population(50, true);
        System.out.println("Initial distance: " + pop.getBestTour().getDistance());

        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 200; i++) {
            pop = GA.evolvePopulation(pop);
        }

        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getBestTour().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getBestTour());
    }
}
