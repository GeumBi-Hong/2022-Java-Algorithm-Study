
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 골목대장호석기능성 {
    static ArrayList<int[]>[] graph;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new int[] { b, cost });
            graph[b].add(new int[] { a, cost });
        }

        visited = new boolean[n + 1];
        answer = Integer.MAX_VALUE;
        visited[start] = true;

        dfs(start,end,money,0);

        if (answer == Integer.MAX_VALUE) {System.out.println(-1);}
        else {System.out.println(answer);}
    }
    static void dfs(int cur,int end, int money , int max){
        if(cur == end){
            answer = Math.min(answer,max);
            return;
        }
        for(int[] now:graph[cur]){
            int next = now[0];
            int cost = now[1];

            if(visited[next]) continue;
            if(money<cost) continue;

            visited[next]= true;
            int nextMax = Math.max(max,cost);
            dfs(next,end,money-cost,nextMax);
            visited[next] = false;
        }
    }
}
