class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        int[] right = new int[n];
        int[] down = new int[n];

        right[0] = 1;
        down[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                int fromLeft = j > 0 ? right[j - 1] : 0;
                int fromTop = i > 0 ? down[j] : 0;

                int total = (int) (((long) fromLeft + fromTop) % MOD);

                if (i == m - 1 && j == n - 1) {
                    return total;
                }

                if (grid[i][j] == 0) {
                    right[j] = total;
                    down[j] = total;
                } else {
                    right[j] = fromTop;
                    down[j] = fromLeft;
                }
            }
        }

        return 0;
    }
}
