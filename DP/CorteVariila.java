package DP;

public class CorteVariila {
    // time complexity(2^n) a medida de que el input aumenta 
    public static int cutRod(int[] p, int n) {
        int q = 0;
        if (n <= 0)
            return 0;
        for (int i = p.length - n; i < n; i++) {
            q = Math.max(q, p[i] + cutRod(p, n - i - 1));
        }
        return q;
    }
    
    // corte de varilla con memorizacion top down time complexity 0(n^2)
    public static int cutRodMemo(int[] p, int n){
        int r[] = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = -1;
        }
        return cutRodMemoAux(p,n,r);
    }

    private static int cutRodMemoAux(int[] p, int n, int[] r) {
        int q = 0;
        if (n > 0 && r[n - 1] >= 0) { // Check if n > 0 to avoid accessing r[-1]
            return r[n - 1]; // Return optimal solution if found
        }
        if (n == 0) {
            q = 0;
        } else {
            q = -1;
            for (int i = 0; i < n; i++) {
                q = Math.max(q, p[i] + cutRodMemoAux(p, n - i - 1, r)); // Fix recursive call with n - i - 1
            }
        }
        if (n > 0) {
            r[n - 1] = q; // Store result only when n > 0
        }
        return q;
    }

    // corte de varilla con memorizacion bottom up time complexity 0(n^2)
    private static int cutRodMemoBottomUp(int[] p, int n) {
        int[] r = new int[n + 1]; // Create array of size n + 1 to include r[0] to r[n]
        int q;

        r[0] = 0; // Base case: no rod gives zero profit
        for (int j = 1; j <= n; j++) { // Start from j = 1 up to j = n
            q = -1;
            for (int i = 0; i < j; i++) {
                q = Math.max(q, p[i] + r[j - i - 1]); // Corrected indexing for r[j - i - 1]
            }
            r[j] = q; // Store the maximum profit for rod length j
        }

        return r[n]; // Return the maximum profit for a rod of length n
    }
    public static void main(String[] args) {
        int[] p = { 1,5,8,9,10,17,17,20,24,30 };
        System.out.println(cutRodMemo(p, p.length)); // me retorna la maxima ganancia que puedo obtener de cortar la varilla
    }
}
