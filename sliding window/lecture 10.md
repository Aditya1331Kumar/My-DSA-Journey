```java
class Solution {
    public int numberOfSubarrays(int[] s, int k) {
        
        return ans(s,k)-ans(s,k-1);
    }

     public int ans(int[] s, int k) {
         int n = s.length;
        int maxLen = 0;
      int count =0;
int m=0;
        
        int l = 0, r = 0;
        while (r < n) {
         if(s[r]%2!=0){m++;}
           while(m>k){
            if(s[l]%2!=0){m--;}
            l++;
           } 
           
            int len = r - l + 1;
            maxLen = Math.max(len, maxLen);

           count += (r - l + 1);
            r++;
        }
        return count;
    }
}
```
### seekhne wali baat ki us right tak kitne subaarays banenge 
```java
count += (r - l + 1);
```