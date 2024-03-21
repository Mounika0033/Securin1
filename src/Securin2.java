import java.util.HashMap;
import java.util.Map;
public class Securin2 {

    public static Map<Integer, Double> getProbs(int[] dice) {
            Map<Integer, Integer> count = new HashMap<>();
            for (int face : dice) {
                for (int i = 1; i <= 6; i++) {
                    int sum = face + i;
                    count.put(sum, count.getOrDefault(sum, 0) + 1);
                }
            }
            Map<Integer, Double> probs = new HashMap<>();
            int totRolls = dice.length * 6;
            for (int key : count.keySet()) {
                probs.put(key, (double) count.get(key) / totRolls);
            }
            return probs;
        }

        public static int[][] undoom(int[] dieA, int[] dieB) {

            Map<Integer, Double> originalProbs = getProbs(dieA);


            Map<Integer, Double> requiredProbabilities = new HashMap<>(originalProbs);


            int[] newDieA = new int[dieA.length];
            for (int i = 0; i < dieA.length; i++) {
                newDieA[i] = Math.min(4, dieA[i]);
            }


            int[] newDieB = newDieA.clone();
            Map<Integer, Double> newProbabilities = getProbs(newDieA);

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
            int[] A = {1, 2, 3, 4,5,6};
            int[] B = {1, 2, 3,4,5,6};

            int[][] newConfigurations = undoom(A, B);
            if (newConfigurations != null) {
                System.out.println("New Die A: ");
                for (int face : newConfigurations[0]) {
                    System.out.print(face + " ");
                }
                System.out.println();

                System.out.println("New Die B: ");
                for (int face : newConfigurations[1]) {
                    System.out.print(face + " ");
                }
                System.out.println();
            }
        }
    }


