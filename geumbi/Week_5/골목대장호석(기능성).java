import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//골목대장 호석 - 기능성 (인접행렬)
public class Main {

    static int[][] map;
    static boolean[] visited;
    static int N,M,A,B,money;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");



        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        money = Integer.parseInt(st.nextToken());


        map = new int[N+1][N+1];
        visited = new boolean[N+1];





        for(int i = 0 ; i <M;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            //양방향 관계
            map[start][end]=cost;
            map[end][start]=cost;

        }

        dfs(A,B,money,0);

        System.out.print(answer==Integer.MAX_VALUE? -1:answer);

    }


    public static void dfs (int start ,int end ,int money,int maxCost){

        if(start==end){
            answer=Math.min(answer,maxCost);
               // System.out.println(start);
            return;
        }



        for(int i=1; i<=N;i++){

            //가중치가 없으면 넘어감 // //방문한 노드면 넘어감 //방문해야되는 노드가 방문했다면 넘어감
            if(map[start][i]==0 ||visited[start]||visited[i])continue;
         
            int cost = map[start][i];

            if(money<cost) continue; //돈이 없어도 넘어감

         //   System.out.println(start+" "+i);

            visited[start]=true;
            int max = Math.max(cost,maxCost);
            dfs(i,end,money-cost,max);
            visited[start]=false;

        }

    }
}
