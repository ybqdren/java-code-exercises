package com.github.ybqdren.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * created by ybqdren
 */
public class MaxHeap {
    // 使用数组创建完全二叉树的结构，然后使用二叉树创建一个【堆】
    int[] maxHeap;
    // heapSize用于记录数组的大小，因为数组在创建的时候至少需要指明数组的元素个数
    int heapSize;
    // realSize 用于记录【堆】的元素个数
    int realSize = 0;

    public MaxHeap(int heapSize) {
        this.heapSize = heapSize;
        maxHeap = new int[heapSize -1];
        // 为了便于完全二叉树的管理，数组的第0个索引的元素我们不会使用，所以这里随便设置
        maxHeap[0] = 0;
    }


    // 添加元素
    public void add(int element){
        realSize++;
        if(realSize > heapSize){
            System.out.println("Add too many elements!");
            realSize--;
            return;
        }

        maxHeap[realSize] = element;
        int index = realSize;
        // 若数组表示完全二叉树，且根节点存储在数组索引位置时，
        // 任何一个节点的父节点索引位置/2
        // 任何一个节点的左孩子节点的索引位置为：该节点索引位置*2
        // 任何一个节点的有右孩子节点的索引位置为：该节点索引位置*2+1
        int parent = index / 2;
        // 若不满足堆要求，就交换
        while (maxHeap[index] > maxHeap[parent] && index>1){

            int temp = maxHeap[index];
            maxHeap[index] = maxHeap[parent];
            maxHeap[parent] = temp;

            index = parent;
            parent = index/2;
        }
    }

    // 获取堆顶元素
    public int peek(){
        return maxHeap[1];
    }

    // 删除堆顶元素
    public int pop(){
        if(realSize < 1){
            System.out.println("Dont't have any elment!");
            return Integer.MIN_VALUE;
        }else{
            int removeElement = maxHeap[1];
            // 将堆中最后一个元素赋值给堆顶元素
            maxHeap[1] = maxHeap[realSize];
            realSize--;
            int index = 1;
            // 当删除的元素不是孩子节点时
            while (index < realSize && index <= realSize /2){
                // 被删除节点的左孩子节点
                int left = index *2;
                // 被删除节点的右孩子节点
                int right = index *2 + 1;

                // 当删除节点的元素小于左孩子节点或右孩子节点，代表该元素的值小
                // 需要将该元素与左 右孩子节点中最大的值进行交换
                if(maxHeap[index] < maxHeap[left] || maxHeap[index] < maxHeap[right]){
                    if(maxHeap[left] > maxHeap[right]){
                        int temp = maxHeap[left];
                        maxHeap[left] = maxHeap[right];
                        maxHeap[right] = temp;
                        index = left;
                    }else{
                        // maxHeap[left] <= maxHeap[right]
                        int temp = maxHeap[right];
                        maxHeap[right] = maxHeap[index];
                        maxHeap[index] = temp;
                        index = right;
                    }

                }else{
                    break;
                }
            }
            return removeElement;
        }
    }

    // 返回堆的元素个数
    public int size(){
        return  realSize;
    }

    @Override
    public String toString() {
        if(realSize == 0){
            return "Empty";
        }else{
            StringBuffer sb = new StringBuffer();
            sb.append("{");
            for(int i=1;i<realSize;i++){
                sb.append(maxHeap[i]);
                sb.append(',');
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(',');
            sb.append("}");
            return sb.toString();
        }
    }
}
