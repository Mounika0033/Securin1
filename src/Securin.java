import java.util.HashMap;

public class Securin {


        public static void main(String[] args) {
            int[] A = {1, 2, 3, 4, 5, 6};
            int[] B = {1, 2, 3, 4, 5, 6};

            int total = count(A, B);
            System.out.println("Total Combinations: " + total);

            HashMap<Integer, Integer> dist = calculateDist(A, B);

            int totalCombinations2 = A.length * B.length;

            System.out.println("Distribution of all possible combinations & their probabilities:");
            for (int i= 2; i <= 12; i++) {
                int frequency = dist.getOrDefault(i, 0);
                double probability = (double) frequency / totalCombinations2;
                System.out.println("Sum " + i + ": " + frequency + " occurrences, Probability: " + probability);
            }
        }

        public static int count(int[] dieA, int[] dieB) {
            int count = 0;
            for (int i = 0; i < dieA.length; i++) {
                for (int j = 0; j < dieB.length; j++) {
                    count++;
                }
            }
            return count;
        }

        public static HashMap<Integer, Integer> calculateDist(int[] dieA, int[] dieB) {
            HashMap<Integer, Integer> dist1 = new HashMap<>();
            for (int i = 0; i < dieA.length; i++) {
                for (int j = 0; j < dieB.length; j++) {
                    int sum = dieA[i] + dieB[j];
                    dist1.put(sum, dist1.getOrDefault(sum, 0) + 1);
                }
            }
            return dist1;
        }
    }


