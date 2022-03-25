import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
       
        int [][] fee = new int[n+1][n+1];
        int INF=2000001;
        int min =Integer.MAX_VALUE;
        for(int i = 1 ; i<=n;i++){
            for(int j =1;j<=n;j++){
                fee[i][j]=INF;
                if(i==j){
                    fee[i][j]=0;
                }
                
            }
        }
        
        for(int i = 0 ; i<fares.length;i++){
        
                int n1 = fares[i][0];
                int n2 = fares[i][1];
                int f = fares[i][2];
                fee[n1][n2]=Math.min( fee[n1][n2],f);
                fee[n2][n1]=Math.min( fee[n2][n1],f);
            
        }
        
        for(int k=1; k<=n;k++){ //거처가는지점
              for(int i = 1 ; i<=n;i++){//출발지점
                for(int j =1;j<=n;j++){//도착지점
                 fee[i][j]=Math.min(fee[i][j],fee[i][k]+fee[k][j]);
            }
           }
        }
        
        
        for(int i =1;i<=n;i++){
            min=Math.min(fee[s][i]+fee[i][a]+fee[i][b],min);
        }
        
        return min;
    }
}
