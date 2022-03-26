import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

        static int N;
        static int []value;
        static ArrayList<Integer>[] arrayLists;
        static int dp[][];
        
    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());
         value =  new int[N+1];
         dp = new int[N+1][2];
         arrayLists = new ArrayList[N+1];
         
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i<=N;i++){
            value[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i<=N;i++){
            arrayLists[i] = new ArrayList<Integer>();
        }

        for(int i=1 ; i<=N-1;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arrayLists[a].add(b);
            arrayLists[b].add(a);
        }

        dfs(1,-1);

        System.out.println(Math.max(dp[1][0],dp[1][1]));
    }

    public static void dfs(int now ,int parent) {

        for(int next  : arrayLists[now]){
            if(parent!=next) {
                dfs(next, now);
                dp[now][1] += dp[next][0];
                dp[now][0] += Math.max(dp[next][1], dp[next][0]);
            }
        }

        dp[now][1]+=value[now];
    }
}
