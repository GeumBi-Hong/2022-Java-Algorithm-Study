package 스터디.Week_8;

public class 외벽점검 {
    public static void main(String[] args) {
        int n = 1;
        int[] weak = { };
        int[] dist = { 1, 2, 3 };
        int result = new Solution().solution(n, weak, dist);
        System.out.println(result);
    }
}

class Solution {
    private int n, answer;
    private int[] weak, dist, spreadWeak;
    private boolean finish;

    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;

        if(weak.length ==0 )
            return 0;

        makeSpreadWeak();
        finish = false;

        answer = Integer.MAX_VALUE;
        for (int i = 1; i <= dist.length; i++) {
            permutation(0, i, new boolean[dist.length], new int[i]);
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private void permutation(int depth, int num, boolean[] visited, int[] res) {
        if (finish) {
            return;
        }

        if (depth == num) {
            checkIfCanCover(res);
            return;
        }

        for(int i=0; i<dist.length; i++){
            if(!visited[i]){
                res[depth] = dist[i];
                visited[i] = true;
                permutation(depth+1, num,visited,res);
                visited[i] = false;
            }
        }
    }

    private void checkIfCanCover(int[] res) {
        for (int i = 0; i < weak.length; i++) {
            int start = i;
            boolean flag = true;

            for (int idx = 0; idx < res.length; idx++) {
                for (int j = i; j < i + weak.length; j++) {
                    if (spreadWeak[j] - spreadWeak[start] > res[idx]) {
                        start = j;
                        idx++;

                        if (idx == res.length) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    answer = idx + 1;
                    finish = true;
                    return;
                }
            }
        }
    }

    private void makeSpreadWeak() {
        int size = weak.length;
        spreadWeak = new int[size * 2 - 1];
        for (int i = 0; i < size; i++) {
            spreadWeak[i] = weak[i];
        }
        for (int i = 0; i < size - 1; i++) {
            spreadWeak[i + size] = weak[i] + n;
        }
    }

}
