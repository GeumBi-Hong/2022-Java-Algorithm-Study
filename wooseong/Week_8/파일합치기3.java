package 스터디.Week_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파일합치기3 {
    /*
    1. 제일작은거 2개 더하고 더한값을 우선순위큐에 갱신
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                pq.add(Long.parseLong(st.nextToken()));
            }
            long answer = 0;

            while (!pq.isEmpty()) {
                long first = pq.poll();
                if (pq.isEmpty()) {break;}
                long second = pq.poll();
                long sum = first + second;
                answer += sum;

                pq.add(sum);
            }
            System.out.println(answer);
        }
    }
}
