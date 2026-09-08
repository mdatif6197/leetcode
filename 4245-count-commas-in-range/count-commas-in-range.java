class Solution {
    public int countCommas(int n) {
        int count =0;

        for(int i =1000 ; i<= n ;i++){
            count +=( i >= 1000 ? 1 : 0);
            count +=(i >= 1000000 ? 1 :0);
            count +=( i >= 1000000000 ? 1 : 0);
        }
        return count;
    }
}