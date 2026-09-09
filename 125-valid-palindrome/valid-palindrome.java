class Solution {
    public boolean isAlphaNumericCharacter(char c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z') ||
               (c >= '0' && c <= '9');
    }

    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {

            
            if (!isAlphaNumericCharacter(s.charAt(i))) {
                i++;
                continue;
            }

            
            if (!isAlphaNumericCharacter(s.charAt(j))) {
                j--;
                continue;
            }

            
            if (Character.toLowerCase(s.charAt(i)) !=
                Character.toLowerCase(s.charAt(j))) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}