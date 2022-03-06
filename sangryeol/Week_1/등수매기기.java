import java.io.*;
import java.util.*;

public class BOJ_2012 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> ranks = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        for (int i = 0, rank; i < N; i++) {
            rank = Integer.parseInt(br.readLine());
            ranks.add(rank);
        }
        Collections.sort(ranks);
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += Math.abs(i - ranks.get(i-1));
        }
        System.out.println(sum);
    }
}
