package 스터디.Week_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 골목대장호석효율성 {
    static class Node implements Comparable<Node> {
        private int index, cost, max;

        public Node(int index, int cost, int max) {
            this.index = index;
            this.cost = cost;
            this.max = max;
        }

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            if (cost < other.cost) {
                return -1;
            }
            return 1;
        }

    }
    static int n,start,end,m,money;
    static int[] maxArr;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        money = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        maxArr = new int[n + 1];
        Arrays.fill(maxArr, Integer.MAX_VALUE);

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, cost));
            graph[b].add(new Node(a, cost));
        }

        int left = 0, right = 20, mid = 0, ans = 987654321;
        while(left <= right){
            mid = (left + right) / 2;
            if(dijkstra(mid)){
                right = mid - 1;
                ans = mid;
            }
            else left = mid + 1;
        }
    }

    private static boolean dijkstra(int max) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0, Integer.MIN_VALUE));
        boolean[] visited = new boolean[n + 1];

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int now = curNode.index;

            if (visited[now] || now == end) {visited[now] = true;}

            for (Node nextNode : graph[now]) {
                int ncost = curNode.cost + nextNode.cost;
                if (visited[nextNode.index] || ncost > money) {continue;}
                int curMax = curNode.max;
                if (curMax < nextNode.cost) {curMax = nextNode.cost;}
                if (maxArr[nextNode.index] > curMax) {maxArr[nextNode.index] = curMax;}
                pq.add(new Node(nextNode.index, ncost, curMax));
            }
        }
        return maxArr[end] <= money;


    }

}

