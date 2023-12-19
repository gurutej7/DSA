package TwoPointers.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public static void main(String[] args) {
        int a[] = {1000000000,1000000000,1000000000,1000000000};
        int target = -294967296; //  Expected output: [] , for this case use long

        System.out.println(fourSum(a, target));
    }

    //Similar to Three Sum Refer the Three sum problem
     public static List<List<Integer>> fourSum(int[] a, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if(a.length <4) return ans;
        Arrays.sort(a);
        int n = a.length , left ,  right ;
        long sum = 0 ;
        for(int  i = 0 ; i< n-3 ; i++){
            if (i > 0 && a[i] == a[i - 1]) 
            // skip same result
            continue;
           
            for(int j = i+1 ; j<n-2 ; j++){
                if(j > i+1 && a[j] == a[j-1]) continue;
               
                left = j+1;
                right = n-1;
                while(left < right){
                    sum = (long)a[left]+a[right]+a[i]+a[j];
                    if(sum == target){
                        List<Integer> temp = new ArrayList<>();
                        temp.add(a[i]);
                        temp.add(a[j]);
                        temp.add(a[left]);
                        temp.add(a[right]);
                        ans.add(temp);
                        while(left < right && a[left]==temp.get(2))left++;
                        while(left < right && a[right] == temp.get(3))right--;
                    }
                    else if(sum > target) right--;
                    else left++;

                }
                
            }
        }

        return ans;    
    }
}
