package 스터디.Week_4;

public class 광고삽입 {
    public static void main(String[] args) {
        String play_time = "50:00:00";
        String adv_time = "50:00:00";
        String logs[] = {
                "15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"
        };

        long[] times = new long[toSec(play_time)+1];

        // 30만 * 4
        for (int i = 0; i < logs.length; i++) {
            String[] log = logs[i].split("-");
            times[toSec(log[0])] += 1;
            times[toSec(log[1])] -= 1;
        }

        // 36만 중복시간 누적합 , 구간합
        for (int i = 1; i < times.length; i++) {
            times[i] += times[i - 1];
        }
        for (int i = 1; i < times.length; i++) {
            times[i] += times[i - 1];
        }

        int advs = toSec(adv_time);
        long intervalSum = times[advs-1];
        int answer = 0;

        for (int i = advs; i < times.length; i++) {
            if( intervalSum < times[i] -times[i-advs] ){
                intervalSum = times[i] -times[i-advs];
                answer = i-advs+1;
            }
        }

        int hour = answer / 3600;
        int min = answer % 3600 / 60;
        int sec = answer % 3600 % 60;
        String ans = "";


        ans = toTime(hour) + ":" + toTime(min) + ":" + toTime(sec);

        System.out.println(ans);

    }

    public static String toTime(int t) {
        if (t < 10) {
            return "0" + t;
        }
        return String.valueOf(t);
    }

    public static int toSec(String time) {
        int sec = 0;
        String[] t = time.split(":");
        sec += Integer.parseInt(t[0]) * 3600;
        sec += Integer.parseInt(t[1]) * 60;
        sec += Integer.parseInt(t[2]);

        return sec;
    }

}
