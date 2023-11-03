package Recursion.Basic;
public class BS {
  public static void main(String[] args) {
    int arr[] = {1,2,4,8,15};
    int target = 15;
    System.out.println(binarySearchBool(arr,0,arr.length-1,target));
    System.out.println(binarySearchIndex(arr,0,arr.length-1,target));
  }
  //s = starting Index
  //e = ending Index
  //Function to return boolean value if the target is presents or not
  public static boolean binarySearchBool(int arr[] , int s , int e, int target){
    if(s>e) return false;
    //m = mid
    int m = s+ (e-s)/2;
    if(arr[m]==target) return true;
    else if(arr[m]<target) return binarySearchBool(arr, m+1, e, target);
    else return binarySearchBool(arr, s, m-1, target);

  }
  //Function to return the index if the target is present or -1 if target is not present
  public static int binarySearchIndex(int arr[] , int s , int e, int target){
    if(s>e) return -1;
    int m = s+ (e-s)/2;
    if(arr[m]==target) return m;
    else if(arr[m]<target) return binarySearchIndex(arr, m+1, e, target);
    else return binarySearchIndex(arr, s, m-1, target);

  }
}
