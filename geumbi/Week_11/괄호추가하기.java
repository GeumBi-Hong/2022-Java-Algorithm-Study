import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Character> ops;
    static ArrayList<Integer> nums;
    static int answer;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        ops = new ArrayList<>();
        nums = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            char c = input.charAt(i);
            if (c == '-' || c == '+' || c == '*') {
                ops.add(c);
                continue;
            }
            nums.add(Character.getNumericValue(c));
        }

        answer = Integer.MIN_VALUE;
        backTracking(nums.get(0), 0);
        System.out.print(answer);
    }

    public static int calcultate(char op, int n1, int n2) {
        switch (op) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
        }
        return -1;
    }


    public static void backTracking(int result, int depth) {
        if (depth >= ops.size()) {
            answer = Math.max(answer, result);
            return;
        }
        int res1 = calcultate(ops.get(depth), result, nums.get(depth + 1));
        backTracking(res1, depth + 1);

        if (depth + 1 < ops.size()) {
            int res2 = calcultate(ops.get(depth + 1), nums.get(depth + 1), nums.get(depth + 2));
            backTracking(calcultate(ops.get(depth), result, res2), depth + 2);
        }
    }

}
