// Final optimized version with Edge Case handling
public class rotate_2d_matrix_clockwise {
    public static void main(String[] args) {
        // Special Case: 2x2 Matrix (All elements are diagonals, so nothing should move)
        int[][] two_by_two = {
            {1, 2},
            {3, 4}
        };

        int[][] odd_matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Testing 2x2 (Every element is a diagonal):");
        printMatrix(two_by_two);
        rotateMatrixClockwiseFixedDiagonals(two_by_two);
        System.out.println("Result (Should be identical):");
        printMatrix(two_by_two);

        System.out.println("\nTesting 3x3:");
        printMatrix(odd_matrix);
        rotateMatrixClockwiseFixedDiagonals(odd_matrix);
        System.out.println("Result:");
        printMatrix(odd_matrix);
    }

    public static void rotateMatrixClockwiseFixedDiagonals(int[][] matrix) {
        // Guard Clauses (Professional Edge Case Handling)
        if (matrix == null || matrix.length <= 1) return;
        if (matrix.length != matrix[0].length) return; // Square matrix verification

        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                
                // Check if any of the 4 cells in the swap cycle are on a diagonal
                if (isDiagonal(i, j, n) || isDiagonal(j, n - 1 - i, n) || 
                    isDiagonal(n - 1 - i, n - 1 - j, n) || isDiagonal(n - 1 - j, i, n)) {
                    continue;
                }

                // In-place 4-way cyclical swap
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    private static boolean isDiagonal(int r, int c, int n) {
        return (r == c) || (r + c == n - 1);
    }

    public static void printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}
