import java.util.Scanner;

public class dsa4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input number le rahe hain
        int inputNumber = sc.nextInt();
        int digitProduct = 1;

        // Special case handle kar rahe hain agar number 0 hai
        if (inputNumber == 0) {
            digitProduct = 0;
        } else {
            // Har digit ko extract karke product nikal rahe hain
            while (inputNumber > 0) {
                int lastDigit = inputNumber % 10;
                digitProduct = digitProduct * lastDigit;
                inputNumber = inputNumber / 10;
            }
        }

        // Final product print kar rahe hain
        System.out.println(digitProduct);

        sc.close();
    }
}
