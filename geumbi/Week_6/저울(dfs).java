import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
   static List<Integer>[] list ;
   static boolean  [] visited;
   static int []count ;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        count = new int[N+1];

        for(int i = 1 ; i<=N; i++){
            list[i] = new ArrayList<>();
        }


        for (int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list[start].add(end);
        }


        for(int i = 1 ; i<=N;i++){
          visited = new boolean[N+1];
            dfs(i,i);
        }

        for(int i = 1 ; i<=N; i++){
            sb.append(N - 1 - count[i]).append("\n");
        }

        System.out.println(sb);
    }
    public static void dfs(int start ,int curr){

        visited[curr] = true;

        for(int next : list[curr]){
            if(!visited[next]){
                count[next]++; // 전 노드가 존재하므로 +1
                count[start]++; //다음 노드가 존재하므로 +1
                dfs(start,next);
            }
        }
    }
}
