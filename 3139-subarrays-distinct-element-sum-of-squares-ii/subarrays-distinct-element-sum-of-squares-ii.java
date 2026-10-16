
class Solution {
    private static final long MOD = 1_000_000_007L;

    private int n;
    private long[] sum;
    private long[] square;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        square = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];

            // Increase distinct counts for valid subarrays
            update(1, 0, n - 1, prev + 1, i);

            // Sum of squared distinct counts for subarrays ending at i
            ans = (ans + square[1]) % MOD;

            last[nums[i]] = i;
        }

        return (int) ans;
    }

    private void apply(int node, int left, int right, long val) {
        long len = right - left + 1L;

        square[node] = (
            square[node] + 2L * val % MOD * sum[node] % MOD
            + val * val % MOD * len % MOD
        ) % MOD;

        sum[node] = (sum[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void push(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;

        apply(node * 2, left, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, right, lazy[node]);

        lazy[node] = 0;
    }

    private void update(int node, int left, int right,
                        int ql, int qr) {
        if (ql > right || qr < left || ql > qr) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right, 1);
            return;
        }

        push(node, left, right);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, ql, qr);
        update(node * 2 + 1, mid + 1, right, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        square[node] =
            (square[node * 2] + square[node * 2 + 1]) % MOD;
    }
}
