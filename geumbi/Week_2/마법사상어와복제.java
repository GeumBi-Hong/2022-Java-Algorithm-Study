import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static class fish {
        int r;
        int c;
        int d;

        public fish(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static ArrayList<Integer>[][] array = new ArrayList[4][4];
    static ArrayList<fish> fishList = new ArrayList<>();
    static int[][] fishSmell = new int[4][4];
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int sdx[] = {0, -1, 0, 1, 0};
    static int sdy[] = {0, 0, -1, 0, 1};
    static int[] sharkArray = new int[3];
    static int[] three = new int[3];
    static int maxFish = -1;
    static int sharkX, sharkY,S;
    //←, ↖, ↑, ↗, →, ↘, ↓, ↙
    public static void main(String[] args) throws IOException {

        init();

        while (S-->0){
         copyFish();
         moveFish();
         removeSmell();
         moveShark();
         beforeFishCopy();

        }

        System.out.print(countFish());
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                array[i][j] = new ArrayList<>();
            }
        }

        //물고기 좌표 값에 방향 값 넣기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int fx = Integer.parseInt(st.nextToken()) - 1;
            int fy = Integer.parseInt(st.nextToken()) - 1;
            array[fx][fy].add(Integer.parseInt(st.nextToken())-1);
        }

        //상어좌표
        st = new StringTokenizer(br.readLine(), " ");
        sharkX = Integer.parseInt(st.nextToken()) - 1;
        sharkY = Integer.parseInt(st.nextToken()) - 1;
    }

    public static void copyFish() {
        fishList.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d : array[i][j]) {
                    fishList.add(new fish(i, j, d));
                }
            }
        }

    }

    public static void moveFish() {
        ArrayList<Integer>[][] copyArray = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyArray[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d : array[i][j]) {

                    boolean isMoved = false;
                    for (int k = 0; k < 8; k++) {
                        int nd = ((d - k) + 8) % 8;
                        int nx = i + dx[nd];
                        int ny = j + dy[nd];

                        if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
                        if (fishSmell[nx][ny]>0)continue;
                        if(nx==sharkX && ny == sharkY) continue;

                        copyArray[nx][ny].add(nd);
                        isMoved = true;
                        break;

                    }
                    if (!isMoved) copyArray[i][j].add(d);


                }
            }
        }
        array = copyArray;


    }

    public static int eatFish() {
        int curX = sharkX;
        int curY = sharkY;
        int count = 0;
        boolean[][] visited = new boolean[4][4];
        visited[curX][curY] = true;

        for (int i = 0; i < 3; i++) {

            int nx = curX + sdx[three[i]];
            int ny = curY + sdy[three[i]];

            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) return -1;

            if (!visited[nx][ny]) {
                count += array[nx][ny].size();
                visited[nx][ny] = true;
            }
            curX = nx;
            curY = ny;
        }
        return count;
    }

    public static void backTrackDFS(int depth) {

        if (depth == 3) {

            int total_fish = eatFish();

            if (maxFish < total_fish) {
                maxFish = total_fish;
                for (int i = 0; i < 3; i++) {
                    sharkArray[i] = three[i];
                }
            }
            return;
        }
        for (int i = 1; i <= 4; i++) {
            three[depth] = i;
            backTrackDFS(depth + 1);
        }
    }

    public static void moveShark() {
        maxFish=-1;
        backTrackDFS(0);

        for (int i = 0; i < 3; i++) {
            int nx = sharkX + sdx[sharkArray[i]];
            int ny = sharkY + sdy[sharkArray[i]];

            if (array[nx][ny].size() != 0) {
                fishSmell[nx][ny] = 2;
                array[nx][ny].clear();
            }

            sharkX = nx;
            sharkY = ny;
        }
    }

    public static void removeSmell() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(fishSmell[i][j]>0){
                    fishSmell[i][j]--;
                }
            }
        }
    }
    public static void beforeFishCopy(){
        for(fish f: fishList){
            array[f.r][f.c].add(f.d);
        }
    }

    public static int countFish(){
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
              count+=array[i][j].size();
            }
        }
        return count;
    }
}

