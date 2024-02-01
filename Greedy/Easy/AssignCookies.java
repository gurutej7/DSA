package Greedy.Easy;

import java.util.Arrays;

/* 455. Assign Cookies
{ https://leetcode.com/problems/assign-cookies/description/ } 

Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at 
most one cookie.
Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; 
and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will 
be content. Your goal is to maximize the number of your content children and output the maximum number.

Example 1:      Input: g = [1,2,3], s = [1,1]           Output: 1
Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.
 */

public class AssignCookies{
    public static void main(String[] args) {
        int children[] = {1,2,3};
        int cookies[] = {1,1} ;

        System.out.println( findContentChildren(children, cookies) );
        
    }

    // The idea is to maximize the number of children who will receive cookies
    // Greedily distribute the cookies , give small size cookies to child with smaller greedy factor 
    // By this if we reach a point where the current cookie cannot satisfy the current child 
    // then it will never be able to satisfy further childrens , so skip that cookie and move forward
    // until we can satisfy the current child , when we have satisfied a child then child++

    private static int findContentChildren(int[] children, int[] cookies) {
        Arrays.sort(children);
        Arrays.sort(cookies);
        
        int child = 0;
        for (int cookie = 0; child < children.length && cookie < cookies.length; cookie++) {
            if (cookies[cookie] >= children[child]) {
                child++;
            }
        }
        
        return child;
    }
}