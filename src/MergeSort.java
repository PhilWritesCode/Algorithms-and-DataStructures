/**
 * Created by Phil on 8/15/2015.
 */
public class MergeSort {

    public MergeSort() {}

    public int[] sort(int[] collection) {

        System.out.println("Pre-sorted: ");
        for(int i = 0; i < collection.length; i++) {
            System.out.print(collection[i] + " ");
        }
        System.out.println();

        mergeSort(collection, 0, collection.length - 1);

        System.out.println("Post-sorted: ");
        for(int i = 0; i < collection.length; i++) {
            System.out.print(collection[i] + " ");
        }
        return collection;
    }

    private void mergeSort(int[] collection, int leftIndex, int rightIndex) {

        if(leftIndex >= rightIndex) return;
        int midPoint = leftIndex + (rightIndex - leftIndex)/2;

        mergeSort(collection, leftIndex, midPoint);
        mergeSort(collection, midPoint + 1, rightIndex);
        merge(collection, leftIndex, midPoint, rightIndex);
    }

    private void merge(int[] collection, int leftIndex, int midPoint, int rightIndex) {

        int[] copy = collection.clone();

        int index = leftIndex;
        int i = leftIndex;
        int j = midPoint+1;

        while(i <= midPoint && j <= rightIndex) {
            if(copy[i] <= copy[j])
                collection[index++] = copy[i++];
            else
                collection[index++] = copy[j++];
        }
         while( i <= midPoint)
             collection[index++] = copy[i++];

         while(j <= rightIndex)
             collection[index++] = copy[j++];
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();

        int[] unsorted = {6,8,2,3,7,1,9,10,43,2,5,25,43,21,75};

        ms.sort(unsorted);
    }
}
