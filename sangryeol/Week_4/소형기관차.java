import java.io.*;
import java.util.*;

public class BOJ_2616 {
    static final int MAXSZ = 50010;
    static int N, M; // N : 객차의 수, M : 최대로 끌 수 있는 객차의 수
    static int[] pSum = new int[MAXSZ];
    static int[][] dp = new int[4][MAXSZ];
    public static int f(int num, int pos) {
        if (num <= 0 || pos < M) return 0;
        if (dp[num][pos] != -1) return dp[num][pos];
        int count = pSum[pos] - pSum[pos - M];
        dp[num][pos] = Math.max(f(num, pos - 1), f(num - 1, pos - M) + count);
        return dp[num][pos];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1, val; i <= N; i++) {
            val = Integer.parseInt(st.nextToken());
            pSum[i] = pSum[i-1] + val;
        }
        M = Integer.parseInt(br.readLine());
        for (int[] array : dp) {
            Arrays.fill(array, -1);
        }
        System.out.println(f(3, N));
    }
}
