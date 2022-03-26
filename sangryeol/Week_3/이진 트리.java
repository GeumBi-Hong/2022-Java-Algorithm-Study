import java.io.*;
import java.util.*;

public class BOJ_13325 {
    public static int K;
    public static int count;
    public static int[] edges;
    public static int[] dp, sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        count = (1 << (K + 1));
        edges = new int[count];
        dp = new int[count];
        sum = new int[count];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int k = 2; k < count; k++) {
            edges[k] = Integer.parseInt(st.nextToken());
        }
        for (int i = (1 << K) - 1; i >= 1; i--) {
            int l = i * 2, r = i * 2 + 1;
            dp[i] = Math.max(dp[l] + edges[l], dp[r] + edges[r]);
            sum[i] = sum[l] + sum[r] + (dp[i] - dp[l]) + (dp[i] - dp[r]);
        }
        System.out.println(sum[1]);
    }
}
