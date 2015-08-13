/**
 * Created by Phil on 8/11/2015.
 */
public class MyMaxPQ<Key>  {

    Key[] heap;
    int size;

    public MyMaxPQ() {
        heap = (Key[]) new Object[2];
        size = 0;
    }

    public void insert(Key key) {
        if(size == heap.length - 1) {
            resizeHeap(heap.length*2);
        }

        heap[++size] = key;

        swim(size);
    }

    public Key max() {
        if(size == 0) {
            System.out.println("empty PQ!");
            return null;
        }
        return heap[1];
    }

    public Key deleteMax() {
        if(size == 0) {
            System.out.println("empty PQ!");
            return null;
        }
        Key retVal = heap[1];
        heap[1] = heap[size];
        heap[size--] = null;
        sink(1);
        assert(isMaxHeap() == true);
        if(size > 0 && size == heap.length/4)
            resizeHeap(heap.length / 2);
        return retVal;
    }

    private void sink(int index) {
        int largestChildIndex = index*2;
        Key temp;
        while(largestChildIndex <= size) {
            if(largestChildIndex+1 <= size && ((Comparable<Key>)heap[largestChildIndex]).compareTo(heap[largestChildIndex+1]) < 0)
                largestChildIndex++;
            if(((Comparable<Key>)heap[index]).compareTo(heap[largestChildIndex]) > 0) break;
            temp = heap[index];
            heap[index] = heap[largestChildIndex];
            heap[largestChildIndex] = temp;
            index = largestChildIndex;
            largestChildIndex = index*2;
        }

    }

    private void swim(int index) {
        Key temp;
        int parentIndex = getParentIndex(index);
        while (index > 1 && ((Comparable<Key>)heap[index]).compareTo(heap[parentIndex]) > 0) {
            temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }

        assert(isMaxHeap() == true);
    }

    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    private boolean isMaxHeap(int index) {
        if(index > size) return true;

        Key indexObject = heap[index];
        int leftIndex = index*2;
        int rightIndex = leftIndex+1;
        if(leftIndex <= size && ((Comparable<Key>)indexObject).compareTo(heap[leftIndex]) < 0) return false;
        if(rightIndex <= size && ((Comparable<Key>)indexObject).compareTo(heap[rightIndex]) < 0) return false;

        return isMaxHeap(leftIndex) && isMaxHeap(rightIndex);
    }

    private int getParentIndex(int index) {
        return index/2;
    }

    private void resizeHeap(int newSize) {
        Key newHeap[] = (Key[])new Object[newSize];
        for(int i = 0; i <= size; i++)
            newHeap[i] = heap[i];

        heap = newHeap;
    }

    public static void main(String[] args) {

        MyMaxPQ<Integer> maxPQ = new MyMaxPQ();

        maxPQ.insert(2);
        maxPQ.insert(5);
        maxPQ.insert(3);
        maxPQ.insert(4);
        maxPQ.insert(1);
        System.out.println("max: " + maxPQ.max());
        System.out.println("delete max: " + maxPQ.deleteMax());
        System.out.println("max: " + maxPQ.max());
        maxPQ.insert(8);
        maxPQ.insert(11);
        maxPQ.insert(6);
        System.out.println("max: " + maxPQ.max());
        System.out.println("delete max: " + maxPQ.deleteMax());
        System.out.println("max: " + maxPQ.max());
        maxPQ.insert(15);
        maxPQ.insert(12);
        maxPQ.insert(21);
        System.out.println("max: " + maxPQ.max());
        System.out.println("delete max: " + maxPQ.deleteMax());
        System.out.println("max: " + maxPQ.max());
        System.out.println("delete max: " + maxPQ.deleteMax());
        System.out.println("max: " + maxPQ.max());
        System.out.println("delete max: " + maxPQ.deleteMax());
        System.out.println("max: " + maxPQ.max());
        System.out.println("delete max: " + maxPQ.deleteMax());
        System.out.println("max: " + maxPQ.max());
        System.out.println("delete max: " + maxPQ.deleteMax());
        System.out.println("max: " + maxPQ.max());
        System.out.println("delete max: " + maxPQ.deleteMax());
        System.out.println("max: " + maxPQ.max());
        System.out.println("delete max: " + maxPQ.deleteMax());
        System.out.println("max: " + maxPQ.max());





    }



}
