import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//마법사 상어와 비바라기
public class Main {

    static int N,M;
    static int[][] map ;
    static boolean[][] cloud , copyCloud;
   // 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static int [] dr = {0,0,-1,-1,-1,0,1,1,1};
    static int [] dc = {0,-1,-1,0,1,1,1,0,-1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());

        map = new int[N][N];
        cloud = new boolean[N][N];
        copyCloud = new boolean[N][N];


        for(int i = 0; i <N;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j <N; j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }


        //처음 구름 만들기
        makeFirstCloud(N);

        for(int i = 0 ; i <M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            //구름이동
            moveCloud(d,s);

            //대각선 체크
            countDiagonal();

            //2만큼 빼기
            minusTwo();

        }


        System.out.println(total());
    }

    public static void makeFirstCloud(int n){
        cloud[n-1][0] = true;
        cloud[n-1][1] = true;
        cloud[n-2][0] = true;
        cloud[n-2][1] = true;
    }
    public static void moveCloud(int d ,int s){

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                if(cloud[r][c]){
                    int nr = (r+ (dr[d] + N) * s) % N;
                    int nc = (c + (dc[d] + N) * s) % N;


                    map[nr][nc]++;
                    copyCloud[nr][nc]=true;
                    cloud[r][c]=false;


                }

            }
        }


    }

    public static boolean inWall(int r , int c){
        if(r >=N || c>=N || r<0 || c<0) return false;
            return true;
    }

    public static void countDiagonal(){

        for(int r =0 ; r<N; r++){
            for(int c= 0; c<N;c++){

                if(copyCloud[r][c]){
                    for(int j=2 ; j<=8; j+=2){
                        int nr =r+dr[j];
                        int nc= c+dc[j];
                        if(inWall(nr,nc) && map[nr][nc]!=0){
                            map[r][c]++;
                        }

                    }
                }
            }
        }
    }

    public static void minusTwo(){

        for(int r =0 ; r<N; r++){
            for(int c= 0;  c<N;c++){

                if(!copyCloud[r][c] && map[r][c]>=2){
                    map[r][c]-=2;
                    cloud[r][c]=true;
                }

               else if(copyCloud[r][c]) copyCloud[r][c]=false;
            }
        }



    }

    public static int total(){
        int answer=0;
        for(int r =0; r <N;r++){
            for(int c =0; c<N;c++){
                answer+=map[r][c];
            }
        }
        return answer;
    }
}

