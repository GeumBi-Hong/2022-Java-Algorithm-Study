import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{

    static int N;
    static int [][] map ;
    static int [] dr = {0,0,1,-1};
    static int [] dc = {1,-1,0,0};
    static ArrayList<Integer>[]  arrayList;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        int people =(int) Math.pow(N,2);
        arrayList = new ArrayList[people+1];


        for(int i = 1 ; i<=people;i++){
            arrayList[i] = new ArrayList<>();
        }

        for(int i =1 ; i<=people;i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            int student = Integer.parseInt(st.nextToken());
          //  System.out.println(student);

            while (st.hasMoreTokens()){
                arrayList[student].add(Integer.parseInt(st.nextToken()));
            }
            assignSeats(student);
         //   printMap();
        }


        //만족도 구하기
      System.out.print(countSatisfaction());

    }

    private static void printMap(){
        for(int i =1; i<=N;i++){
            for(int j = 1 ; j<=N;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("**************");
    }

    private static void assignSeats(int student){


        int studentR=0,studentC=0,studentBlock=-1,studentLike=-1;

        for(int r=1; r<=N;r++){
            for(int c = 1;c<=N;c++){

                int block = 0 ; //빈 자리 수
                int like =0; //좋아하는 사람 수

                if(map[r][c]==0){//빈자리만 탐색

                    for(int i = 0 ; i <4 ;i++){
                        int nr = r+dr[i];
                        int nc = c+dc[i];


                        if(inWall(nr,nc)){ //교실 범위에 있는 경우

                            if(map[nr][nc]==0) block++; //빈 블럭인경우
                            if(arrayList[student].contains(map[nr][nc])) like++; //좋아하는 사람인 경우


                        }
                    }//for

                    //비교
                    if(like>studentLike){
                        studentLike=like;
                        studentBlock=block;
                        studentR=r;
                        studentC=c;
                    }

                    if(like==studentLike){
                        if(block>studentBlock){
                            studentBlock=block;
                            studentR=r;
                            studentC=c;
                        }
                    }

                }

            }
        }//for
        map[studentR][studentC]=student;

    }

    private static boolean inWall(int r ,int c){
        if(r<=N && c<=N && r>0 && c>0)return true;
        else return false;
    }
    private static int countSatisfaction(){

        int sum=0;
        for(int r =1; r <=N; r++){
            for(int  c= 1; c<=N;c++){


                int like=0;

                for(int i =0 ; i<4;i++){
                    int nr = r +dr[i];
                    int nc = c+ dc[i];

                    if(inWall(nr,nc)){
                        if(arrayList[map[r][c]].contains(map[nr][nc])) like++; //좋아하는 사람인 경우
                    }
                }//for

                if(like==1) sum+=1;
                else if(like==2)sum+=10;
                else if(like==3)sum+=100;
                else if(like==4)sum+=1000;
            }
        }
        return  sum;
    }
}

