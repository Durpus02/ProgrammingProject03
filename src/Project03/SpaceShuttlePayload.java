
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SpaceShuttlePayload {
    public static void main(String[] args) {
        List<Experiment> experiments = Arrays.asList(
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
        experiments.sort(Comparator.comparingInt(e -> e.weight));
        
        List<Experiment> selectedExperimentsW = new ArrayList<>();
        int totalWeight = 0;
        int totalRating = 0;
        int weightLimit = 700;

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
        System.out.println("Selected Experiments by weight:");
        for (Experiment exp : selectedExperimentsW) {
            System.out.println(exp.number + " " + exp.name + " " + exp.weight + "kg " + exp.rating);
        }

        System.out.println("\nTotal Weight: " + totalWeight + "kg");
        System.out.println("Total Rating: " + totalRating);

        // Sort experiments by rating (decending)
        experiments.sort(Comparator.comparingInt(e -> -e.rating));
        
        List<Experiment> selectedExperimentsR = new ArrayList<>();
        totalWeight = 0;
        totalRating = 0;

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
        System.out.println("\n\nSelected Experiments by rating:");
        for (Experiment exp : selectedExperimentsR) {
            System.out.println(exp.number + " " + exp.name + " " + exp.weight + "kg " + exp.rating);
        }

        System.out.println("\nTotal Weight: " + totalWeight + "kg");
        System.out.println("Total Rating: " + totalRating);
    }
}

