package com.github.ybqdren.heap;

import org.junit.jupiter.api.Test;

/**
 * created by ybqdren
 */
public class MaxHeapTest {
    @Test
    public void should_test(){
        MaxHeap maxHeap = new MaxHeap(5);
        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(3);

        // [3,1,2]
        System.out.println(maxHeap.toString());

        // 3
        System.out.println(maxHeap.peek());
        System.out.println(maxHeap.toString());

        // 3
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());

        // empty
        System.out.println(maxHeap.toString());
        maxHeap.add(4);
        // add too many elements
        maxHeap.add(5);
        // [4,1,2]
        System.out.println(maxHeap.toString());
    }


}
