import java.util.HashMap;
import java.util.Map;

public class Securin2 {


        // Method to calculate probabilities of obtaining different sums
        public static Map<Integer, Double> getProbabilities(int[] dice) {
            Map<Integer, Integer> counts = new HashMap<>();
            for (int face : dice) {
                for (int i = 1; i <= 6; i++) {
                    int sum = face + i;
                    counts.put(sum, counts.getOrDefault(sum, 0) + 1);
                }
            }

            Map<Integer, Double> probabilities = new HashMap<>();
            int totalRolls = dice.length * 6;
            for (int key : counts.keySet()) {
                probabilities.put(key, (double) counts.get(key) / totalRolls);
            }
            return probabilities;
        }

        // Method to undoom the dice
        public static int[][] undoomDice(int[] dieA, int[] dieB) {
            // Step 1: Get original probabilities
            Map<Integer, Double> originalProbabilities = getProbabilities(dieA);

            // Step 2: Determine probabilities we need to maintain
            Map<Integer, Double> requiredProbabilities = new HashMap<>(originalProbabilities);

            // Step 3: Reattach spots to dice faces
            // For Die A, we need to ensure no face has more than 4 spots
            int[] newDieA = new int[dieA.length];
            for (int i = 0; i < dieA.length; i++) {
                newDieA[i] = Math.min(4, dieA[i]);
            }

            // For Die B, we can keep the same configuration as Die A since it can have more than 6 spots
            int[] newDieB = newDieA.clone();

            // Calculate new probabilities
            Map<Integer, Double> newProbabilities = getProbabilities(newDieA);

            // Ensure the new probabilities match the required probabilities
            for (int key : requiredProbabilities.keySet()) {
                double diff = Math.abs(newProbabilities.get(key) - requiredProbabilities.get(key));
                if (diff > 1e-9) {
                    System.out.println("Impossible to maintain probabilities");
                    return null;
                }
            }

            return new int[][] {newDieA, newDieB};
        }

        public static void main(String[] args) {
            int[] dieA = {1, 2, 3, 4,5,6}; // Example Die A configuration
            int[] dieB = {1, 2, 3,4,5,6}; // Example Die B configuration

            int[][] newDiceConfigurations = undoomDice(dieA, dieB);
            if (newDiceConfigurations != null) {
                System.out.println("New Die A: ");
                for (int face : newDiceConfigurations[0]) {
                    System.out.print(face + " ");
                }
                System.out.println();

                System.out.println("New Die B: ");
                for (int face : newDiceConfigurations[1]) {
                    System.out.print(face + " ");
                }
                System.out.println();
            }
        }
    }


