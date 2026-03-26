/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 *
 * @author raffiuddin
 */
public class NewClass1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int N = Integer.parseInt(br.readLine().trim());
        int[] A = new int[N];
        String[] arr_A = br.readLine().split(" ");
        for(int i_A = 0; i_A < N; i_A++) {
        	A[i_A] = Integer.parseInt(arr_A[i_A]);
        }
        int out_ = Solve(N, A);
        System.out.println(out_);

         wr.close();
         br.close();
    }
    static int Solve(int N, int[] A){
        if(A.length>1)
        A = sort(A);
        
        return A[0];
    }

    private static int[] sort(int[] A) {
     
        int mid = A.length/2;
        
        
        return null;
        
    }
}
 