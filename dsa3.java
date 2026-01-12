import java.util.Scanner;

public class dsa3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input le rahe hain days aur start day ke liye
        int totalDays = sc.nextInt();
        int startDayIndex = sc.nextInt(); // 0 = Sunday ... 6 = Saturday

        // Step 1: Check kar rahe hain ki kitne full weeks hain
        int fullWeeksCount = totalDays / 7;
        int totalSundays = fullWeeksCount;

        // Step 2: Remaining days calculate kar rahe hain
        int extraDays = totalDays % 7;

        // Step 3: Dekh rahe hain ki remaining days mein Sunday aata hai ya nahi
        int daysToNextSunday = (7 - startDayIndex) % 7;

        if (daysToNextSunday < extraDays) {
            totalSundays++;
        }

        // Output print kar rahe hain
        System.out.println(totalSundays);

        sc.close();
    }
}
