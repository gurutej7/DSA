package BitManipulation.Easy;

public class BasicBitOperations {
    public static void main(String[] args) {
        int n = 10;

        System.out.println(getBit(n, 1)); 
        System.out.println(clearBit(n, 1));
        System.out.println(setBit(n, 1));
        System.out.println(n);


    }
    

    // Function to get the bit at the
    // ith position
    private static boolean getBit(int num, int i)
    {
        
        // Return true if the ith bit is
        // set. Otherwise return false
        return ((num & (1 << i)) != 0);
    }
    
    // Function to set the ith bit of the
    // given number num
    private static int setBit(int num, int i)
    {
        
        // Sets the ith bit and return
        // the updated value
        return num | (1 << i);
    }
    
    // Function to clear the ith bit of
    // the given number num
    private static int clearBit(int num, int i)
    {
        
        // Create the mask for the ith
        // bit unset
        int mask = ~(1 << i);
    
        // Return the updated value
        return num & mask;
    }
}
