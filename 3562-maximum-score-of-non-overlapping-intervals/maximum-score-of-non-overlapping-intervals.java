import java.util.*;

class Solution {

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0); // start
            a[i][1] = intervals.get(i).get(1); // end
            a[i][2] = intervals.get(i).get(2); // weight
            a[i][3] = i;                       // original index
        }

        // Sort by end time
        Arrays.sort(a, (x, y) -> {
            if (x[1] != y[1])
                return Integer.compare(x[1], y[1]);

            return Integer.compare(x[0], y[0]);
        });

        long[][] dp = new long[n + 1][5];

        List<Integer>[][] path = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                path[i][k] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= n; i++) {

            int start = a[i - 1][0];
            int weight = a[i - 1][2];
            int index = a[i - 1][3];

            // Don't take current interval
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = dp[i - 1][k];
                path[i][k] = new ArrayList<>(path[i - 1][k]);
            }

            // Previous compatible interval
            int p = findPrevious(a, i - 1, start);

            for (int k = 1; k <= 4; k++) {

                long newWeight = dp[p + 1][k - 1] + weight;

                List<Integer> newPath =
                    new ArrayList<>(path[p + 1][k - 1]);

                newPath.add(index);

                Collections.sort(newPath);

                if (newWeight > dp[i][k] ||
                    (newWeight == dp[i][k] &&
                     isLexicographicallySmaller(newPath, path[i][k]))) {

                    dp[i][k] = newWeight;
                    path[i][k] = newPath;
                }
            }
        }

        List<Integer> ans = path[n][4];

        Collections.sort(ans);

        int[] result = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }

    private int findPrevious(int[][] a, int right, int start) {

        int left = 0;
        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (a[mid][1] < start) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean isLexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}