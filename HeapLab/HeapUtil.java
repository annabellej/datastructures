public class HeapUtil {
    /**
     * Organizes a max heap so that every node is less than its left and right children.
     * heapify should run in O(n) time.
     * @param heap array that contains heap data
     * @param index root of the tree being heapified
     * @param heapSize size of the heap
     */
    public static void heapify(Comparable[] heap, int index, int heapSize)
    {
        if (index > heapSize) return;
        int max = index;
        int maxChild;
        int left = 2*index;
        int right = 2*index+1;
        if (left > heapSize && right > heapSize) return;
        else if (right > heapSize) maxChild = left;
        else if (left > heapSize) maxChild = right;
        else {
            if (heap[left].compareTo(heap[right]) >= 0) maxChild = left;
            else maxChild = right;
        }
        if (heap[maxChild].compareTo(heap[max]) > 0){
            swap(heap, index, maxChild);
            heapify(heap, maxChild, heapSize);
        }
    }

    /**
     * Swaps the element at index with the element at maxChild
     * @param heap the heap to swap in
     * @param index the index of the first element to swap
     * @param maxChild the index of the other element to swap
     */
    public static void swap(Comparable[] heap, int index, int maxChild)
    {
        Comparable temp = heap[index];
        heap[index] = heap[maxChild];
        heap[maxChild] = temp;
    }

    /**
     * Builds a heap of size heapSize by completing a binary tree and representing it in an array
     * in the correct max heap order.
     * buildHeap should run in O(n) time.
     * @param heap inputted array that represents a binary tree with heapSize number of nodes.
     * @param heapSize the number of nodes heap will have
     * post condition: heap a complete binary tree satisfying the heap conditions.
     */
    public static void buildHeap(Comparable[] heap, int heapSize)
    {
        if (heapSize == 0 || heapSize >= heap.length) return;
        int curIndex = heapSize/2; //index of first non-leaf
        while (curIndex >= 1) {
            heapify(heap, curIndex, heapSize);
            curIndex--;
        }
    }

    /**
     * Removes the root of the heap and returns its value.
     * Resulting binary tree's size will be one less,
     * and should still adhere to the max heap conditions.
     * @param heap the heap to remove from
     * @param heapSize the size of the heap to remove from
     * @return the value of the removed root of the heap
     */
    public static Comparable remove(Comparable[] heap, int heapSize)
    {
        buildHeap(heap, heapSize); //??
        Comparable removed = heap[1];
        heap[1] = heap[heapSize];
        heap[heapSize] = removed;
        heapSize--;
        heapify(heap, 1, heapSize);
        return removed;
    }

    /**
     * Inserts a given item into the heap, making sure that the heap still
     * maintains max heap conditions, and returns the new heap.
     * @param heap the heap to add to
     * @param item the item to insert in the heap
     * @param heapSize the size of the heap in question
     * @return the heap with the addition of item
     */
    public static Comparable[] insert(Comparable[] heap, Comparable item, int heapSize)
    {
        if (heapSize > heap.length-1) return null;
        heap[heapSize] = item; //assuming heap is not already full
        int index = heapSize;
        while (heapSize >= 1 && index > 0){
            heapifyUp(heap, index, heapSize);
            index /= 2;
        }
        buildHeap(heap, heapSize);
        return heap;
    }

    /**
     * Performs the heapify function from the bottom up
     * rather than top down.
     * @param heap array that contains heap data
     * @param index element of the tree being heapified up
     * @param heapSize size of the heap
     */
    public static void heapifyUp(Comparable[] heap, int index, int heapSize)
    {
        if (index == 1) return;
        int parentIndex = index/2;
        if (heap[parentIndex].compareTo(heap[index]) < 0) {
            swap(heap, index, parentIndex);
            heapify(heap, parentIndex, heapSize);
        }
    }

    /**
     * Performs a heap sort of the data in the array in place.
     * Runs in O(nlogn)
     * @param heap  the heap to sort
     * @param heapSize  the size of the given heap
     */
    public static void heapSort(Comparable[] heap, int heapSize)
    {
        buildHeap(heap, heapSize);
        while (heapSize > 0){
            remove(heap, heapSize);
            heapSize--;
            //heapify(heap, 1, heapSize); -- done in remove method
        }
    }
}
