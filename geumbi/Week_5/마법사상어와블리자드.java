import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {


    static class Dot {
        int r ;
        int c;

        public Dot (int r, int c){
            this.r =r ;
            this.c= c;
        }
    }
    static int N, M, sharkR, sharkC;
    static int[][] map;
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, -1, 1};  // 위 아래 왼 오 1  2  3 4

    static int[] bead = new int[4];
    static Dot [] dots;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        dots = new Dot[N*N];


        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }


        int []d =  new int[M];
        int []s = new int[M];

        for(int i = 0 ; i <M;i++){
            st = new StringTokenizer(br.readLine(), " ");
            d[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
        }

        sharkR = ((N + 1) / 2) - 1;
        sharkC = ((N + 1) / 2) - 1;


        makeIndexList();
       // printList();

      for(int i = 0 ;  i<M;i++) {
          /*  1.블라지드 마법  */
          blizzardMagic(d[i], s[i]);
          //printMap();

          /*  2.구슬 이동 */
          beadMove();
          //printMap();

          /*  3.구슬 폭발  */
         while (explosionBead()){
             beadMove();
         }
         //printMap();
          /* 4. 구슬 변화 */
          beadGroup();

         //printMap();
      }

      System.out.print(bead[1]+bead[2]*2+bead[3]*3);

    }

    public static void printMap (){
        for(int r = 0 ; r <N;r++){
            for(int c = 0 ; c<N;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }

        System.out.println("***************");
    }

    public static void printList (){
        for (int i = 1 ;  i <N*N;i++){
            System.out.println(dots[i].r+" "+dots[i].c);
        }

    }
  //  1차원 배열에 칸의 번호에 해당하는 좌표값을 저장한다.
  //  달팽이 (?) 형식의 2차원 배열에 접근을 쉽게 하기 위해서 이다.
    public static void makeIndexList(){

        int curR = sharkR;
        int curC =sharkC;
        int count =0;
        int d = 3;
        int index =1;
        boolean finish = true;


        while (finish){
            if(d==3 || d==4) count++;

            for(int i=0 ; i <count;i++){
                if(index==N*N){
                    finish =false;
                    break;
                }

                int nr = curR+dr[d];
                int nc = curC+dc[d];

                dots[index]=new Dot(nr,nc);


                curR +=dr[d];
                curC +=dc[d];

                index++;
            }
            if(finish){
                d=chageDirection(d);
            }

        }

    }

    public static int chageDirection(int d){

        if(d==3)return 2;
        if(d==2)return 4;
        if(d==4)return 1;
        if(d==1)return 3;

        return 0;
    }

    public static void countBead(int r, int c){

        bead[map[r][c]]++;
    }

    /*  1.블라지드 마법  */
    public static void blizzardMagic(int d, int s) {

        int curR = sharkR;
        int curC = sharkC;

        for (int i = 0; i < s; i++) {

            int nr = curR + dr[d];
            int nc = curC + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc]==0) {
                break;
            }

            map[nr][nc]=-1;

            curR +=dr[d];
            curC +=dc[d];

        }
    }
    /*  2.구슬 이동 */
    public static void beadMove(){
        int [][]copyMap = new int[N][N];
        int emtpy = 0;

        for(int i =1; i<N*N;i++){

            if(map[dots[i].r][dots[i].c]==-1){
                emtpy++;
            }else {
                copyMap[dots[i-emtpy].r][dots[i-emtpy].c] =map[dots[i].r][dots[i].c];
            }
        }
        for (int i = 0; i < N; i++) {
            map[i] = copyMap[i].clone();
        }
    }
    /*  3.구슬 폭발  */
    public  static  boolean explosionBead(){
        int index = -1;
        int count = 0;
        boolean hasBoom = false;

        for(int i =1 ; i <N*N;i++){
            if(index == map[dots[i].r][dots[i].c]) count++;

            else {

                if(count>=4){
                   for (int c = count; c>0;c--){
                       countBead(dots[i-c].r,dots[i-c].c);
                       map[dots[i-c].r][dots[i-c].c]=-1;
                   }
                   hasBoom = true;


                }
                count=1;
                index = map[dots[i].r][dots[i].c];
            }


        }
        return hasBoom;
    }
    /* 4. 구슬 변화 */
    public static void beadGroup(){
        int[][] copyMap =  new int[N][N];
        int value =map[dots[1].r][dots[1].c];
        int count = 0;
        int copyMapIndex=1;



        for(int i = 1; i <N*N;i++){
            if(copyMapIndex>=N*N){
                break;
            }
            if(value == map[dots[i].r][dots[i].c]) count++;

            else {

                copyMap[dots[copyMapIndex].r][dots[copyMapIndex].c] = count;
                copyMap[dots[copyMapIndex+1].r][dots[copyMapIndex+1].c]= value;

                value = map[dots[i].r][dots[i].c];
                count=1;

                copyMapIndex=copyMapIndex+2;

            }
        }
        for (int i = 0; i < N; i++) {
            map[i] = copyMap[i].clone();
        }
    }
}
