
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 상어중학교 {
    /*
    1.가장큰 블록그룹찾기 , 무지개블록수 , 가장큰 행번호 , 가장큰 열 순으로 젤큰거
    2. 1.에서 찾은거 제거후 블록수의 제곱 점수획득
    3. 중력작용 ( 검은색블록빼고 다 행이큰방향으로)
    4. 90도회전
    5. 중력작용
     */
    private static int[][] grid;
    private static int n, answer;
    private static boolean[][] visited, rainbowVisted;
    private static Block block;
    private static Queue<int[]> q;
    private static boolean flag;

    private static class Block implements Comparable<Block> {
        private int blockCnt, rainbowBlock;
        private int row, col;

        public Block(int row, int col) {
            this.row = row;
            this.col = col;
            blockCnt = 0;
            rainbowBlock = 0;
        }

        @Override
        public int compareTo(Block target) {
            if (blockCnt == target.blockCnt) {
                if (rainbowBlock == target.rainbowBlock) {
                    if (row == target.row) {
                        return target.col - col;
                    }
                    return target.row - row;
                }
                return target.rainbowBlock - rainbowBlock;
            }
            return target.blockCnt - blockCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        flag = true;

        while (flag) {
            int[] rowcol = blockCheck();
            if (!flag) {break;}
            removeBlock(rowcol);
            gravity();
            spin();
            gravity();
        }

        System.out.println(answer);

    }

    //블록제거
    private static void removeBlock(int[] rowcol) {
        visited = new boolean[n][n];
        int row = rowcol[0];
        int col = rowcol[1];
        removeDfs(row, col, grid[row][col]);
    }

    //회전
    private static void spin() {
        int[][] spinnedGrid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                spinnedGrid[i][j] = grid[j][n - i - 1];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = spinnedGrid[i][j];
            }
        }
    }

    //중력
    private static void gravity() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int j = 0;
            while (j < n) {
                if (grid[j][i] != -2) {
                    stack.push(grid[j][i]);
                }

                if (j == n - 1 || grid[j][i] == -1) {
                    int k = j;
                    while (!stack.isEmpty()) {
                        grid[k][i] = stack.pop();
                        k--;
                    }
                    j++;
                    continue;
                }

                grid[j][i] = -2;
                j++;
            }
        }
    }

    //없앨 블록선정
    private static int[] blockCheck() {
        visited = new boolean[n][n];
        PriorityQueue<Block> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] != -1 && grid[i][j] != 0 && grid[i][j] != -2) {
                    rainbowVisted = new boolean[n][n];
                    block = new Block(i, j);
                    dfs(i, j, grid[i][j]);
                    pq.add(block);
                }
            }
        }
        if (pq.isEmpty()) {
            flag = false;
            return new int[] { -1, -1 };
        }
        Block checkedBlock = pq.poll();
        if (checkedBlock.blockCnt < 2) {
            flag = false;
            return new int[] { -1, -1 };
        }
        answer += checkedBlock.blockCnt * checkedBlock.blockCnt;
        return new int[] { checkedBlock.row, checkedBlock.col };
    }

    //입력받기
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = 0;
    }

    //블록 선정 dfs
    private static void dfs(int r, int c, int color) {
        if (r < 0 || r >= n || c < 0 || c >= n || grid[r][c] == -1 || visited[r][c] || grid[r][c] == -2
            || rainbowVisted[r][c]) {return;}

        if (grid[r][c] != color && grid[r][c] != 0) {return;}

        if (grid[r][c] == color) {
            block.blockCnt++;
            visited[r][c] = true;
        }

        if (grid[r][c] == 0) {
            block.rainbowBlock++;
            block.blockCnt++;
            rainbowVisted[r][c] = true;
        }

        dfs(r + 1, c, color);
        dfs(r - 1, c, color);
        dfs(r, c + 1, color);
        dfs(r, c - 1, color);
    }

    // 블록제거 dfs
    private static void removeDfs(int r, int c, int color) {
        if (r < 0 || r >= n || c < 0 || c >= n || grid[r][c] == -1 || visited[r][c]) {return;}

        visited[r][c] = true;
        if (grid[r][c] != color && grid[r][c] != 0) {return;}
        if (grid[r][c] == color || grid[r][c] == 0) {grid[r][c] = -2;}

        removeDfs(r + 1, c, color);
        removeDfs(r - 1, c, color);
        removeDfs(r, c + 1, color);
        removeDfs(r, c - 1, color);
    }
}
