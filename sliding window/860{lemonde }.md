# lemonde change its easy
```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int[] arr = new int[3];
        for(int i =0;i<bills.length;i++){
          int  e = bills[i];
            if(e==5){
                arr[0]++;
            }
            if(e==10){ arr[1]++;
                if(arr[0]<=0){return false;}
                else{arr[0]--;}
            }
            if(e==20){
                arr[2]++;
                if (arr[1]>0&& arr[0]>0){arr[1]--; arr[0]--;}
               else if(arr[0]>2){arr[0]--;arr[0]--;arr[0]--;}
               else {return false;}
                
            }
        }
        return true;
    }
}
```
## or try { both are same }
```java
  public static boolean lemonadeChange(List<Integer> bills) {
        
        // Initialize a counter
        // for $5 bills
        int five = 0; 
        
        // Initialize a counter
        // for $10 bills
        int ten = 0;   
        
        // Iterate through each customer's bill
        for(int i = 0; i < bills.size(); i++){
            
            // If the customer's
            // bill is $5
            if(bills.get(i) == 5){
                
                // Increment the
                // count of $5 bills
                five++;  
            }
            
            // If the customer's
            // bill is $10
            else if(bills.get(i) == 10){
                
                // Check if there are $5
                // bills available to give change
                if(five > 0){
                     // Use one $5 bill
                     // to give change
                    five--; 
                    // Receive one $10 bill
                    ten++;   
                }
                
                // If no $5 bill
                // available, return false
                else return false;  
            }
            
            // If the customer's
            // bill is $20
            else {
                // Check if there are both
                // $5 and $10 bills
                // available to give change
                if(five > 0 && ten > 0){
                     // Use one $5 bill
                    five--; 
                    // Use one $10 bill
                    ten--;   
                }
                // If there are not enough $10 bills,
                // check if there are at least
                // three $5 bills available
                else if (five >= 3){
                    // Use three $5 bills
                    // to give change
                    five -= 3;  
                }
                // If unable to give
                // change, return false
                else return false;  
            }
        }
        
        // Return true if all customers
        // are served with correct change
        return true;  
    }
```