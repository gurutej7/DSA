package Greedy.Easy;

/* 860. Lemonade Change
{ https://leetcode.com/problems/lemonade-change/description/ } 

At a lemonade stand, each lemonade costs $5. Customers are standing in a queue to buy from you and order one at a 
time (in the order specified by bills). Each customer will only buy one lemonade and pay with either a $5, $10, or
 $20 bill. You must provide the correct change to each customer so that the net transaction is that the customer 
 pays $5.
Note that you do not have any change in hand at first.
Given an integer array bills where bills[i] is the bill the ith customer pays, return true if you can provide every customer with the correct change, or false otherwise.

 */

public class LemonadeChange {
    public static void main(String[] args) {
        int[] bills = { 5, 5, 10, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 5, 5, 20, 5, 20, 5 }; // Expected true

        System.out.println(lemonadeChange(bills));

    }

    // The greedy idea here is that we use large coins to change as much as possible
    // so as to leave more small coins in our hands.
    // When the customer gives us $20, we have two options:
    // 1. To give three $5 in return
    // 2. To give one $5 and one $10.
    // On insight is that the second option (if possible) is always better than the
    // first one.Because two $5 in hand is always better than one $10
    private static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int i : bills) {
            // 5$
            if (i == 5)
                five++;
            // 10$
            else if (i == 10) {
                five--;
                ten++;
            // 20$
            } else if (ten > 0) {
                ten--;
                five--;
            } 
            // if it is 20 $ and we don`t have 10 then we will use 5$
            else
                five -= 3;
            // At any point we have negative change then return false    
            if (five < 0)
                return false;
        }
        return true;
    }
}
