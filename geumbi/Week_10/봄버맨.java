import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{

    static int[][] bombMap  ;
    static int N,M,T;

    static int [] dr ={1,0,-1,0};
    static int [] dc = {0,-1,0,1};


    static int time = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        bombMap = new int[N][M];

        for(int i = 0 ; i<N; i++){
            String s = br.readLine();
            for(int j = 0 ; j<M;j++){
                if(s.charAt(j)=='O'){
                    bombMap[i][j]=0; //폭탄 자리;
                }else {
                    bombMap[i][j]=-1;//빈칸은 -1
                }
            }
        }

        if(T==1){
            printMap();
            return;

        }else {
          
            time++;//처음 그냥 가만히
            plusOneSecond();
            while (true){

                plusOneSecond(); //원래존재하던 폭탄 +1초
                makeBomb(); //새로운 폭탄
                time++;


                if(T==time){

                    printMap();
                    return;
                }
                plusOneSecond();
                removeBomb();


                time++;



                if(T==time){

                    printMap();
                    return;
                }
            }

        }

    }

    private static void printMap(){
        for(int i = 0 ; i<N; i++){
            for(int j = 0 ; j<M;j++){
                if(bombMap[i][j]!=-1){
                    System.out.print("O");
                }else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private static void printMap2(){
        for(int i = 0 ; i<N; i++){
            for(int j = 0 ; j<M;j++){
           System.out.print(bombMap[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void plusOneSecond(){
        for(int i = 0 ; i<N; i++){
            for(int j = 0 ; j<M;j++){
               if(bombMap[i][j]!=-1){//폭탄이라면
                   bombMap[i][j]++;
               }
            }
        }
    }
    private static void makeBomb(){
        for(int i = 0 ; i<N; i++){
            for(int j = 0 ; j<M;j++){
                if(bombMap[i][j]==-1){
                    bombMap[i][j]=0;
                }
            }
        }
    }

    private static void removeBomb(){
        List<int[]> list = new ArrayList<>();
        for(int i = 0 ; i<N; i++){
            for(int j = 0 ; j<M;j++){
                if(bombMap[i][j]==3){

                    list.add(new int[]{i,j});
                    for(int k = 0 ; k < 4; k++){
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        if(isRange(nr,nc)){

                            list.add(new int[]{nr,nc});
                        }
                    }
                }
            }
        }// for

        for(int [] n :list){
            bombMap[n[0]][n[1]]=-1;
        }
    }

    private static boolean isRange(int r ,int c){
        return r>=0 && r<N && c>=0 && c<M;
    }
}
