import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    /*
    1. 찾을 순서정하기 permutation
    2. bfs 최단거리 찾기
    3. cnt 가 제일 적은 경우 찾기
     */
    int[][] boards = new int[4][4];
    int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
    ArrayList<String> list = new ArrayList<>();

    public int solution(int[][] board, int r, int c) {
        for(int i=0; i<4; i++){
            boards[i] = board[i].clone();
        }
        int cardCnt=0;

        for(int i =0; i<4; i++){
            for(int j=0; j<4; j++){
                if ( board[i][j] != 0) {
                    cardCnt++;
                }
            }
        }

        cardCnt /= 2;
        permutation("",cardCnt);
        int minCnt = 10000;

        for(String order : list ){
            int totalCnt=0;
            int tempr = r;
            int tempc = c;

            for(int i=0; i<4; i++){
                boards[i] = board[i].clone();
            }

            for (int i = 0; i < order.length(); i++) {
                int x = order.charAt(i) - '0';
                if( boards[tempr][tempc] != x) {
                    ArrayList<int[]> cntAndrc = bfs(x,tempr,tempc);
                    int minIdx = cntAndrc.get(0)[0] < cntAndrc.get(1)[0] ? 0 : 1;
                    int[] minArr = cntAndrc.get(minIdx);
                    totalCnt+= minArr[0];
                    tempr= minArr[1];
                    tempc= minArr[2];
                }
                boards[tempr][tempc] = 0;
                int[] current = bfs(x,tempr,tempc).get(0);
                totalCnt+=current[0]+2;
                tempr= current[1];
                tempc= current[2];
                boards[tempr][tempc] = 0;
            }

            minCnt = Math.min(minCnt,totalCnt);

        }

        return minCnt;
    }
    public void permutation(String perm,int depth){
        if(perm.length() == depth) {
            list.add(perm);
            return;
        }
        for(int i=1; i<=depth; i++){
            if(!perm.contains(""+i))
                permutation(perm + i,depth);
        }

    }

    public ArrayList<int[]> bfs(int num, int r, int c){
        int[][] dist = new int[4][4];
        boolean[][] visited = new boolean[4][4];

        for(int i=0; i<4; i++)
            Arrays.fill(dist[i],10000);
        dist[r][c]= 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r,c,0});
        ArrayList<int[]> end = new ArrayList<>();

        while(!q.isEmpty()){
            int[] cur =q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curCnt = cur[2];
            if( curCnt > 6 || visited[curR][curC]) continue;
            visited[curR][curC] = true;

            for(int i=0; i<4; i++){
                int nr = curR+direction[i][0];
                int nc = curC+direction[i][1];
                if (nr < 0 || nr > 3 || nc < 0 || nc > 3) continue;
                int[] ctrl = ctrlMove(curR,curC,i);
                int ctrlR = ctrl[0];
                int ctrlC = ctrl[1];

                if (boards[nr][nc] == num) {
                    if(dist[nr][nc]== 10000) {
                        end.add(new int[]{nr,nc});
                    }
                }
                else if (boards[ctrlR][ctrlC] == num) {
                    if(dist[ctrlR][ctrlC]== 10000) {
                        end.add(new int[]{ctrlR,ctrlC});
                    }
                }

                dist[ctrlR][ctrlC] = Math.min(dist[ctrlR][ctrlC],curCnt+1);
                dist[nr][nc] = Math.min(dist[nr][nc],curCnt+1);

                q.add(new int[]{nr,nc,curCnt+1});
                q.add(new int[]{ctrlR,ctrlC,curCnt+1});
            }
        }
        ArrayList<int[]> cntAndrc = new ArrayList<>();

        for(int[] endrc : end){
            cntAndrc.add(new int[]{dist[endrc[0]][endrc[1]],endrc[0],endrc[1]});
        }

        return cntAndrc;
    }

    public int[] ctrlMove(int r,int c,int dir){
        for( int i=0; i<4; i++) {
            int nr = r+ direction[dir][0];
            int nc = c+ direction[dir][1];
            if (nr < 0 || nr > 3 || nc < 0 || nc > 3) break;
            r= nr;
            c= nc;

            if(boards[r][c] != 0 ){
                break;
            }
        }
        return new int[]{r,c};
    }
}
