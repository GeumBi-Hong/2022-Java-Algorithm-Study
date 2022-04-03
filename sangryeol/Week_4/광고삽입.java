class Solution {
    final int MAXSZ = 400_000;
    long[] pSum = new long[MAXSZ];
    public int changeTimeToSecond(String time) {
        // HH:MM:SS
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]) * 3600;
        int minute = Integer.parseInt(times[1]) * 60;
        int second = Integer.parseInt(times[2]);
        return hour + minute + second;
    }
    public String changeSecondToTime(int time) {
        String hour = String.format("%02d", time / 3600);
        String minute = String.format("%02d", (time % 3600) / 60);
        String second = String.format("%02d", (time % 3600) % 60);
        return hour + ":" + minute + ":" + second;
    }
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = changeTimeToSecond(play_time);
        int advTime = changeTimeToSecond(adv_time);
        for (String log : logs) {
            String[] times = log.split("-");
            int start = changeTimeToSecond(times[0]);
            int end = changeTimeToSecond(times[1]);
            pSum[start]++;
            pSum[end]--;
        }
        for (int i = 1; i < MAXSZ; i++) {
            pSum[i] += pSum[i-1];
        }
        for (int i = 1; i < MAXSZ; i++) {
            pSum[i] += pSum[i-1];
        }
        long maxTime = pSum[advTime - 1];
        int atTime = 0;
        for (int i = advTime; i < playTime; i++) {
            long time = pSum[i] - pSum[i - advTime];
            if (maxTime < time) {
                maxTime = time;
                atTime = i - advTime + 1;
            }
        }
        return changeSecondToTime(atTime);
    }
}