import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Set<String> set = new HashSet<>();

        int left = 0;
        int right = 0;

        // Find minimum removals needed
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        dfs(s, 0, left, right, 0, new StringBuilder(), set);

        return new ArrayList<>(set);
    }

    private void dfs(String s, int index,
                     int leftRemove,
                     int rightRemove,
                     int balance,
                     StringBuilder current,
                     Set<String> set) {

        if (index == s.length()) {
            if (leftRemove == 0 &&
                rightRemove == 0 &&
                balance == 0) {

                set.add(current.toString());
            }
            return;
        }

        char c = s.charAt(index);

        // Remove current character
        if (c == '(' && leftRemove > 0) {
            dfs(s, index + 1,
                leftRemove - 1,
                rightRemove,
                balance,
                current,
                set);
        }

        if (c == ')' && rightRemove > 0) {
            dfs(s, index + 1,
                leftRemove,
                rightRemove - 1,
                balance,
                current,
                set);
        }

        // Keep current character
        if (c == '(') {

            current.append(c);

            dfs(s, index + 1,
                leftRemove,
                rightRemove,
                balance + 1,
                current,
                set);

            current.deleteCharAt(current.length() - 1);

        } else if (c == ')') {

            if (balance > 0) {

                current.append(c);

                dfs(s, index + 1,
                    leftRemove,
                    rightRemove,
                    balance - 1,
                    current,
                    set);

                current.deleteCharAt(current.length() - 1);
            }

        } else {

            // Normal character
            current.append(c);

            dfs(s, index + 1,
                leftRemove,
                rightRemove,
                balance,
                current,
                set);

            current.deleteCharAt(current.length() - 1);
        }
    }
}