import java.util.Scanner;

public class dsa6 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Number aur repetitions input le rahe hain
        long inputNumber = sc.nextLong();
        int repetitionCount = sc.nextInt();

        // Diye gaye iterations tak digit sum calculate kar rahe hain
        for (int i = 0; i < repetitionCount; i++) {
            inputNumber = computeDigitSum(inputNumber);
        }

        System.out.println(inputNumber);

        sc.close();
    }

    // Ek number ke digits ka sum calculate karne ka function
    static long computeDigitSum(long number) {
        long currentSum = 0;

        while (number > 0) {
            currentSum += number % 10;
            number /= 10;
        }

        return currentSum;
    }
}
