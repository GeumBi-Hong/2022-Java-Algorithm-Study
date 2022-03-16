import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static double [][] array;
    static boolean [][] visited;
    static int [] x_dot = {0,0,1,-1};
    static int [] y_dot = {1,-1,0,0};
    static int N,M;
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

         N = Integer.parseInt(st.nextToken());
         M =  Integer.parseInt(st.nextToken());
        int count = 0;

        array = new double[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i <N;i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int  j = 0;  j < M;j++){
                array[i][j] =(double) (Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken()))/3;
           
            }
            }
        int T = Integer.parseInt(br.readLine());


        for (int i = 0 ;  i <N; i++){
            for(int j = 0 ; j <M;j++){
                if( array[i][j]<T) array[i][j]=0;
            }
        }

        for (int i = 0;   i < N ; i++){
            for (int j = 0; j<M;j++){
                if(!visited[i][j]&&array[i][j]>0){
                    bfs(i,j);
                    count++;
                }
            }
        }
            System.out.print(count);
        }
        public static void bfs (int x,int y ) {
            visited[x][y]=true;
            Queue<Dot> queue = new LinkedList<>();
            queue.add(new Dot(x,y));

            while (!queue.isEmpty()){
                Dot d = queue.poll();

                for(int i = 0 ; i < 4 ; i++){
                    int nx = d.x +x_dot[i];
                    int ny = d.y +y_dot[i];


                    if(nx <0 || ny <0 || nx>=N||ny>=M)continue;
                    if(visited[nx][ny]) continue;
                    if(array[nx][ny]==0)continue;

                    queue.add(new Dot(nx,ny));
                    visited[nx][ny]=true;
                }


            }

        }

        static class Dot{
        int x,y;

        public Dot(int x ,int y ){
            this.x =x;
            this.y=y;
        }

        }
    }



