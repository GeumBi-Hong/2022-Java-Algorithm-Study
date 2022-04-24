import java.io.*;
import java.util.*;

class Boundary implements Comparable<Boundary> {
    int s, e;
    Boundary(int s, int e) {
        this.s = s;
        this.e = e;
    }
    @Override
    public int compareTo(Boundary o) {
        return s - o.s;
    }
}

public class BOJ_18877 {
    final static long INF = (long)1e18;
    static int N, M;
    static ArrayList<Boundary> boundaries;
    public static boolean isPossible(long value) {
        long cowCnt = 0, prev = -INF;
        for (Boundary boundary : boundaries) {
            int s = boundary.s, e = boundary.e;
            if (prev + value > e) continue;
            prev = Math.max(prev + value, s);
            long intervalCnt = (e - prev) / value;
            cowCnt += intervalCnt + 1;
            prev += value * intervalCnt;
        }
        return cowCnt >= N;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        boundaries = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            boundaries.add(new Boundary(s, e));
        }
        Collections.sort(boundaries);
        long lo = 0L, hi = INF;
        while (lo + 1 < hi) {
            long mid = (lo + hi) / 2;
            if (isPossible(mid)) lo = mid;
            else hi = mid;
        }
        System.out.println(lo);
    }
}
