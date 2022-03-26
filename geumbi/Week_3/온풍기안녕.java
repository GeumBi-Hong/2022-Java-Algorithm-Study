import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


    /*
   1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
   2.온도가 조절됨
   3.온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
   4.초콜릿을 하나 먹는다.
   5.조사하는 모든 칸의 온도가 K 이상이 되었는지 검사. 모든 칸의 온도가 K이상이면 테스트를 중단하고, 아니면 1로다시 1부터 다시 시작한다.
     */
    static class Machine {
        int r;
        int c;
        int direction;

        public Machine(int r, int c, int direction) {
            this.r = r;
            this.c = c;
            this.direction = direction;
        }
    }

    static class Check {
        int r;
        int c;

        public Check(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Dot {
        int r;
        int c;
        int t;

        public Dot(int r, int c,int t) { //t는 온도값
            this.r = r;
            this.c = c;
            this.t=t;
        }

    }

    static ArrayList<Machine> machineList = new ArrayList<>();
    static ArrayList<Check> checkList = new ArrayList<>();

    static int R, C, K;
    static int[][] map;
    static boolean[][][] wall;

    static int RIGHT = 1;
    static int LEFT = 2;
    static int UP = 3;
    static int DOWN = 4;

    static int []dx={0,0,0,-1,1};
    static int []dy={0,1,-1,0,0};
    static int dWx[][] = {{0}, {-1, 0, 1}, {-1, 0, 1}, {-1, -1, -1}, {1, 1, 1}};
    static int dWy[][] = {{0}, {1, 1, 1}, {-1, -1, -1}, {-1, 0, 1}, {-1, 0, 1}};
    static int choco;
  
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R + 1][C + 1];
        wall = new boolean[R + 1][C + 1][5]; //0제외하고 1~4까지


        //조사해야되는 칸과 온풍기 위치 저장
        for (int r = 1; r <= R; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 1; c <= C; c++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 0) continue;
                if (n == 5) {
                    checkList.add(new Check(r, c));
                } else {

                    machineList.add(new Machine(r, c, n));
                }
            }
        }

        //벽 정보 저장
        int w = Integer.parseInt(br.readLine());
        while (w-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (t == 0) { //상하
                wall[x][y][UP] = true;
                wall[x - 1][y][DOWN] = true;
            } else { //좌우
                wall[x][y][RIGHT] = true;
                wall[x][y + 1][LEFT] = true;
            }
        }


        while (choco<=100){

            //온풍기 바람
            controlWind();
            //온도 조절
            controlTemperature();
            //온도 1이상인 가장 바깥쪽 칸의 온도 1씩 감소
            decreaseTemperature();
            //초콜릿먹기
            choco++;
            //조사하는 모든 칸의 온도가 K이상이였는지 검사
            if(CheckRoom()){
                System.out.println(choco);
                return;
            }
        }

        System.out.println(101);

    }



//1. 온풍기 바람
    public static void controlWind() {

        for (Machine m : machineList) {
            boolean [][]visited = new boolean[R+1][C+1];

            Queue<Dot> queue = new LinkedList<>();

            int mr = m.r+dx[m.direction];
            int mc = m.c+dy[m.direction];
            map[mr][mc]+=5;
            queue.add(new Dot(mr,mc,5));


            while (!queue.isEmpty()) {
                Dot d = queue.poll();

                int r = d.r;
                int c = d.c;
                int t = d.t;

                for (int i = 0; i < 3; i++) {

                    int nr = r + dWx[m.direction][i];
                    int nc = c + dWy[m.direction][i];

                    if(t==1)continue;
                    if(nr >R || nr <1 || nc > C || nc <1)continue;
                    if(isWall(r,c,nr,nc,m.direction,i))continue;
                    if(visited[nr][nc])continue;

                    queue.add(new Dot(nr, nc,t-1));
                    map[nr][nc] += t-1;
                    visited[nr][nc]=true;


                }
            }

        }

    }
    // 벽이 있는지 없는지 체크한다.
    public static boolean isWall(int r, int c, int nr, int nc, int direction, int i) {

        if (i == 1) { //직진 루트

            if (wall[r][c][direction]) {return true;}
        }

        if (i == 0) {
            if (direction == RIGHT) {
                if (wall[r][c][UP] ||wall[nr][nc][LEFT]) return true;
            }
            if (direction == LEFT) {
                if (wall[r][c][UP] || wall[nr][nc][RIGHT]) return true;
            }
            if (direction == UP) {
                if (wall[r][c][LEFT] || wall[nr][nc][DOWN]) return true;
            }
            if (direction == DOWN) {
                if (wall[r][c][LEFT] || wall[nr][nc][UP]) return true;
            }
        }
        if (i == 2) {
            if (direction == RIGHT) {
                if (wall[r][c][DOWN] ||wall[nr][nc][LEFT]) return true;
            }
            if (direction == LEFT) {
                if (wall[r][c][DOWN] || wall[nr][nc][RIGHT]) return true;
            }
            if (direction == UP) {
          
                if (wall[r][c][RIGHT] || wall[nr][nc][DOWN]) return true;
            }
            if (direction == DOWN) {
                if (wall[r][c][RIGHT] || wall[nr][nc][UP]) return true;
            }

        }
        return false;

    }
    //2.온도조절하기
    public static void controlTemperature(){
        int [][]tempMap = new int[R+1][C+1];
        for(int r = 1 ; r<=R ; r++){
            for(int c =1; c<=C;c++){

                for(int t =1 ; t<=4;t++){
                    int nr = r+dx[t];
                    int nc = c+dy[t];

                    if(nr >R || nr <1 || nc > C || nc <1)continue;
                    if(wall[r][c][t])continue;

                    int dif = (map[r][c]-map[nr][nc])/4;

                    if(dif>0){
                        tempMap[nr][nc]+=dif;
                        tempMap[r][c]-=dif;
                    }
                }

            }
        }

        for(int i = 1 ; i<=R ; i++){
            for(int j =1; j<=C;j++){
                map[i][j]+=tempMap[i][j];
            }
        }

    }

// 3.온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
    public static void decreaseTemperature() { 
        for (int j = 1; j <=C-1; j++) {
            if(map[1][j]> 0) map[1][j]--;
        }
        for(int i=1; i<=R-1;i++){
            if(map[i][C]>0) map[i][C]--;
        }

        for(int i= C; i>=2;i--){
            if(map[R][i]>0) map[R][i]--;
        }

        for(int i=R;i>=2;i--){
            if(map[i][1]>0)map[i][1]--;
        }

    }

// 5.조사하는 모든 칸의 온도가 K 이상이 되었는지 검사. 모든 칸의 온도가 K이상이면 테스트를 중단하고, 아니면 1로다시 1부터 다시 시작한다.
    public static boolean CheckRoom(){
        for(Check check : checkList){
            if(map[check.r][check.c]>=K)continue;
            else return false;
        }
        return true;
    }
}
