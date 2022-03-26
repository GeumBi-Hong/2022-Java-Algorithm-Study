package 스터디.Week_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1949 {

    static int N;
    static int[] person;
    static int[][] dp;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList[N + 1];
        person = new int[N + 1];
        dp = new int[N + 1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {person[i] = Integer.parseInt(st.nextToken());}

        for (int i = 0; i <= N; i++) {arr[i] = new ArrayList<>();}

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }

        dfs(1, 0);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    public static void dfs(int n, int parent) {

        for (int node : arr[n]) {
            if (node != parent) {
                dfs(node, n);
                dp[n][0] += Math.max(dp[node][0], dp[node][1]);
                dp[n][1] += dp[node][0];
            }
        }
        dp[n][1] += person[n];
    }
}
