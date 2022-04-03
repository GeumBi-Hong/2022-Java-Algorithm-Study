package 스터디.Week_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위굴리기2 {
    static int n, m, k;
    static int[][] graph;
    static int[][] scoreGraph;
    static int cnt;
    static boolean[][] visited;
    static int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};
    static Dice dice;


    public static void main(String[] args) throws IOException {

        init();


        int dir = 0;
        int answer = 0;
        int r=0,c=0;
        int nr=r,nc=c;

        for(int i =0; i<k; i++){

//            System.out.println("front : "+ dir);
            nr+=direction[dir][0];
            nc+=direction[dir][1];


            if(nr < 0|| nr >= n || nc <0 || nc >=m) {
                dir = (dir+2)%4;

                nr += 2*direction[dir][0];
                nc += 2*direction[dir][1];
            }
            int bot = dice.rollDice(dir);
//            System.out.println("nr :"+ nr + " nr : "+nc);


            r= nr;
            c= nc;
            answer += scoreGraph[r][c];

            if(graph[r][c] < bot){
                dir = (dir+1)%4;
            }
            else if(graph[r][c] > bot){
                dir = (dir-1+4)%4;
            }
//            System.out.println("bottom "+bot);
//            System.out.println("scroe "+ graph[r][c]);
//            System.out.println("dir :" +dir);
//
//            System.out.println("-------------------");

        }

        System.out.println(answer);


    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        scoreGraph = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 점수판 초기화

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                cnt = 0;
                visited = new boolean[n][m];
                dfs(i,j,graph[i][j]);
                scoreGraph[i][j] = cnt * graph[i][j];
            }
        }

        dice =new Dice();
    }

    public static void dfs(int r,int c,int num){
        if ( r < 0 || r >= n || c <0 || c>=m){
            return;
        }
        if(num != graph[r][c] || visited[r][c]) return;;

        cnt++;
        visited[r][c] = true;
        dfs(r+1,c,num);
        dfs(r-1,c,num);
        dfs(r,c+1,num);
        dfs(r,c-1,num);

    }


    static class Dice{
        int top,bottom,left,right,front,rear;

        public Dice() {
            top = 1;
            bottom = 6;
            left = 4;
            right = 3;
            rear = 2;
            front = 5;
        }

        public int rollDice(int dir){
            if(dir ==0) toEast();
            else if (dir == 1) toSouth();
            else if (dir == 2) toWest();
            else if (dir == 3) toNorth();

            return bottom;
        }

        public void toEast(){
            int temp = top;
            top = left;
            left = bottom;
            bottom = right;
            right = temp;
        }

        public void toWest(){
            int temp = top;
            top = right;
            right = bottom;
            bottom = left;
            left = temp;
        }

        public void toNorth(){
            int temp = top;
            top = front;
            front = bottom;
            bottom = rear;
            rear = temp;
        }
        public void toSouth(){
            int temp = top;
            top = rear;
            rear = bottom;
            bottom = front;
            front = temp;
        }


    }
}
