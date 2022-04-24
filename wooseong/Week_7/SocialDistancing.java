
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SocialDistancing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<long[]> grass = new ArrayList<>();
        long max = 0;
        long min = (long) 1e18;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());
            grass.add(new long[] { a, b });
            min = min > a ? a : min;
            max = max < b ? b : max;
        }
        Collections.sort(grass, (l1, l2) -> {
            if (l1[0] == l2[0]) {return l1[1] < l2[1] ? -1 : 1;}

            return l1[0] < l2[0] ? -1 : 1;
        });

        long lo = 1;
        long hi = max;
        long answer = 0;

        while (lo <= hi) {
            long mid = (lo + hi) / 2;
            long cowCnt = 1;
            long startPoint = min;
            for (long[] grassZone : grass) {
                long front = grassZone[0];
                long rear = grassZone[1];
                while (startPoint + mid <= rear) {
                    startPoint = Math.max(startPoint + mid, front);
                    cowCnt++;
                }
            }
            if (cowCnt >= n) {
                lo = mid + 1;
                answer = mid;
            } else {hi = mid - 1;}
        }

        System.out.println(answer);
    }
}
