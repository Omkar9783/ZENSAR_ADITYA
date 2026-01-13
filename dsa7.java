import java.util.Scanner;

public class dsa7{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("enter target capacity of tank in units");
        int initialCapacityInput = sc.nextInt();
        
        System.out.println("enter the number of operations");
        int n = sc.nextInt();
        int[] operations = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("enter operation " + (i + 1));
            operations[i] = sc.nextInt();
        }

        int capacity = 0;
        int minRequired = 0;

        // Efficiently find minimum capacity required
        // Equivalent to the absolute value of the most negative cumulative sum encountered
        while (true) {
            int oil = capacity;
            boolean valid = true;

            for (int op : operations) {
                oil += op;

                if (oil < 0) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                minRequired = capacity;
                break;
            }

            capacity++;
        }

        System.out.println("Minimum tank capacity required: " + minRequired);
        
        if (initialCapacityInput < minRequired) {
            System.out.println("The provided capacity " + initialCapacityInput + " is insufficient. You need at least " + minRequired + " units.");
        } else {
            System.out.println("The provided capacity " + initialCapacityInput + " is sufficient.");
        }

        sc.close();
    }
}
