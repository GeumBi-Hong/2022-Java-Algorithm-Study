import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static  class Dot {
        int r;
        int c ;

        public Dot(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N,M;
    static int [][]map;
    static int []  dr = {0,0,1,-1};
    static int []  dc = {1,-1,0,0};
    static List<Dot> finalList = new ArrayList<>(); // 블록 삭제해야할 좌표
    static List<Dot> tempList ;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int answer=0;

        map = new int[N][N];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0  ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        while (true){
           int groupSize = findBlockGrorup();
           if( groupSize < 2){
            break;
           }


           // System.out.println("시작");
            //점수 계산
            answer+=Math.pow(groupSize,2);

            //System.out.println("그룹블록 삭제하기");
            removeBlockGroup();

            //System.out.println("중력가하기");
             mapGravity();

            //System.out.println("반시계 방향으로 90도 돌리기");
            antiClockWise90();

            //System.out.println("다시중력가하기");
            mapGravity();



        }
        System.out.println(answer);
    }

    private static void printMap(){
        for(int r = 0 ;  r < N; r ++){
            for(int c = 0 ;  c < N ; c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }

        System.out.println("*************");
    }

    private static int findBlockGrorup(){

        int maxSize = 0 ;
        int maxRainbow = 0;
        boolean[][] isVisited = new boolean[N][N];

        //블록의 행이 가장 큰 것을, 그 것도 여러개이면 열이 가장 큰 것을 찾는다.
        for(int r = 0 ; r<N; r++){
           for(int c = 0; c<N ;c++){
               if(map[r][c]>0 && map[r][c]<=M &&!isVisited[r][c]){

                   int[] info =calGruopSizeAndRainBow(map[r][c],r,c,isVisited);

                   if(maxSize<info[0]){  // info[0]= 그룹크기 info[1]=무지개블록 개수
                       maxSize=info[0];
                       maxRainbow = info[1];
                       finalList=tempList;


                   }else if(maxSize==info[0]){
                       if(maxRainbow<=info[1]){
                           maxRainbow = info[1];
                           finalList=tempList;
                       }

                   }


               }

            }
        }
        return maxSize;

    }


    private static int[] calGruopSizeAndRainBow(int blockColor,int r,int c,boolean[][]isVisited){
        //너비 우선 탐색

        int count =1; // 현재 칸의 수도 세어주어야하기때문
        List<Dot> rainBowList = new ArrayList<>();
        Queue<Dot> queue = new LinkedList<>();
        queue.add(new Dot(r,c));
        isVisited[r][c]=true;
        int rainbow=0;

        tempList = new ArrayList<>();
        tempList.add(new Dot(r,c));


        while (!queue.isEmpty()){

            Dot d = queue.poll();

            for(int i = 0 ; i<4;i++){
                int nr = d.r +dr[i];
                int nc = d.c +dc[i];

                //범위를 벗어나거나
                //기준 블록과 색이 다르거나
                //검은색 블록 이면 넘어감

                if(inWall(nr,nc)){
                    if(!isVisited[nr][nc] && (map[nr][nc]==blockColor || map[nr][nc]==0)){
                        if(map[nr][nc]==0){
                            rainbow++;
                            //무지개 블록좌표를 넣음  -> 무지개 블록은 후에 false처리를 해주기 위함
                            rainBowList.add(new Dot(nr,nc));
                        }
                        count++;
                        queue.add(new Dot(nr,nc));
                        isVisited[nr][nc]=true;
                        tempList.add(new Dot(nr,nc));
                    }
                }

            }
        }

        for(Dot d : rainBowList ){ //무지개 블록 방문 취소
            isVisited[d.r][d.c]=false;
        }
            return new int[]{count,rainbow};
    }

    private static boolean inWall(int r,int c){
        if(r>=0 && r<N && c>=0 && c<N)return  true;
         else return false;
    }


    private static void  removeBlockGroup (){

        for(Dot d : finalList){
            //블록이 없음  -> -2로 함
            map[d.r][d.c]=-2;
        }

    }

    private static void antiClockWise90 (){

        int [][] tempMap = new int[N][N];

        for(int r = 0 ;  r <N ; r++){
            for(int c = 0 ; c<N;c++){
                tempMap[N-1-c][r]=map[r][c];
            }
        }
        map=tempMap;
    }

    private static void mapGravity (){

        int [][]temp = new int[N][N];

        for(int c = 0 ; c<N;c++){
            //빈 블럭 좌표
            int spaceR = -1;
            int spaceC = -1;
            
            for(int r = N-1 ; r>=0 ;r--){
                if(map[r][c]==-1) { //검은색 블록이라면
                    spaceR = -1;
                    spaceC = -1;
                    temp[r][c]=map[r][c];

               }else if(map[r][c]==-2){ //빈 블록 (-2) 이라면

                    if(spaceR==-1&& spaceC==-1){
                        spaceR = r;
                        spaceC = c;
                    }
                    temp[r][c]=-2;

                }else { //일반 블록이나 무지개 블록이라면 중력
                    if(spaceR!=-1 && spaceC!=-1){
                        temp[spaceR][spaceC] = map[r][c];
                        temp[r][c]=-2;
                        spaceR=spaceR-1;
                        spaceC=c;
                    }else {
                        temp[r][c]=map[r][c];
                    }

                }
            }
        }

        map=temp;
    }
}

