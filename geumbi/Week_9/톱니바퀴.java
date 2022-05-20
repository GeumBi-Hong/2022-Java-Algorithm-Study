import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [][] gear = new int[5][10];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 1 ; i<=4;i++){
            char []c = br.readLine().toCharArray();
           for(int j = 0 ; j<c.length;j++){
               gear[i][j+1] = c[j]-'0';
           }
        }


        int K = Integer.parseInt(br.readLine());//회전 회수

        while (K-->0){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int num = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
           go(num,direction,gear);
        }



       int total= 0;
       //정답 구하기
        for(int i = 1; i<=4;i++){
          if(gear[i][1]==1){ 
              
             total+=Math.pow(2,i-1);
          }
        }
        
        System.out.println(total);
    }

    private static void printMap(){
        for(int i =1 ; i<=4;i++){
            for(int j = 1 ; j<=8;j++){
                System.out.print(gear[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("@@@@@@@@@@@@");
    }


    private static void go(int num ,int d ,int [][]gear){

        goLeft(num-1,-d,gear);
        goRight(num+1,-d,gear);
        rotate(num,d,gear);
    }

    private static void goLeft(int num , int d, int [][]gear){
        if(num<1)return;
        if(gear[num][3]!=gear[num+1][7]){
            goLeft(num-1,-d,gear);
            rotate(num,d,gear);

        }
    }
    private static void goRight(int num , int d, int [][]gear){
        if(num>4)return;
        if(gear[num][7]!=gear[num-1][3]){
            goRight(num+1,-d,gear);
            rotate(num,d,gear);
        }
    }

    private static void rotate (int num ,int d ,int [][]gear){
        if(d==1)moveClockWise(num,gear);
        else moveAntiClockWist(num,gear);
    }

    private static void moveClockWise(int num, int [][]g){

        for(int i = 9; i>1 ;i--){

            g[num][i]=g[num][i-1];
        }
        g[num][1]=g[num][9];


    }

    private static void moveAntiClockWist(int num,int [][]g){
        for(int i = 0 ; i<8;i++){
            g[num][i]=g[num][i+1];
        }
        g[num][8]=g[num][0];
    }
}
