class Solution {
    public void duplicateZeros(int[] arr) {
         int[] dest =  new int[arr.length];

         int s= 0 ;
         int d = 0;

         while(s < arr.length){
            if(arr[s] == 0){
                if(d < arr.length){
                dest[d] = 0;
                }
                d +=1;
                if(d < arr.length){
                dest[d] = 0;
                }
            } else {
                if(d < arr.length){
                dest[d] = arr[s];
            }
            }
            d++;
            s++;
         }
            for(int i = 0 ; i< arr.length ; i++){
                arr[i] = dest[i];
            }

    }
    }

