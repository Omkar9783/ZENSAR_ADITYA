import java.util.Scanner;

public class dsa1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int MOD = 1000000007;

        // Total elements aur operations ka count le rahe hain
        int totalElements = sc.nextInt();
        int queryCount = sc.nextInt();

        // Array mein input values store kar rahe hain
        int[] elements = new int[totalElements];
        for (int i = 0; i < totalElements; i++) {
            elements[i] = sc.nextInt();
        }

        long finalAnswer = 0;

        // Har ek query ko process kar rahe hain
        for (int q = 0; q < queryCount; q++) {

            int queryType = sc.nextInt();
            int leftIndex = sc.nextInt() - 1; // 0-based indexing ke liye convert kiya
            int rightIndex = sc.nextInt() - 1;

            if (queryType == 1) {
                // Range update kar rahe hain logic ke hisaab se
                int valueAtLeft = elements[leftIndex];
                for (int i = leftIndex; i <= rightIndex; i++) {
                    elements[i] = (i - leftIndex + 1) * valueAtLeft;
                }

            } else {
                // Range ka sum calculate kar rahe hain
                long currentSum = 0;
                for (int i = leftIndex; i <= rightIndex; i++) {
                    currentSum += elements[i];
                }
                finalAnswer = (finalAnswer + currentSum) % MOD;
                // Debugging ke liye output
                System.out.println("Sum: " + currentSum + ", Answer: " + finalAnswer);
            }
        }

        // Final calculated answer print kar rahe hain
        System.out.println(finalAnswer);

        sc.close();
    }
}
