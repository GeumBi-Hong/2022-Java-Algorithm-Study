import java.io.*;
import java.util.*;

class Flower {
    int startDate;
    int endDate;
    Flower(int s, int e) {
        startDate = s;
        endDate = e;
    }
}

public class BOJ_2457 {
    static int N;
    static Flower[] flowers;
    static final int fStartDate = 301;
    static final int fEndDate = 1130;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        flowers = new Flower[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            flowers[i] = new Flower(start, end);
        }
        Arrays.sort(flowers, (o1, o2) -> {
            if (o1.startDate != o2.startDate) return o1.startDate - o2.startDate;
            return o1.endDate - o2.endDate;
        });
        int i = 0, answer = 0;
        int currDate = fStartDate;
        while (currDate <= fEndDate) {
            int maxDate = 0;
            for (; i < N && currDate >= flowers[i].startDate; i++) {
                if (maxDate < flowers[i].endDate) {
                    maxDate = flowers[i].endDate;
                }
            }
            if (maxDate == 0) {
                System.out.println(0);
                return;
            }
            currDate = maxDate;
            answer++;
        }
        System.out.println(answer);
    }
}
