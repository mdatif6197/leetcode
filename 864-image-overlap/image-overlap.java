

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {

        int n = img1.length;
        int max = 0;

        // Try every possible shift
        for (int dr = -n + 1; dr < n; dr++) {

            for (int dc = -n + 1; dc < n; dc++) {

                int count = 0;

                // Check every cell of img1
                for (int r = 0; r < n; r++) {

                    for (int c = 0; c < n; c++) {

                        if (img1[r][c] == 1) {

                            int nr = r + dr;
                            int nc = c + dc;

                            // Check if shifted position is inside img2
                            if (nr >= 0 && nr < n &&
                                nc >= 0 && nc < n &&
                                img2[nr][nc] == 1) {

                                count++;
                            }
                        }
                    }
                }

                max = Math.max(max, count);
            }
        }

        return max;
    }
}