# What is a Monotonic Stack?
A monotonic stack is a stack whose elements are monotonically increasing or decreasing. It contains all 
qualities that a typical stack has and its elements are all monotonic decreasing or increasing.

## When to Use Monotonic Stack ?
Below are the features of a monotonic stack:
1. It is a _range of queries in an array_ situation.
2. The _minima/maxima_ element or the monotonic order of elements in a range is useful to get answer of every range query.
3. When a element is popped from the monotonic stack, it will never be used again.

## Types of Monotonic Stack:
There are 2 types of monotonic stacks:

* Monotonic Increasing Stack
* Monotonic Decreasing Stack
### Monotonic Increasing Stack:
It is a stack in which the elements are in increasing order from the bottom to the top of the stack. 

#### How to achieve Monotonic Increasing Stack?
To create a Monotonic Increasing Stack, start with an empty stack, then, while iterating through elements in a sequence, keep removing elements from the stack as long as they are smaller than the current element, and push the current element onto the stack. This process ensures the stack maintains a strictly increasing order from bottom to top.

### Monotonic Decreasing Stack:
A stack is monotonically decreasing if It’s elements are in decreasing order from the bottom to the top of the stack. 

#### How to achieve Monotonic Decreasing Stack?
To create a Monotonic Decreasing Stack, begin with an empty stack, then, while iterating through elements in a sequence, continuously remove elements from the stack as long as they are smaller than or equal to the current element, and finally, push the current element onto the stack. This process ensures the stack maintains a monotonic decreasing order from bottom to top.

## Applications of Monotonic Stack :
1. Monotonic stack is generally used to deal with a typical problem like Next Greater Element. NGE Find the
 first value on the right that is greater than the element.
2. Also can be used for its varieties.
    * Next Smaller Element
    * Previous Greater Element
    * Previous Smaller Element
3. Also, we use it to get the greatest or smallest array or string by the given conditions (remaining size
 k/ no duplicate).
4. To understand the optimization power of monotonic stacks, let’s take this example problem: Minimum Cost
  Tree From Leaf Values. This problem can be solved in 3 different algorithm ways, out of which the monotonic
  stack is the most optimized approach.
5. Dynamic Programming Algorithmic Approach: O(N^3) Time O(N^2) Space
6. Greedy Algorithmic Approach: O(N^2) Time O(1) Space
7. Monotonic Stack Algorithmic Approach: O(N) Time O(N) Space
## Advantages of Monotonic Stack:
1. We can use the extra space of a monotonic stack to reduce the time complexity.
2. We can get the nearest smaller or greater element depending on the monotonic stack type, by just retrieving
  the stack’s top element, which is just an O(1) operation.
3. The monotonic stack helps us maintain maximum and minimum elements in the range and keeps the order of 
  elements in the range. Therefore, we don’t need to compare elements one by one again to get minima and maxima
 in the range. Meanwhile, because it keeps the element’s order, we only need to update the stack based on the 
 newest added element.
## Disadvantages of Monotonic Stack:
1. It increases the space complexity of the algorithm by a factor of O(N), i.e. by a linear complexity.
2. It is often more complex to handle as now with the existing problem, we also need to handle the stack
  carefully. As once the elements are popped from the stack, we cannot get them back.

#### References
* [](https://github.com/lzl124631x/algorithm/blob/master/monotonic-stack.md#monotonic-stack)
* [](https://www.geeksforgeeks.org/how-to-identify-and-solve-monotonic-stack-problems/)