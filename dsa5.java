import java.util.Scanner;

public class dsa5 {

    static int validArrangementCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Total members ka input le rahe hain
        int totalMembers = sc.nextInt();

        // President ke alawa baaki logon ki array bana rahe hain
        char[] otherMembers = new char[totalMembers - 1];
        otherMembers[0] = 'M';
        for (int i = 1; i < totalMembers - 1; i++) {
            otherMembers[i] = (char) ('A' + i - 1); // A, B, C... assigned kar rahe hain
        }

        char[] tableArrangement = new char[totalMembers];

        tableArrangement[0] = 'P'; // President ko fix kar rahe hain
        boolean[] isMemberDataUsed = new boolean[otherMembers.length];

        permute(otherMembers, isMemberDataUsed, tableArrangement, 1);

        System.out.println(validArrangementCount);
        sc.close();
    }

    static void permute(char[] membersList, boolean[] usedFlags, char[] currentSeating, int currentPos) {

        // Agar saare seats fill ho gaye hain to check karo
        if (currentPos == currentSeating.length) {
            if (isValidArrangement(currentSeating)) {
                validArrangementCount++;
            }
            return;
        }

        // Backtracking use karke permutations generate kar rahe hain
        for (int i = 0; i < membersList.length; i++) {
            if (!usedFlags[i]) {
                usedFlags[i] = true;
                currentSeating[currentPos] = membersList[i];

                permute(membersList, usedFlags, currentSeating, currentPos + 1);

                usedFlags[i] = false; // Backtrack kar rahe hain
            }
        }
    }

    static boolean isValidArrangement(char[] seatingConfig) {

        int size = seatingConfig.length;
        int presidentIndex = 0;
        int ministerIndex = -1;

        // Prime Minister ki position find kar rahe hain
        for (int i = 0; i < size; i++) {
            if (seatingConfig[i] == 'M') {
                ministerIndex = i;
                break;
            }
        }

        // Table circular hai to adjacency check kar rahe hain
        if (Math.abs(presidentIndex - ministerIndex) == 1)
            return true;

        // Circular condition check kar rahe hain last aur first ke beech
        if (presidentIndex == 0 && ministerIndex == size - 1)
            return true;

        return false;
    }
}
