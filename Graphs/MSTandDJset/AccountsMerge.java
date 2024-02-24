package Graphs.MSTandDJset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 721. Accounts Merge
{ https://leetcode.com/problems/accounts-merge/description/ } 

Example 1:
Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]

Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]

Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 */

public class AccountsMerge {

    @SuppressWarnings("unchecked")
    private static List<List<String>> accountsMerge(List<List<String>> accounts) {
        /* Hint by Leetcode : For every pair of emails in the same account, draw an edge between those 
        emails. The problem is about enumerating the connected components of this graph. */
        Map<String,Integer> map = new HashMap<>();
        // Integer -> index -> name of the account
        int n = accounts.size();
        UnionFind uf = new UnionFind(n); // at max n users -> n vertices
        
        // step 1 , point mails to the corresponding name
        // At any point if we encounter a mail which is of the user in the map , then attach(union) that user(index) to the existing user
        for(int i = 0 ; i < n  ;i++){ // i = 0 , means the account holder name at 0th index
            for(int j = 1 ;  j < accounts.get(i).size() ; j++ ){
                if(map.containsKey(accounts.get(i).get(j))){ // if the current mail is already existing in the map then union(current,i)
                    uf.unionByRank(i,map.get(accounts.get(i).get(j)));
                }
                map.put(accounts.get(i).get(j) , i);
            }
        }

        // step 2 , accumulate all the mails to their ultimate users (parent)
        // create a array of List of strings , arr[0] => the mails that belong to person at 0 th  index
        ArrayList<String> arr[] = new ArrayList[n];
        for(int i = 0 ; i < n ; i++) arr[i] = new ArrayList<String>();

        // Traverse through the map and add the mails to corresponding names

        for(Map.Entry<String,Integer> pair : map.entrySet()){
            String mail = pair.getKey();
            int accountName = pair.getValue();
            int up = uf.find(accountName); // finding the ultimate parent for the current mail
            arr[up].add(mail);
        }

        // step 3 : form the result in the required format
        List<List<String>> res = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            if(arr[i].size() == 0) continue; // this account has been merged to some other account
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0)); // getting the account holder name
            Collections.sort(arr[i]); // mails need to be in the sorted order
            for(String str : arr[i]) temp.add(str);
            res.add(temp);
        }

        return res;

    }
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));

        System.out.println(accountsMerge(accounts));
    }
    
}
