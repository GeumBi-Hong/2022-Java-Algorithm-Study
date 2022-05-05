package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main10775 {
    static int[] airport;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        airport = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            airport[i] = i;
        }
        int ans = 0;
        for (int i=0;i<P;i++) {
            int g = Integer.parseInt(br.readLine());
            int emptyGate = find(g);

            if (emptyGate != 0) {//게이트에 도킹이 가능할때
                ans++;
                union(emptyGate, emptyGate - 1); //다음 차선책
            } else { //더 이상 차선책 게이트가 없을경우
                break;
            }
        }
        System.out.println(ans);
    }

   private static int find(int x) {
        if (airport[x] == x)
            return x;
        else
            return airport[x] = find(airport[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x!=y)
            airport[x] = y;
    }

}
