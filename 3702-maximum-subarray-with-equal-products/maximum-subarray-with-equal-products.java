
class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            long product = 1;
            long lcm = 1;
            int gcd = 0;

            for (int j = i; j < n; j++) {
                product *= nums[j];

                gcd = gcd(gcd, nums[j]);

                lcm = (lcm / gcd((int) lcm, nums[j])) * nums[j];

                if (product == lcm * gcd) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
