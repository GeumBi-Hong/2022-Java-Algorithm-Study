
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

   static boolean[][] map;
   static int []count ;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        count = new int[N+1];
        map = new boolean[N+1][N+1];

        for (int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
             map[start][end]=true;
        }


       for(int k = 1; k <= N ; k++){ //거처가는 노드
           for(int r =1 ; r <=N;r++){ //시작
               for(int c=1; c<=N;c++){//도착
                   if(map[r][k] && map[k][c]) map[r][c]=true;
               }
           }
       }


        for(int r =1 ; r <=N;r++){ //시작
            int count = N-1; //자기 자신 제외
            for(int c=1; c<=N;c++){//도착
                
                //map[r][c] 시작노드보다 무게가 작은 노드들이 존재하는지
                //map [c][r] 시작노드보다 무게가 큰 노드들이 존재하는지
                if(map[r][c] || map[c][r]) count --;
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }

}

