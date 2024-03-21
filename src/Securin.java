import java.util.HashMap;

public class Securin {


        public static void main(String[] args) {
            int[] dieA = {1, 2, 3, 4, 5, 6};
            int[] dieB = {1, 2, 3, 4, 5, 6};

            int totalCombinations = countCombinations(dieA, dieB);
            System.out.println("Total combinations: " + totalCombinations);

            HashMap<Integer, Integer> distribution = calculateDistribution(dieA, dieB);

            int totalCombinations2 = dieA.length * dieB.length;

            System.out.println("Distribution of all possible combinations and their probabilities:");
            for (int sum = 2; sum <= 12; sum++) {
                int frequency = distribution.getOrDefault(sum, 0);
                double probability = (double) frequency / totalCombinations2;
                System.out.println("Sum " + sum + ": " + frequency + " occurrences, Probability: " + probability);
            }
        }

        public static int countCombinations(int[] dieA, int[] dieB) {
            int count = 0;
            for (int i = 0; i < dieA.length; i++) {
                for (int j = 0; j < dieB.length; j++) {
                    count++;
                }
            }
            return count;
        }

        public static HashMap<Integer, Integer> calculateDistribution(int[] dieA, int[] dieB) {
            HashMap<Integer, Integer> distribution = new HashMap<>();
            for (int i = 0; i < dieA.length; i++) {
                for (int j = 0; j < dieB.length; j++) {
                    int sum = dieA[i] + dieB[j];
                    distribution.put(sum, distribution.getOrDefault(sum, 0) + 1);
                }
            }
            return distribution;
        }
    }


