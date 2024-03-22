import java.util.ArrayList;
import java.util.HashMap;

public class Securin2 {

    public static void main(String[] args) {
        int[] Die_A = {1, 2, 3, 4, 5, 6};
        int[] Die_B = {1, 2, 3, 4, 5, 6};

        System.out.println("Original Dice A = " + arrayToString(Die_A));
        System.out.println("Original Dice B = " + arrayToString(Die_B));

        int total_combinations = Die_A.length * Die_B.length;

        HashMap<Integer, Integer> original_sums = new HashMap<>();
        for (int i = 0; i < Die_A.length; i++) {
            for (int j = 0; j < Die_B.length; j++) {
                int temp = Die_A[i] + Die_B[j];
                int val = original_sums.getOrDefault(temp, 0) + 1;
                original_sums.put(temp, val);
            }
        }

        HashMap<Integer, Double> original_probabilities = new HashMap<>();
        for (int key : original_sums.keySet()) {
            double val = (double) original_sums.get(key) / total_combinations;
            original_probabilities.put(key, val);
        }

        System.out.println("\nOriginal Probabilities: ");
        for (int key : original_probabilities.keySet()) {
            System.out.println("P(Sum = " + key + ")  Occurrence = " + original_sums.get(key) +
                    "  Probability = " + original_probabilities.get(key));
        }
        System.out.println();

        ArrayList<int[]> diceA = new ArrayList<>();
        ArrayList<int[]> diceB = new ArrayList<>();

        findDiceAPossibility(1, new int[]{1}, diceA);
        findDiceBPossibility(1, new int[]{1}, diceB);

        int[][] transformedDice = unDoomDice(Die_A, Die_B, original_sums);

        System.out.println("Transformed Dice A -> " + arrayToString(transformedDice[0]));
        System.out.println("Transformed Dice B -> " + arrayToString(transformedDice[1]));

        System.out.println("\nProbability of Dice After Transforming:");
        for (int key : original_sums.keySet()) {
            System.out.println("P(Sum = " + key + ") Occurrence = " + original_sums.get(key) +
                    "  Probability = " + ((double) original_sums.get(key) / total_combinations));
        }
    }

    public static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void findDiceAPossibility(int number, int[] temp, ArrayList<int[]> diceA) {
        if (number > 4 || temp.length > 6)
            return;
        if (temp.length == 6) {
            diceA.add(temp.clone());
            return;
        }
        for (int i = number; i <= 5; i++) {
            int[] newTemp = new int[temp.length + 1];
            System.arraycopy(temp, 0, newTemp, 0, temp.length);
            newTemp[temp.length] = i;
            findDiceAPossibility(i, newTemp, diceA);
        }
    }

    public static void findDiceBPossibility(int number, int[] temp, ArrayList<int[]> diceB) {
        if (number > 11 || temp.length > 6)
            return;
        if (temp.length == 6) {
            diceB.add(temp.clone());
            return;
        }
        for (int i = number + 1; i <= 12; i++) {
            int[] newTemp = new int[temp.length + 1];
            System.arraycopy(temp, 0, newTemp, 0, temp.length);
            newTemp[temp.length] = i;
            findDiceBPossibility(i, newTemp, diceB);
        }
    }

    public static int[][] unDoomDice(int[] Die_A, int[] Die_B, HashMap<Integer, Integer> original_sums) {
        int[][] transformedDice = new int[2][6];
        System.out.println("Doomed Dice A = " + arrayToString(transformedDice[0]));
        System.out.println("Doomed Dice B = " + arrayToString(transformedDice[1]) + "\n");

        ArrayList<int[]> diceA = new ArrayList<>();
        ArrayList<int[]> diceB = new ArrayList<>();

        findDiceAPossibility(1, new int[]{1}, diceA);
        findDiceBPossibility(1, new int[]{1}, diceB);

        System.out.println("Undooming Dice A and Dice B...");
        for (int[] i : diceA) {
            for (int[] j : diceB) {
                HashMap<Integer, Integer> tdic = new HashMap<>();
                for (int k = 0; k < i.length; k++) {
                    for (int l = 0; l < j.length; l++) {
                        int sum = i[k] + j[l];
                        int val = tdic.getOrDefault(sum, 0) + 1;
                        tdic.put(sum, val);
                    }
                }
                int c = 0;
                for (int key : tdic.keySet()) {
                    if (!original_sums.containsKey(key) || tdic.get(key) != original_sums.get(key)) {
                        break;
                    } else {
                        c++;
                    }
                }
                if (c == 11) {
                    transformedDice[0] = i;
                    transformedDice[1] = j;
                    return transformedDice;
                }
            }
        }
        return transformedDice;
    }
}


