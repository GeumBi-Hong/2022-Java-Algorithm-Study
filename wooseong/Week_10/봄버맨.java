package 스터디.Week_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 봄버맨 {
    static int[][] grid;
    static int r,c,n;
    static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        String[] temp= new String[r];
        for(int i=0; i<r; i++){
            temp[i] = br.readLine();
        }
        grid = new int[r][c];


        for(int i=0; i<r; i++){
            Arrays.fill(grid[i],-1);
            for(int j=0; j<c; j++){
                if (temp[i].charAt(j) == '0')
                    grid[i][j] = 0;
            }
        }
        int sec=1;
        while(sec<n){
            sec+=1;
            bombInstall(sec);
            boom(sec);
        }
        for(int i=0; i<r; i++){
            String ans = "";
            for(int j=0; j<c; j++){
                if(grid[i][j] >-1){
                    ans+="0";
                }
                else if(grid[i][j] ==-1){
                    ans+=".";
                }
            }
            System.out.println(ans);
        }
    }
    private static void boom(int s){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(s - grid[i][j]==3){
                    dfs(i,j,s);
                }
            }
        }
    }
    private static void dfs(int i,int j, int s){
        if( i<0 || i>=r || j<0 || j>=c || s-grid[i][j]!=3){
            return;
        }
        for(int d=0; d<4; d++){
            int nr = i+dir[d][0];
            int nc = j+dir[d][1];
            if(nr<0 || nr>=r || nc<0 || nc>=c){
                continue;
            }
            if (s - grid[nr][nc] != 3) {

                grid[nr][nc] =-1;
            }
        }
        grid[i][j] =-1;

    }
    private static void bombInstall(int s){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(grid[i][j]==-1){
                    grid[i][j] =s;
                }
            }
        }
    }
}

