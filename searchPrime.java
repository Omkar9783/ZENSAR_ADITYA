import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class searchPrime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start, end;

        if (args.length >= 2) {
            start = Integer.parseInt(args[0]);
            end = Integer.parseInt(args[1]);
        } else {
            System.out.print("Enter start and end: ");
            start = sc.nextInt();
            end = sc.nextInt();
        }

        System.out.println("\n--- Prime Finding Algorithms: Ranked Best to Worst ---");

        System.out.println("\n[RANK 1] Linear Sieve (The Gold Standard):");
        linearSieve(start, end);

        System.out.println("\n[RANK 2] Sieve of Eratosthenes (Optimized Primitive):");
        eratosthenesSieve(start, end);

        System.out.println("\n[RANK 3] Boolean Array Sieve (Basic Primitive):");
        booleanArraySieve(start, end);

        System.out.println("\n[RANK 4] Boolean List Sieve (Object-based List):");
        booleanListSieve(start, end);

        System.out.println("\n[RANK 5] Normal Method (Trial Division):");
        normalSieve(start, end);

        System.out.println("\n[RANK 6] Manual Deletion Sieve (Worst - Physical Shifts):");
        manualDeletionSieve(start, end);
    }

    /**
     * RANK 1: Linear Sieve
     * TC: O(N) - Every number is visited exactly once.
     * SC: O(N)
     */
    public static void linearSieve(int start, int end) {
        if (end < 2) return;
        int[] minPrime = new int[end + 1];
        ArrayList<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= end; i++) {
            if (minPrime[i] == 0) {
                minPrime[i] = i;
                primes.add(i);
            }
            for (int p : primes) {
                if (p > minPrime[i] || (long)i * p > end) break;
                minPrime[i * p] = p;
            }
        }

        for (int p : primes) {
            if (p >= start) System.out.print(p + " ");
        }
        System.out.println();
    }

    /**
     * RANK 2: Sieve of Eratosthenes (Optimized)
     * TC: O(N log log N), SC: O(N)
     * This uses primitive boolean array and p*p optimization.
     */
    public static void eratosthenesSieve(int start, int end) {
        if (end < 2) return;
        boolean[] isPrime = new boolean[end + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int p = 2; p * p <= end; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= end; i += p)
                    isPrime[i] = false;
            }
        }

        for (int i = Math.max(start, 2); i <= end; i++) {
            if (isPrime[i]) System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * RANK 3: Boolean Array Sieve (Basic)
     * TC: O(N log log N), SC: O(N)
     * Same complexity as Rank 2, but slightly less optimized implementation.
     */
    public static void booleanArraySieve(int start, int end) {
        if (end < 2) return;
        boolean[] isPrime = new boolean[end + 1];
        for (int i = 0; i <= end; i++) isPrime[i] = true;
        
        isPrime[0] = false;
        isPrime[1] = false;

        for (int p = 2; p * p <= end; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= end; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        for (int i = Math.max(start, 2); i <= end; i++) {
            if (isPrime[i]) System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * RANK 4: Boolean List Sieve
     * TC: O(N log log N), SC: O(N)
     * High overhead due to it being an Object List instead of primitive array.
     */
    public static void booleanListSieve(int start, int end) {
        if (end < 2) return;
        List<Boolean> isPrime = new ArrayList<>(end + 1);
        for (int i = 0; i <= end; i++) isPrime.add(true);
        isPrime.set(0, false);
        isPrime.set(1, false);

        for (int p = 2; p * p <= end; p++) {
            if (isPrime.get(p)) {
                for (int i = p * p; i <= end; i += p) {
                    isPrime.set(i, false);
                }
            }
        }

        for (int i = Math.max(start, 2); i <= end; i++) {
            if (isPrime.get(i)) System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * RANK 5: Normal Trial Division
     * TC: O(N^2), SC: O(1)
     * Slow because it checks every single divisor.
     */
    public static void normalSieve(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (i <= 1) continue;
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * RANK 6: Manual Deletion Sieve
     * TC: O(N^2), SC: O(N)
     * Worst performance because it physically moves every element in the list.
     */
    public static void manualDeletionSieve(int start, int end) {
        if (end < 2) return;
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 2; i <= end; i++) nums.add(i);

        for (int i = 0; i < nums.size(); i++) {
            int currentPrime = nums.get(i);
            Iterator<Integer> it = nums.iterator();
            while (it.hasNext()) {
                int nextVal = it.next();
                if (nextVal > currentPrime && nextVal % currentPrime == 0) {
                    it.remove();
                }
            }
            if (currentPrime * currentPrime > end) break;
        }

        for (int n : nums) {
            if (n >= start) System.out.print(n + " ");
        }
        System.out.println();
    }
}
