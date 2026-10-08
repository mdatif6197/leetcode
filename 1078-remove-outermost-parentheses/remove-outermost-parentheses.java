class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int balance = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                // Add '(' only if it is not the outermost one
                if (balance > 0) {
                    result.append(c);
                }
                balance++;
            } 
            else {
                balance--;

                // Add ')' only if it is not the outermost one
                if (balance > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}