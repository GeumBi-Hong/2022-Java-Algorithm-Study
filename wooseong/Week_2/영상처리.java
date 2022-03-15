import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21938 {
    public static int[][] display;
    public static boolean[][] visited;
    static int T;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        display = new int[N][M];
        visited = new boolean[N][M];

        double sums = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                for(int k=0; k<3; k++){
                    sums += Integer.parseInt(st.nextToken());
                }
                display[i][j] = (int) Math.floor(sums/3);
                sums=0;
            }
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        int count=0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(dfs(i,j)) count++;
            }
        }

        System.out.println(count);
    }


    public static boolean dfs(int x,int y){
        if (x < 0 || x >= N || y < 0 || y >= M || display[x][y] < T || visited[x][y]) {
            return false;
        }

        if ( display[x][y] >= T ){
            visited[x][y] = true;
            dfs(x+1,y);
            dfs(x-1,y);
            dfs(x,y+1);
            dfs(x,y-1);
            return true;
        }
        return false;
    }
}
