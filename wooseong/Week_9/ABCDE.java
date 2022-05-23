package 스터디.Week_9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ABCDE {
    static boolean[] visited;
    static List<Integer>[] graph;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new List[n];
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        visited = new boolean[n];
        ans=0;

        for(int i=0; i<n; i++){
            if(ans == 1) break;
            visited[i] = true;
            dfs(i,0);
            visited[i] = false;
        }
        System.out.println(ans);

    }
    static void dfs(int num,int depth){
        if( depth == 4){
            ans = 1;
            return;
        }
        for(int next : graph[num]){
            if( !visited[next] ){
                visited[next] = true;
                dfs(next,depth+1);
                visited[next] = false;
            }
        }
    }
}
