import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Dot {
        int r ;
        int c;

        public Dot (int r, int c){
            this.r=r;
            this.c=c;
        }
    }

    static int [][] map;
    static int N,M;

    /* 0 동 /1 남/ 2 서/ 3 북/ */
    static int direction = 0;
    static int [] dr = {0,1,0,-1};
    static int [] dc = {1,0,-1,0};
    static int currR ;
    static int currC;
    static int answer=0;
    static int [][] dice = {
            {0,0,0,0,0},
            {0,0,2,0,0},
            {0,4,1,3,0},
            {0,0,5,0,0},
            {0,0,6,0,0},
            {0,0,0,0,0}

    };
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
       
        currR =1;
        currC =1;

        for(int r = 1; r<=N;r++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int c = 1; c<=M;c++){
                map[r][c]=Integer.parseInt(st.nextToken());
            }
        }

        while (K-->0){

            //주사위가 이동방향으로 한칸 굴러간다. 이동할 칸이 없다면 반대 (180도)방향으로 굴러간다.
            canGO(currR,currC,direction);
            //다이스 굴린다.
            diceMove(direction);
            //주사위가 도착한 칸에 대하여 점수를 흭득한다.
            getScore();
            //주사위의 다음 이동방향을 결정한다.
            nextDirection();
        }

        System.out.print(answer);


    }

    public static boolean isNext(int r,int c) {
        if (r >= 1 && r <= N && c >= 1 && c <= M) {
            return true;
        }else {
            return false;
        }
    }
    public static void canGO(int r , int c,int d){

        int nr = r+dr[d]; int nc = c+dc[d];
        if(!isNext(nr,nc)){
            if(d==0) direction=2;
            if(d==1) direction=3;
            if(d==2) direction=0;
            if(d==3) direction=1;
        }
        currR=r+dr[direction]; currC=c+dc[direction];
    }
  
    public static void getScore(){

        boolean isVisited[][] = new boolean[N+1][M+1];
        Queue<Dot> queue = new LinkedList<>();
        queue.add(new Dot(currR, currC));
        isVisited[currR][currC]= true;
        int count=1;

        int value = map[currR][currC];

        while (!queue.isEmpty()){
            Dot d = queue.poll();
            int r =  d.r;
            int c = d.c;

            for (int i = 0 ; i <4;i++){
                int nr = r+dr[i];
                int nc = c+dc[i];


                if(!isNext(nr,nc))continue;
                if(map[nr][nc]!=value)continue;
                if(isVisited[nr][nc])continue;

                queue.add(new Dot(nr,nc));
                isVisited[nr][nc]=true;
                count++;
            }
        }

        answer+=(count*value);
    }
    public static void diceMove(int d ){
        if(d==0){ //동
          
            dice[2][4]=dice[2][3];
            dice[2][3]=dice[2][2];
            dice[2][2]=dice[2][1];

            dice[2][1]=dice[4][2];
            dice[4][2]=dice[2][4];
        }
        if(d==1){//남
     
            for(int i=5;2<=i;i--){
                dice[i][2]= dice[i-1][2];
            }
            dice[1][2] = dice[5][2];
        }

        if(d==2){//서
    
            dice[2][0]=dice[2][1];
            dice[2][1]=dice[2][2];
            dice[2][2]=dice[2][3];

            dice[2][3]=dice[4][2];
            dice[4][2]=dice[2][0];

        }
        if(d==3){ //북
        
            for(int i = 0 ; i  <=3;i++ ){
                dice[i][2] = dice[i+1][2];
            }
            dice[4][2]=dice[0][2];
        }

    }
    public static void nextDirection(){
      
        int diceDown = dice[4][2]; //다이스 아래면
        int mapValue = map[currR][currC];//(x,y)값

        if(diceDown>mapValue){ //다이스 아랫면값이 큰경우 시계방향
            direction= (direction+1) % 4;

        }else if (diceDown<mapValue){ // 좌표값이 큰경우 반시계
            direction = (direction-1)%4;
            if(direction==-1) direction=3;
        }
    }
}
