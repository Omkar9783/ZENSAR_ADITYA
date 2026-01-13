    import java.util.Scanner;

    public class dsa1 {

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            int MOD = 1000000007;

            // Total elements aur operations ka count le rahe hain
            System.out.println("enter the size of array");
            int totalElements = sc.nextInt();

            // Array mein input values store kar rahe hain
            int[] elements = new int[totalElements + 1];
            for (int i = 0; i < totalElements; i++) {
                System.out.println("enter element " + (i + 1));
                elements[i] = sc.nextInt();
            }

            System.out.println("enter total queries");
            int queryCount = sc.nextInt();

            long finalAnswer = 0;

            // Har ek query ko process kar rahe hain
            for (int q = 0; q < queryCount; q++) {

                System.out.println("enter the type of query " + (q + 1));
                int queryType = sc.nextInt();
                
                if (queryType != 1 && queryType != 2) {
                    System.out.println("Invalid query type! Please enter 1 or 2.");
                    continue;
                }

                System.out.println("enter l");
                int l = sc.nextInt();
                System.out.println("enter r");
                int r = sc.nextInt();

                if (l < 1 || r > totalElements || l > r) {
                    System.out.println("Invalid range! Please ensure 1 <= l <= r <= " + totalElements);
                    continue;
                }

                if (queryType == 1) {
                    // Range update kar rahe hain logic ke hisaab se
                    int valueAtLeft = elements[l];
                    for (int i = l; i <= r; i++) {
                        elements[i] = (i - l + 1) * valueAtLeft;
                    }

                } else if (queryType == 2) {
                    // Range ka sum calculate kar rahe hain
                    long currentSum = 0;
                    for (int i = l; i <= r; i++) {
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
