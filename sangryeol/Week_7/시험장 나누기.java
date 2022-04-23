import java.util.*;

public class Programmers_시험장나누기 {
    public static void main(String[] args) {
        Solution ret = new Solution();
        System.out.println(ret.solution(3, new int[]{12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1},
                new int[][]{{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {8, 5}, {2, 10}, {3, 0}, {6, 1}, {11, -1}, {7, 4}, {-1, -1}, {-1, -1}}));
    }
}
class Room {
    int left, right;
    int peopleCount;
    Room(int left, int right, int peopleCount) {
        this.left = left;
        this.right = right;
        this.peopleCount = peopleCount;
    }
}
class Solution {
    int[] parents;
    int groupCount;
    Room[] rooms;
    final static int INF = (int)1e9;
    public int countPeoples(int curr, int value) {
        int left = rooms[curr].left;
        int right = rooms[curr].right;
        int leftCount = 0, rightCount = 0, currCount = rooms[curr].peopleCount;
        if (left != -1) leftCount = countPeoples(left, value);
        if (right != -1) rightCount = countPeoples(right, value);
        if (currCount + leftCount + rightCount <= value) return currCount + leftCount + rightCount;
        if (currCount + Math.min(leftCount, rightCount) <= value) {
            groupCount++;
            return currCount + Math.min(leftCount, rightCount);
        }
        groupCount += 2;
        return currCount;
    }
    public int solution(int k, int[] num, int[][] links) {
        int roomSize = num.length;
        rooms = new Room[roomSize];
        parents = new int[roomSize];
        Arrays.fill(parents, -1);
        for (int i = 0; i < roomSize; i++) {
            int peopleCount = num[i];
            int left = links[i][0], right = links[i][1];
            rooms[i] = new Room(left, right, peopleCount);
            if (left != -1) parents[left] = i;
            if (right != -1) parents[right] = i;
        }
        int root = 0;
        while (parents[root] != -1) root = parents[root];
        int lo = Arrays.stream(num).max().getAsInt() - 1;
        int hi = INF;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            groupCount = 1;
            countPeoples(root, mid);
            if (groupCount <= k) hi = mid;
            else lo = mid;
        }
        return hi;
    }
}
