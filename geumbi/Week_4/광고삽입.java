import java.util.StringTokenizer;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {


        int playTime = timeToSec(play_time); 
        int advTime = timeToSec(adv_time); 


        long[] dp = new long[playTime + 1];

        for (String log : logs) {
            String[] split = log.split("-");
            dp[timeToSec(split[0])]++; //시작 +1
            dp[timeToSec(split[1])]--; //종료 -1
        }

        for (int i = 1; i <= playTime; i++) dp[i] += dp[i - 1]; 
        for (int i = 1; i <= playTime; i++) dp[i] += dp[i - 1];


        long maxTime = dp[advTime - 1], maxStartTime = 0;
        
        for (int i = 0; i + advTime <= playTime; i++) {
            long tmp = dp[i + advTime] - dp[i];

            if (tmp > maxTime) {
                maxTime = tmp;
                maxStartTime = i + 1;
            }
        }

        return String.format("%02d:%02d:%02d", maxStartTime / (60 * 60), (maxStartTime / 60) % 60, maxStartTime % 60);
    }

    int timeToSec(String str) {
        StringTokenizer st = new StringTokenizer(str,":");
        int [] arr = new int[3];

        for(int i = 0; i <3;i++){
            arr[i]= Integer.parseInt(st.nextToken());
        }
        return (arr[0] * 60 * 60) + (arr[1] * 60) + arr[2];
    }
}
