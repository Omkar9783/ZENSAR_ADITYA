import java.util.*;

public class dsa2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of elements:");
        if (!sc.hasNextInt())
            return;
        // Total elements input le rahe hain
        int totalElements = sc.nextInt();

        int[] numbers = new int[totalElements];

        System.out.println("Enter elements 1 by 1");
        // Array mein elements store kar rahe hain
        for (int i = 0; i < totalElements; i++) {
            numbers[i] = sc.nextInt();
        }

        int increaseCount = 1;

        // Loop start karke check kar rahe hain ki current element pichle se bada hai ya nahi
        for (int i = 1; i < totalElements; i++) {
            if (numbers[i] > numbers[i - 1]) {
                increaseCount++;
            }
        }

        // Final count print kar rahe hain
        System.out.println("Count is " + increaseCount);
        sc.close();
    }
}
