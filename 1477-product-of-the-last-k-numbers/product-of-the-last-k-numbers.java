import java.util.*;

class ProductOfNumbers {

    List<Integer> prefix;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1); 
    }

    public void add(int num) {
        if (num == 0) {
            
            prefix = new ArrayList<>();
            prefix.add(1);
        } else {
            int lastProduct = prefix.get(prefix.size() - 1);
            prefix.add(lastProduct * num);
        }
    }

    public int getProduct(int k) {
        int n = prefix.size();

        
        if (k >= n) return 0;

        int last = prefix.get(n - 1);
        int before = prefix.get(n - 1 - k);

        return last / before;
    }
}