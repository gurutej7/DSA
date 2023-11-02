/* 1011. Capacity to Ship Packages WithIn D days
Example 1:
Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like 
this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and
 splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not 
 allowed.
 
 */

public class CapacityToShip {
    public static void main(String[] args) {
        int [] weights = {1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        System.out.println(shipWithinDays(weights, days));

    }
    public static int shipWithinDays(int[] weights, int days) {
        int low = Max(weights);
        int high = Sum(weights);
        while(low<=high){
            int mid = low + (high-low)/2;
            if(canShip(weights,mid,days)){
                high = mid-1;
            } 
            else low = mid+1;
        }
        return low;

    }
    public static  boolean canShip(int[] weights,int capacity,int days){
        int load = 0 , day = 1;
        for(int i=0 ; i<weights.length ; i++){
            if(load + weights[i] > capacity ){
                day++;
                load = weights[i];
            }
            else load += weights[i];
        }
        return day<=days ;

    }
    public static int Sum(int[] nums){
        int sum = 0;
        for(int i = 0 ; i<nums.length ;i++){
            sum += nums[i];
        }
        return sum;
    }
    public static int Max(int [] nums){
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i<nums.length ; i++){
            if(nums[i] > max) max = nums[i];
        }
        return max;
    }
    
}
