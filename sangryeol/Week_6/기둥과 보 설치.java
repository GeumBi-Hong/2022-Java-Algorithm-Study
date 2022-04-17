import java.util.*;

public class Programmers_기둥과보 {
    class Struct implements Comparable<Struct> {
        int x, y, a;
        public Struct(int x, int y, int a) {
            this.x = x;
            this.y = y;
            this.a = a;
        }
        public boolean isColumn() {
            return a == 0;
        }
        public boolean hasBaseColumn(TreeSet<Struct> cache) {
            return y == 0 ||
                    cache.contains(new Struct(x, y - 1, 0)) ||
                    cache.contains(new Struct(x, y, 1)) ||
                    cache.contains(new Struct(x - 1, y, 1));
        }
        public boolean hasBaseBo(TreeSet<Struct> cache) {
            return cache.contains(new Struct(x, y - 1, 0)) ||
                    cache.contains(new Struct(x + 1, y - 1, 0)) ||
                    (cache.contains(new Struct(x - 1, y, 1)) && cache.contains(new Struct(x + 1, y, 1)));
        }
        public int[] getArray() {
            return new int[]{x, y, a};
        }
        @Override
        public int compareTo(Struct o) {
            if (x != o.x) return x - o.x;
            else if (y != o.y) return y - o.y;
            return a - o.a;
        }
    }
    class Solution {
        public boolean isPossible(TreeSet<Struct> cache) {
            for (Struct struct : cache) {
                if (struct.isColumn()) {
                    if (!struct.hasBaseColumn(cache)) return false;
                }
                else if (!struct.hasBaseBo(cache)) return false;
            }
            return true;
        }
        public int[][] solution(int n, int[][] build_frames) {
            TreeSet<Struct> cache = new TreeSet<>();
            for (int[] build_frame : build_frames) {
                Struct struct = new Struct(build_frame[0], build_frame[1], build_frame[2]);
                int type = build_frame[3];
                if (type == 0) { // erase
                    cache.remove(struct);
                    if (!isPossible(cache)) cache.add(struct);
                }
                else { // add
                    cache.add(struct);
                    if (!isPossible(cache)) cache.remove(struct);
                }
            }
            int i = 0, structureCount = cache.size();
            int[][] answer = new int[structureCount][3];
            for (Struct struct : cache) {
                answer[i++] = struct.getArray().clone();
            }
            return answer;
        }
    }
}
