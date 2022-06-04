package 스터디.Week_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불 {
    static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
        String[][] maze = new String[R][C];
        Queue<Node> jihoonq = new LinkedList<>();
        Queue<Node> fireq = new LinkedList<>();

        int[][] f_visit = new int[R][C];
        int[][] j_visit = new int[R][C];

        for(int i=0; i<R; i++){
            String[] split = br.readLine().split("");
            for(int j=0; j<C; j++){
                maze[i][j] = split[j];
                if(maze[i][j].equals("F")){
                    fireq.add(new Node(i,j));
                }
                else if(maze[i][j].equals("J")){
                    jihoonq.add(new Node(i,j));
                }
            }
        }
        while (!fireq.isEmpty()){
            Node now = fireq.poll();
            for(int i=0; i<4; i++){
                int nr = now.r + dir[i][0];
                int nc = now.c + dir[i][1];
                if(nr<0 || nr>=R || nc<0 || nc >=C || maze[nr][nc].equals("#") || f_visit[nr][nc]!=0){
                    continue;
                }
                f_visit[nr][nc] = f_visit[now.r][now.c]+1;
                fireq.add(new Node(nr,nc));
            }
        }

        while (!jihoonq.isEmpty()){
            Node now = jihoonq.poll();
            for(int i=0; i<4; i++){
                int nr = now.r + dir[i][0];
                int nc = now.c + dir[i][1];
                if(nr<0 || nr>=R || nc<0 || nc >=C){
                    System.out.println(j_visit[now.r][now.c]+1);
                    return;
                }
                if (j_visit[nr][nc]!=0 || maze[nr][nc].equals("#") || (f_visit[nr][nc]!=0 && f_visit[nr][nc]<= j_visit[now.r][now.c]+1)){
                    continue;
                }
                j_visit[nr][nc] = j_visit[now.r][now.c]+1;
                jihoonq.add(new Node(nr,nc));
            }
        }

        System.out.println("IMPOSSIBLE");

    }
}
