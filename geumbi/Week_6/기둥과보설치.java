import java.util.*;


class Solution {
    
    static int up =0;
    static int  right =1;
    static int N;
    static boolean [][][] frame;
    static  int count = 0 ; //만들어 줘야하는 배열 수 {x,y, 0 or 1}

   public int[][] solution(int n, int[][] build_frame) {
        N = n;
       frame = new boolean[n + 1][n + 1][2];

       for (int[] frame : build_frame) {
           int c = frame[0]; //열
           int r = frame[1]; //행
           int beamOrPillar = frame[2];
           int installOrDelete = frame[3];

           //설치 또는 삭제
           if (installOrDelete == 0) {
               delete(r, c, beamOrPillar);
           } else {
               install(r, c, beamOrPillar);
           }


       }
       //return 해줘야되는 배열 만들기
       //배열은 x(가로)좌표 기준으로 오름차순 정렬하며, x좌표가 같을 경우 y(세로)좌표 기준으로 오름차순 정렬
       //x, y좌표가 모두 같은 경우 기둥이 보보다 앞에 오면 됨
       int[][] answer = new int[count][3];
       int idx = 0;

       for (int x = 0; x<= N; x++) {//가로좌표를 기준으로 세로좌표를 탐색해주면된다.
           for (int y = 0; y <= N; y++) {//세로

               //기둥먼저 검사하고 보 검사하면 기둥 ,보 순으로 정렬해줄 필요가 없음
               if (frame[y][x][up]) {
                   answer[idx++] = new int[]{x, y, 0}; // {가로, 세로 ,기둥}
               }

               if (frame[y][x][right]) {
                   answer[idx++] = new int[]{x, y, 1}; // {가로 ,세로 ,보}
               }
           }

       }
       return answer;
    }

    //틀 범위에서 나가지 않는지 체크
    public static boolean checkRange(int r,int c){
        if(r>=0 && c>=0&& r <=N  && c<=N) {
            return true;
        }
        return false;
    }

    public static boolean canInstallPillar(int r,int c){

        /* 기둥의 설치 조건 */
        //0.바닥 위 이거나 
        //1.보의 한쪽 끝 부분 위에 있거나 (기둥 좌표의 왼쪽과 , 현재 좌표의 보)
        //2.또다른 기둥 위
    if(r==0){
            return true;
        }
        else if ((checkRange(r,c-1)&&frame[r][c-1][right])||(checkRange(r,c)&&frame[r][c][right]) ){
            return true;
        }
        else if(frame[r-1][c][up]) {
            return true;
        }
        else return false;

    }
    public static boolean canInstallBeam (int r,int c){
        /* 보의 설치 조건 */
        //1.양쪽 끝의 다른 보와 동시에연결
        //2.보의 한쪽 끝이 기둥 위
    if (((checkRange(r,c-1)&& frame[r][c-1][right]) && (checkRange(r,c+1)&&frame[r][c+1][right]))){
            return true;
        }else if(frame[r-1][c][up] || frame[r-1][c+1][up]){
            return true;
        }
        else return false;

    }

    public static void install (int r ,int c ,int bp){ 

        if(bp==0){ //기둥
            if(canInstallPillar(r,c)){
                frame[r][c][up]=true;
                count++; //설치하였으니 배열 하나 만들어야한다. {x,y,0}
            }
        }


        if(bp==1){ //보
            if(canInstallBeam(r,c)){
                frame[r][c][right]=true;
                count++;//설치하였으니 배열 하나 만들어야한다. {x,y,1}
            }
        }
    }


    public static void delete (int r ,int c ,int bp) {

        boolean flag =false;
        //먼저 설치물을 제거한다.
        frame[r][c][bp]=false;

        
        
        //설치되어진 기둥이나 보가 있다면 보존되어질 수 있는지 (설치할 수 있는 조건인지) 알아본다.
        loop:
        for(int i = 0 ; i <=N;i++){
            for(int j = 0 ; j<=N;j++){
                if(frame[i][j][up]){
                    if(!canInstallPillar(i,j)){
                        flag = true;
                        break loop;
                    }
                }

                if(frame[i][j][right]){
              
                    if(!canInstallBeam(i,j)){
                  
                        flag = true;
                        break loop;
                    }

                }
            }
        }

        //보존이 안되는 경우가 있다면 삭제했던 설치물을 다시 설치한다.
        if(flag)frame[r][c][bp]=true;
        else {count--;}

    
    }

}
