package radixsort;

class Main {
    static class Sort {
        // Counting sort by digit position
        private static void countingSort(Integer[] a, int pos) { // O(n);
            int d = a.length;
            Integer[] c = new Integer[10]; // For base 10 digits (0-9)
            Integer[] output = new Integer[d]; // Output array

            // Initialize the count array
            for (int i = 0; i < 10; i++) {
                c[i] = 0;
            }

            // Store the count of occurrences of each digit
            for (int i = 0; i < d; i++) {
                System.out.printf("(%d / %d ) mod 10 = %d\n", a[i], pos, a[i] / pos % 10);
                int digit = (a[i] / pos) % 10;
                c[digit]++;
            }

            // Change count[i] so that it contains the actual position of this digit in output[]
            for (int i = 1; i < 10; i++) {
                c[i] += c[i - 1];
            }

            // Build the output array
            for (int i = d - 1; i >= 0; i--) {
                int digit = (a[i] / pos) % 10;
                output[c[digit] - 1] = a[i];
                c[digit]--; // Decrease count for that digit
            }

            // Copy the output array to a[], so that a[] now contains sorted numbers by current digit
            for (int i = 0; i < d; i++) {
                a[i] = output[i];
            }
        }

        // Function to get the maximum value in an array
        private static Integer getMax(Integer[] a) {
            int d = a.length;
            Integer max = a[0];
            for (int i = 1; i < d; i++) {
                if (a[i] > max) {
                    max = a[i];
                }
            }
            return max;
        }

        // Radix sort function
        public static void radixSort(Integer[] a) {
            Integer max = getMax(a); // Get the maximum number to know the number of digits

            // Apply counting sort for every digit, starting from least significant digit
            for (int pos = 1; max / pos > 0; pos *= 10) { 
                countingSort(a, pos); // O(n)
            }
        }
    }

    public static void main(String[] args) {
        Integer A[] = {86, 198, 466, 709, 973, 981, 374, 766, 473, 342};
        Sort.radixSort(A);

        // Print sorted array
        for (int num : A) {
            System.out.print(num + " ");
        }
    }
}
