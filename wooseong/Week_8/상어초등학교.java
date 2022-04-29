package 스터디.Week_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 상어초등학교 {
    /*
    1. 2중 for문으로 동서남북 체크
    2. 좋아하는학생 많은칸 , 빈칸 많은 칸, 행번호작은칸, 행번호 큰칸순으로 힙큐에 넣기
     */

    static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static class Count implements Comparable<Count>{
        int likeCnt,emptyCnt, row, col;

        public Count(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Count target) {
            if(likeCnt == target.likeCnt){
                if(emptyCnt == target.emptyCnt){
                    if(row == target.row){
                        return col - target.col;
                    }
                    return row - target.row;
                }
                return target.emptyCnt - emptyCnt;
            }
            return target.likeCnt-likeCnt;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int stdNum = n*n;

        int[][] grid = new int[n][n];
        int[][] stdLike = new int[stdNum+1][4];
        Queue<Integer> order = new LinkedList<>();

        for(int i=0; i<stdNum; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            order.add(student);
            for(int j=0; j<4; j++){
                stdLike[student][j] = Integer.parseInt(st.nextToken());
            }
        }
        PriorityQueue<Count> pq = new PriorityQueue<>();

        while(!order.isEmpty()){
            int now = order.poll();
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(grid[i][j] != 0) continue;
                    Count count = new Count(i,j);
                    for(int k=0; k<4; k++){
                        int nr = i+dir[k][0];
                        int nc = j+dir[k][1];

                        if(nr <0 || nr >= n|| nc < 0 || nc >= n) continue;

                        for(int l=0; l<4; l++){
                            if(grid[nr][nc] == stdLike[now][l]){
                                count.likeCnt++;
                                break;
                            }
                        }
                        if(grid[nr][nc] == 0 ){
                            count.emptyCnt ++;
                        }
                    }
                    pq.add(count);
                }
            }
            Count result = pq.poll();
            pq.clear();
            grid[result.row][result.col] = now;
        }

        int answer =0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) {
                int now = grid[i][j];
                int cnt = 0;

                for (int k = 0; k < 4; k++) {
                    int nr = i + dir[k][0];
                    int nc = j + dir[k][1];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                    for (int l = 0; l < 4; l++) {
                        if (grid[nr][nc] == stdLike[now][l]) {
                            cnt++;
                            break;
                        }
                    }
                }
                if(cnt ==0 ) continue;
                answer += Math.pow(10,cnt-1);
            }
        }
        System.out.println(answer);
    }
}
