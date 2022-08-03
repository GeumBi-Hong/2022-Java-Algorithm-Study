package 스터디.Week_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 괄호추가 {

    public static int length;

    public static char[] expression;

    public static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        length = Integer.parseInt(br.readLine());
        expression = br.readLine().toCharArray();

        makeExpression(2, expression[0] - '0');

        System.out.println(MAX);

    }

    public static void makeExpression(int now, int total) {
        System.out.println(total);
        if (now >= length) {
            MAX = Math.max(MAX, total);
            return;
        }
        makeExpression(now + 2, calculate(total, expression[now] - '0', expression[now - 1]));

        if (now + 2 < length) {
            int sum = calculate(expression[now] - '0', expression[now + 2] - '0', expression[now + 1]);
            int sumTotal = calculate(total, sum, expression[now - 1]);
            makeExpression(now + 4, sumTotal);

        }

    }

    public static int calculate(int sum, int plus, char sep) {
        if (sep == '+') {return sum + plus;}
        if (sep == '-') {return sum - plus;}
        return sum * plus;
    }
}
