import java.io.*;
import java.util.*;

public class BOJ_1477 {
    static int N, M, L;
    static ArrayList<Integer> areas;
    public static boolean isPossible(int value) {
        int count = 0;
        for (int i = 0; i < areas.size() - 1; i++) {
            int section = areas.get(i + 1) - areas.get(i) - 1;
            count += section / value;
        }
        return count > M;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        areas = new ArrayList<>();
        areas.add(0); areas.add(L);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            areas.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(areas);
        int lo = 0, hi = L;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (isPossible(mid)) lo = mid;
            else hi = mid;
        }
        System.out.println(hi);
    }
}
