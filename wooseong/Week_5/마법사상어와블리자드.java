
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법사상어와블리자드 {
    static int[][] grid;
    static int[][] disi;
    static int n, m,blizzardIdx;
    static int shark;
    static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
    static int[][] searchDir = {{0,-1},{1,0},{0,1},{-1,0}};
    static int cnt =0 ;

    public static void main(String[] args) throws IOException {
        init();
        blizzard();
        for (int i = 0; i < n; i++) {System.out.println(Arrays.toString(grid[i]));}
        beedPull();
        for (int i = 0; i < n; i++) {System.out.println(Arrays.toString(grid[i]));}
        beedBoom();

        Queue<Integer> q = copyBeed();



        for (int i = 0; i < n; i++) {System.out.println(Arrays.toString(grid[i]));}
        System.out.println(cnt);
    }

    private static void beedBoom() {
        boolean flag = true;
        Deque<Integer> waitq = new LinkedList<>();
        Queue<Integer> storeq = new LinkedList<>();
        while(flag) {
            flag = false;
            Queue<Integer> storedq = copyBeed();
            while (!storedq.isEmpty()) {
                if (waitq.isEmpty()) {
                    waitq.add(storedq.poll());
                    System.out.println(1);
                } else if (waitq.peekLast() == storedq.peek()) {
                    waitq.add(storedq.poll());
                    System.out.println(2);
                } else if (waitq.peekLast() != storedq.peek() && waitq.size() >= 4) {
                    while (!waitq.isEmpty()) cnt += waitq.pollFirst();
                    flag = true;
                    waitq.add(storedq.poll());
                    System.out.println(3);
                } else {
                    while (!waitq.isEmpty()) storeq.add(waitq.poll());
                    System.out.println(4);
                }
            }
            if (waitq.size() >= 4) {
                while (!waitq.isEmpty()) cnt += waitq.pollFirst();
                flag = true;
            } else {
                while (!waitq.isEmpty()) {
                    storeq.add(waitq.poll());
                }
            }

            pasteBeed(storeq);
        }
    }

    private static void beedPull() {
        pasteBeed(copyBeed());
    }

    private static Queue<Integer> copyBeed() {
        int dir = 0;
        int r = shark;
        int c = shark;
        Queue<Integer> storeq = new LinkedList<>();

        // 0이아닌걸저장
        for(int i=1; i<=n; i++){
            for(int j=0; j<2; j++){
                for(int k = 0; k<i ; k++){
                    r+= searchDir[dir][0];
                    c+= searchDir[dir][1];
                    if (r<0 || r>=n || c<0 || c>=n) break;
                    if ( grid[r][c] != 0 ) {
                        storeq.add(grid[r][c]);
                    }
                }
                dir++;
                dir %= 4;
            }
        }
        return storeq;
    }

    private static void pasteBeed(Queue<Integer> storeq) {
        int c;
        int dir;
        int r;
        dir = 0;
        r= shark;
        c= shark;
        // 다시 붙여넣기
        for(int i=1; i<=n; i++){
            for(int j=0; j<2; j++){
                for(int k = 0; k<i ; k++){
                    r+= searchDir[dir][0];
                    c+= searchDir[dir][1];
                    if (r<0 || r>=n || c<0 || c>=n) break;
                    grid[r][c] = storeq.isEmpty() ? 0 : storeq.poll();
                }
                dir++;
                dir %= 4;
            }
        }
    }

    private static void blizzard() {
        blizzardIdx = 0;
        int di = disi[blizzardIdx][0];
        int si = disi[blizzardIdx][1];
        blizzardIdx++;

        for(int i=1; i<=si; i++) {
            grid[shark + direction[di][0]*i][shark + direction[di][1]*i] = 0;
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        disi = new int[m][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            disi[i][0] = Integer.parseInt(st.nextToken())-1;
            disi[i][1] = Integer.parseInt(st.nextToken());
        }
        shark = n / 2;
    }
}
