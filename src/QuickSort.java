/**
 * Created by Phil on 8/15/2015.
 */
public class QuickSort {

    public QuickSort() {}

    public int[] sort(int[] collection) {
        System.out.println("Pre-sorted: ");
        for(int i = 0; i < collection.length; i++) {
            System.out.print(collection[i] + " ");
        }
        System.out.println();

        quickSort(collection, 0, collection.length - 1);

        System.out.println("Post-sorted: ");
        for(int i = 0; i < collection.length; i++) {
            System.out.print(collection[i] + " ");
        }
        return collection;
    }

    private void quickSort(int[] collection, int leftIndex, int rightIndex) {

        if(leftIndex >= rightIndex) return;

        int pivotIndex = pivot(collection, leftIndex, rightIndex);
        quickSort(collection, leftIndex, pivotIndex-1);
        quickSort(collection, pivotIndex+1, rightIndex);
    }

    private int pivot(int[] collection, int leftIndex, int rightIndex) {

        int i = leftIndex+1;
        int j = rightIndex;

        int midIndex = leftIndex + (rightIndex - leftIndex)/2;
        int pivotValue = collection[midIndex];
        swap(collection, midIndex, leftIndex);

        while(i < j) {
            while(collection[i] < pivotValue && i <= rightIndex)
                i++;
            while(collection[j] > pivotValue && j >= leftIndex)
                j--;

            if(i < j)
                swap(collection, i, j);
        }
        swap(collection, leftIndex, j);
        return j;
    }

    private void swap(int[] collection, int indexA, int indexB) {
        int swapSpace = collection[indexA];
        collection[indexA] = collection[indexB];
        collection[indexB] = swapSpace;
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();

        int[] unsorted = {6,8,2,3,7,1,9,10,43,2,5,25,43,21,75};

        qs.sort(unsorted);
    }
}
