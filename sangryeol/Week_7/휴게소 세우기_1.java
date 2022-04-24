import java.io.*;
import java.util.*;

public class Review {
    public static void main(String[] args) throws IOException {
        int N, M, L;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        boolean[] bArea = new boolean[L];
        for (int i = 0; i < N; i++) {
            int area = Integer.parseInt(st.nextToken());
            bArea[area] = true;
        }
        for (int length = 1; length <= 1000; length++) {
            int last = 0, count = 0;
            for (int i = 1; i < L; i++) {
                if (bArea[i]) last = i;
                else if (i - last == length) {
                    last = i;
                    count++;
                }
            }
            if (count <= M) {
                System.out.println(length);
                return;
            }
        }
        System.out.println(-1);
    }
}