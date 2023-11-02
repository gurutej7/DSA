/*  875. Koko Eating Bananas
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
The guards have gone and will come back in h hours.
Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile
of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats
all of them instead and will not eat any more bananas during this hour.
Koko likes to eat slowly but still wants to finish eating all the bananas before the
guards return.
Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:
Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:
Input: piles = [30,11,23,4,20], h = 5
Output: 30
 
 */

public class KokoEatingBananas {
    public static void main(String[] args) {
        int[] piles = {30,11,23,4,20};
        int h = 5;

        System.out.println(minEatingSpeed(piles, h));
    }
    public static int minEatingSpeed(int[] piles, int h) {
        int low = 1 ;
        int high = Max(piles);
        while(low<=high){
            int mid = low + (high-low)/2 ;
            // if koko can eat, mid bananas per hour in less then or equals to h time
            if(calcTime(piles,mid) <= h) high = mid-1;// means decrement our right pointer to optimise better solution 
            else  low = mid+1; // if not true, increment left pointer
        }
        return low;
    }
    public static int calcTime(int[] nums , int k){
        int TotHours = 0;
        for(int i = 0 ; i<nums.length ; i++){
            TotHours += Math.ceil((double)(nums[i])/(double)(k) );
            /*  performing claculation, take an example 
            k = 4
            pile = 10
            pile / k => 10 / 4 = 2
            //pile % k => 2, so we need to have one more hour to eat remaining bananas left over as remainder 
            hours = 3;
            int div = pile / k;
            hours += div;
            if(pile % k != 0) hours++; // if remainder value is not 0, we need to have an extra hour
            */
        }
        return TotHours;

    }
    public static int Max(int [] nums){
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i<nums.length ; i++){
            if(nums[i] > max) max = nums[i];
        }
        return max;
    }
    
}
