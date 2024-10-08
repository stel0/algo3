class Main {

    public static class Heap{

        private static int left(int i){
            return i*2 + 1;
        }

        private static int right(int i){
            return (i*2)+2;
        }

        private static void exchange(int i,int j,int[] A){
            int aux_value = A[i];
            A[i] = A[j];
            A[j] = aux_value;

        }

        private static void heapify(int[]A,int i){
            int left = left(i);
            int right = right(i);
            int larger=i;
            System.out.println("i=" + i);
            System.out.println("larger=" + i);

            if(left < A.length && A[left] > A[i] )
                larger = left;
            if(right < A.length && A[right] > A[larger])
                larger = right;
            if(larger != i){
                System.out.println("i=" + i);
                System.out.println("larger=" + i);
                exchange(i, larger, A);
                heapify(A, larger);
            }
        }

        public static void maxHeap(int[]A){
            for(int i = A.length/2 - 1 ; i >= 0 ; i-- )
                heapify(A, i);
        }

    }    

    public static void main(String[]args){
        int[] A = {1,2,3,4,5,6,7,8,9};
        Heap.maxHeap(A);

        for(int elemento:A){
            System.out.println(elemento);
        }
    }
}