import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,K;
    static final int INF = 987654321;
    static int [][] fish_Bowl ;
    static int [] x_Dot = {0,0,1,-1};
    static int [] y_Dot = {-1,1,0,0};
    public static void main(String[] args)  throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fish_Bowl = new int[N+1][N+1];
        st = new StringTokenizer(br.readLine()," ");
        for (int i = 1 ; i <=N; i++){
            fish_Bowl[N][i]=Integer.parseInt(st.nextToken());
        }

        int answer= 0;
        while (!isFinish()){
            answer++;
            putOneFish();
            levitaionFish90();
            moveFish();
            makeLine();

            fold();
            moveFish();
            makeLine();

        }

        System.out.print(answer);

    }
    /* 어항 정리가 잘 되었는지 확인
        public static void printArray(){
            for(int i = 1 ; i<=N; i++){
                for(int j = 1 ; j<=N;j++){
                    System.out.print(fish_Bowl[i][j]+" ");
                }
                System.out.println();
            }
        }

     */
    //1. 물고기 한마리 넣기
    public static void putOneFish(){
        int min = INF;
        for (int i = 1 ;  i <=N; i++){
            min = Math.min(min,fish_Bowl[N][i]);
        }

        for(int i = 1; i<=N;i++){
            if(fish_Bowl[N][i]==min){
                fish_Bowl[N][i]++;
            }
        }
    }

    //2. 공중부양 (1) 여기 꼭 다시 공부하자
    public  static  void levitaionFish90(){

        int pivotX = 1;
        int w = 1;
        int h = 1;
        int idx = 0;

        while (pivotX - 1 + w + h <= N) {
            idx++;
            for (int x = pivotX; x < pivotX + w; x++) {
                for (int y = N; y > N - h; y--) {
                    int ny = N - w + x - pivotX;
                    int nx = pivotX + w + N - y;
                    fish_Bowl[ny][nx] = fish_Bowl[y][x];
                    fish_Bowl[y][x] = 0;
                }
            }
            pivotX += w;
            if (idx % 2 == 0) {
                w++;
            } else {
                h++;
            }
        }


    }
    //3.물고기 이동
    public static void moveFish(){
        int [][]copyFishBowl = new int[N+1][N+1]; //초기 값

        for (int i = 1 ; i<=N;i++){
            for(int j = 1 ;j<=N;j++){
                copyFishBowl[i][j]=fish_Bowl[i][j];
            }
        }

        for (int x = 1 ; x<=N;x++){
            for(int y = 1 ;y<=N;y++){

                if(copyFishBowl[x][y]==0) continue;

                for(int i = 0 ; i <4 ; i++){
                    int nx = x+x_Dot[i];
                    int ny = y+y_Dot[i];

                    if((ny < 1 || nx < 1 || ny > N || nx > N) || copyFishBowl[nx][ny] == 0) continue;

                    int dif = copyFishBowl[x][y] - copyFishBowl[nx][ny];
                    dif =  dif/5;

                    if(dif>0){
                        fish_Bowl[x][y]-= dif;
                        fish_Bowl[nx][ny] += dif;
                    }
                }
            }
        }

    }
    //4 . 어항 일렬로 만들기
    public static void makeLine(){

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i<=N;i++){
            for (int j = N; j>0;j--){
                if(fish_Bowl[j][i]==0)break;
                queue.add(fish_Bowl[j][i]);
                fish_Bowl[j][i]=0;
            }
        }

        for (int i = 1;  i <=N;i++){
            fish_Bowl[N][i] = queue.poll();
        }
    }
    // 5.어항 n/2 180도 두번
    public static void fold(){
        int h=0;
        int pivotX =0;
        int half;

        Queue<Integer> queue = new LinkedList<>();
        for (int n = 1 ;  n <= 2 ; n++){ //두번 반복

            half = (N+pivotX)/2;
            for(int i = pivotX+1; i<=half;i++){
                for(int j=N; j>=N-h;j--){ //행
                    queue.add(fish_Bowl[j][i]);
                    fish_Bowl[j][i]=0;
                }
            }

            h+=n;

            for( int i = N; i>half;i--){ //열
                for(int j =N-h; j<=N-n;j++){//행
                    fish_Bowl[j][i]= queue.poll();
                }

            }

            pivotX= half;
        }

    }


    public static boolean isFinish() {
        int max = 0;
        int min = INF;

        for (int i = 1 ;  i <= N; i++){
            max = Math.max(max,fish_Bowl[N][i]);
            min = Math.min(min,fish_Bowl[N][i]);
        }
        if (max - min <=K ) {
            return true;
        } else
            return false;
    }

}
