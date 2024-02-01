package Greedy.Easy;
/* 
{ https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1 }
Given weights and values of N items, we need to put these items in a knapsack of capacity W to get the maximum 
total value in the knapsack.
Note: Unlike 0/1 knapsack, you are allowed to break the item here 

Input:      N = 3, W = 50           value[] = {60,100,120}          weight[] = {10,20,30}
Output:     240.000000
Explanation:
Take the item with value 60 and weight 10, value 100 and weight 20 and split the third item with value 120 and 
weight 30, to fit it into weight 20. so it becomes (120/30)*20=80, so the total value becomes 60+100+80.0=240.0
Thus, total maximum value of item we can have is 240.00 from the given capacity of sack. 
 
 */

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {
    public static void main(String[] args) {
        Item arr[] = {new Item(60, 10) , new Item(100, 20) , new Item(120, 30)};

        System.out.println( fractionalKnapsack(50, arr, arr.length) );
        
    }

    // Function to get the maximum total value in the knapsack.
    // Since we can take any item irrespective of their weight => we will pick the item with the maximum value
    // To pick the maximum value we have to sort it in the reverse order
    private static double fractionalKnapsack(int W, Item arr[], int n) 
    {
        Arrays.sort(arr, new itemComparator()); 
        
        double finalvalue = 0.0; 
        
        for (int i = 0; i < n; i++) {
            // if the current weigth is less than available weight just pick it and reduce the weight
            if (arr[i].weight <= W) {
                W -= arr[i].weight;
                finalvalue += arr[i].value;
            }
            
            // else break the current element into piece with available 
            // The formula for this is given in the problem description
            else {
                finalvalue += ((double)arr[i].value / (double)arr[i].weight) * (double)W;
                break;
            }
        }
     
        return finalvalue;
        
    }    
}

class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}
// function to sort in the decreasing order
class itemComparator implements Comparator<Item>
{
    @Override
    public int compare(Item a, Item b) 
    {
        double r1 = (double)(a.value) / (double)(a.weight); 
        double r2 = (double)(b.value) / (double)(b.weight); 
        if(r1 < r2) return 1; 
        else if(r1 > r2) return -1; 
        else return 0; 
    }
}

// { https://takeuforward.org/data-structure/find-minimum-number-of-coins/ }
