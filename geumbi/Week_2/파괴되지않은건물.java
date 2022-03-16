//https://www.youtube.com/watch?v=caGtdr3_nxs 보고 똑같이 짜봤습니다.
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] array = new int[1003][1003];


        for(int i = 0; i < skill.length; i++){
            int type = skill[i][0], r1 = skill[i][1], c1 = skill[i][2],
                    r2 = skill[i][3], c2 = skill[i][4], degree = skill[i][5];
            if(type == 1) degree = -degree;
            array[r1][c1] += degree;
            array[r1][c2+1] -= degree;
            array[r2+1][c1] -= degree;
            array[r2+1][c2+1] += degree;
        }

        for(int i = 0; i < n; i++)
            for(int j = 1; j < m; j++)
                array[i][j] += array[i][j-1];


        for(int i = 1; i < n; i++)
            for(int j = 0; j < m; j++)
                array[i][j] += array[i-1][j];


        int answer = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(array[i][j]+board[i][j] > 0) answer++;
        return answer;
    }
}
