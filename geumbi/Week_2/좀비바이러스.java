import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] village;
    static int[][] hour;
    static int[] virus_count = new int[4];
    static int N, M;
    static int[] x_dot = {0, 0, 1, -1};
    static int[] y_dot = {1, -1, 0, 0};

    static class Dot {
        int x, y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        village = new int[N][M];
        hour = new int[N][M];
        
        Queue<Dot> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                village[i][j] = Integer.parseInt(st.nextToken());
                if (village[i][j] > 0) {
                    hour[i][j] = 1;
                    queue.add(new Dot(i, j));
                }
            }
        }
        
        bfs(queue);
        System.out.print(countInfectedVillage());
    }

    public static void bfs(Queue<Dot> queue){

        while (!queue.isEmpty()){


            Dot d = queue.poll();
            int x = d.x;
            int y = d.y;

            if(village[x][y]==3){
                continue;
            }

            for (int i = 0; i < 4 ; i++){

                int nx = x + x_dot[i];
                int ny = y + y_dot[i];


               if(nx<0||nx>=N||ny<0||ny>=M)continue;
               if(village[nx][ny]==-1) continue;

                if(hour[nx][ny]==0){
                    village[nx][ny] = village[x][y];
                    hour[nx][ny] = hour[x][y]+1;
                    queue.add(new Dot(nx,ny));
                }else if (hour[nx][ny]>0){
                    if(hour[nx][ny]==hour[x][y]+1 && village[nx][ny]!=village[x][y]){
                        village[nx][ny]=3;

                    }
                }

            }
        }
    }
    public static String countInfectedVillage(){

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < N; i++){
            for (int j = 0 ; j<M;j++){
                if(village[i][j]==-1 || village[i][j]==0)continue;
                virus_count[village[i][j]]++;

            }
        }

        for (int i= 1;  i<=3;i++){
            sb.append(virus_count[i]+" ");
        }

        return sb.toString();
    }

}

