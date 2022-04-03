package 스터디.Week_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 소형기관차 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] train = new int[n+1];
        int[] sum = new int[n+1];

        for(int i =1; i<=n; i++) {
            train[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + train[i];
        }

        int m = Integer.parseInt(br.readLine());

        int[][] dp = new int[4][n+1];

        for( int i = 1; i<4; i++){
            for ( int j=i*m ; j<=n ; j++){
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-m] + sum[j] - sum[j-m]);
            }
        }
        for(int i=0; i<4; i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(dp[3][n]);

    }
}
