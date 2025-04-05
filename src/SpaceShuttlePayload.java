
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SpaceShuttlePayload {
    public static void main(String[] args) {
        List<Experiment> experiments = Arrays.asList(
            // The table of possible experiments to be considered as payload
            new Experiment(1, "Cloud Patterns", 36, 5),
            new Experiment(2, "Solar Flares", 264, 9),
            new Experiment(3, "Solar Power", 188, 6),
            new Experiment(4, "Binary Stars", 203, 8),
            new Experiment(5, "Relativity", 104, 8),
            new Experiment(6, "Seed Viability", 7, 4),
            new Experiment(7, "Sun Spots", 90, 2),
            new Experiment(8, "Mice Tumors", 65, 8),
            new Experiment(9, "Microgravity Plant Growth", 75, 5),
            new Experiment(10, "Micrometeorites", 170, 9),
            new Experiment(11, "Cosmic Rays", 80, 7),
            new Experiment(12, "Yeast Fermentation", 27, 4)
        );


        // Sort experiments by weight (ascending)
        experiments.sort(Comparator.comparingInt(exp -> exp.weight));
        
        List<Experiment> selectedExperimentsW = new ArrayList<>();
        int totalWeight = 0;
        int totalRating = 0;
        int weightLimit = 700;

        // Saves a list of experiments whose total weight is less than 700kg
        for (Experiment exp : experiments) {
            if (totalWeight + exp.weight <= weightLimit) {
                selectedExperimentsW.add(exp);
                totalWeight += exp.weight;
                totalRating += exp.rating;
            } else {
                break;
            }
        }

        // Output the results
        System.out.println("Selected Experiments by weight (lightest to heaviest):");
        for (Experiment exp : selectedExperimentsW) {
            System.out.println(exp.number + " " + exp.name + " " + exp.weight + "kg " + exp.rating);
        }

        System.out.println("\nTotal Weight: " + totalWeight + "kg");
        System.out.println("Total Rating: " + totalRating);


        // Sort experiments by rating (decending)
        experiments.sort(Comparator.comparingInt(exp -> -exp.rating));
        
        List<Experiment> selectedExperimentsR = new ArrayList<>();
        totalWeight = 0;
        totalRating = 0;

        // Saves a list of experiments whose total weight is less than 700kg
        for (Experiment exp : experiments) {
            if (totalWeight + exp.weight <= weightLimit) {
                selectedExperimentsR.add(exp);
                totalWeight += exp.weight;
                totalRating += exp.rating;
            } else {
                break;
            }
        }

        // Output the results
        System.out.println("\n\nSelected Experiments by rating (highest to smallest):");
        for (Experiment exp : selectedExperimentsR) {
            System.out.println(exp.number + " " + exp.name + " " + exp.weight + "kg " + exp.rating);
        }

        System.out.println("\nTotal Weight: " + totalWeight + "kg");
        System.out.println("Total Rating: " + totalRating);


        // Sort experiments by rating/weight ratio (ascending)
        experiments.sort(Comparator.comparingDouble(Experiment::ratingPerWeight));

        List<Experiment> selectedExperimentsR_W = new ArrayList<>();
        totalWeight = 0;
        totalRating = 0;

        // Saves a list of experiments whose total weight is less than 700kg
        for (Experiment exp : experiments) {
            if (totalWeight + exp.weight <= weightLimit) {
                selectedExperimentsR_W.add(exp);
                totalWeight += exp.weight;
                totalRating += exp.rating;
            } else {
                break;
            }
        }

        // Output the results
        System.out.println("\n\nSelected Experiments by rating/weight ratio:");
        for (Experiment exp : selectedExperimentsR_W) {
            System.out.println(exp.number + " " + exp.name + " " + exp.weight + "kg " + exp.rating);
        }

        System.out.println("\nTotal Weight: " + totalWeight + "kg");
        System.out.println("Total Rating: " + totalRating);


        // Brute-force solution out of 4096 possible subsets
        List<Experiment> bestSubset = new ArrayList<>();
        int maxRating = 0;
        int bestWeight = 0;
        int n = experiments.size();

        // Generates all 2^n (4096) possible subsets
        // Each i is treated as a bitmask to represent a subset
        for (int i = 0; i < (1 << n); i++) { 
            List<Experiment> subset = new ArrayList<>();
            totalWeight = 0;
            totalRating = 0;

            // This checks each bit in i. If bit j is on, that experiment added to subset
            for (int j = 0; j < n; j++) {
                if((i & (1 << j)) != 0) {
                    Experiment exp = experiments.get(j);
                    totalWeight += exp.weight;
                    totalRating += exp.rating;
                    subset.add(exp);
                }
            }

            // Saves subsets less than 700kg and with a new best max rating
            if (totalWeight <= weightLimit && totalRating > maxRating) {
                maxRating = totalRating;
                bestWeight = totalWeight;
                bestSubset = subset;
            }
        }

        // Output the results
        System.out.println("\n\nSubset of experiments with highest total rating:");
        for (Experiment exp : bestSubset) {
            System.out.println(exp.number + " " + exp.name + " " + exp.weight + "kg " + exp.rating);
        }

        System.out.println("\nTotal Weight: " + bestWeight + "kg");
        System.out.println("Total Rating: " + maxRating);
    }
}

