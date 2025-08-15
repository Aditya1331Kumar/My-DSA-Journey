# leetcode problem 1464

***khas nhi ***
---
*** *** 
---

## some important points to leran:

---
1. largest and second largest find karne ka tarika dekho: 
```

 int max1 = 0;
        int max2 = 0;

        for (int num : nums) {
            if (num >= max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }
```
---
---

2.explaination
```Case 1: num >= max1
Yaani current number sabse bada hai (ya equal to max1).

Tab:

Purana max1 → max2 me shift hota hai.

Naya number ban jata hai max1.
```
```
Case 2: num > max2
Yaani current number max1 se chhota hai, par max2 se bada hai.

Tab direct max2 = num.
```
---
---

3. new method
```
class Solution {
    public int maxProduct(int[] nums) {
        int n= nums.length;
        buildMaxHeap(nums,n);
        if(n>2){
            int secondLargest = Math.max(nums[1], nums[2]);
            return (nums[0] - 1) * (secondLargest - 1);

        }
        return (nums[0] - 1) * (nums[1] - 1);


    }
    void buildMaxHeap(int nums[],int n){
        for(int i=n/2-1;i>=0;i--){
            heapify(nums,i,n);
        }
    }
    void heapify(int nums[],int i,int n){
        int parent = i;
        int left = i*2+1;
        int right = left+1;
        if(left< n &&nums[parent]<nums[left])parent = left;
        if(right< n &&nums[parent]<nums[right])parent = right;
        if(parent!=i){
            swap(nums,parent,i);
            heapify(nums,parent,n);
        }
    }
    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
```
---
---
```
4.
```
---
---
```
5.
```
---
---
```
6.
```
---
---
```
7.
```
---
---
```
8.
```
---

---
```
9.
```
---
---
```
10.
```
---
