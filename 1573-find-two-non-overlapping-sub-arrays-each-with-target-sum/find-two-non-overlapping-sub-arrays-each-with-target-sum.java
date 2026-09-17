class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = 1000000;

        int[] best = new int[n];

        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;
        int answer = INF;
        int minLength = INF;

        for (int i = 0; i < n; i++) {

            sum += arr[i];

        
            if (map.containsKey(sum - target)) {

                int j = map.get(sum - target);

                int length = i - j;

                
                if (j >= 0 && best[j] != INF) {
                    answer = Math.min(answer, length + best[j]);
                } else if (j == -1) {
                    
                }

                minLength = Math.min(minLength, length);
            }

            
            if (i > 0) {
                best[i] = Math.min(best[i - 1], minLength);
            } else {
                best[i] = minLength;
            }

            map.put(sum, i);
        }

        return answer == INF ? -1 : answer;
    }
}