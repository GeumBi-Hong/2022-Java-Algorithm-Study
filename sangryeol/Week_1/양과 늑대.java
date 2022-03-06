import java.util.*;

class Node {
    int left, right;
    public Node(int l, int r) {
        left = l;
        right = r;
    }
}

class Solution {
    static int N;
    static int[] dp;
    static boolean[] visited;
    static Node[] tree;

    public void init(int[] info, int[][] edges) {
        N = info.length;
        dp = new int[1 << N];
        visited = new boolean[1 << N];
        tree = new Node[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new Node(0, 0);
        }
        Arrays.fill(dp, -1);
    }
    public int f(int state, int[] info) {
        if (dp[state] != -1) return dp[state];
        int sheep = 0, wolf = 0;
        for (int i = 0; i < N; i++) {
            if ((state & (1 << i)) > 0) {
                if (info[i] > 0) wolf++;
                else sheep++;
            }
        }
        if (wolf >= sheep) return 0;
        dp[state] = sheep;
        for (int i = 0; i < N; i++) {
            if ((~state & (1 << i)) > 0) continue;
            int l = tree[i].left, r = tree[i].right;
            if (l > 0) dp[state] = Math.max(dp[state], f(state | (1 << l), info));
            if (r > 0) dp[state] = Math.max(dp[state], f(state | (1 << r), info));
        }
        return dp[state];
    }
    public int solution(int[] info, int[][] edges) {
        init(info, edges);
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (tree[u].left == 0) tree[u].left = v;
            else tree[u].right = v;
        }
        return f(1, info);
    }
}