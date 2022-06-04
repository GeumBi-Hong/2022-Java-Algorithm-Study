package 스터디.Week_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();
        Stack<Character> stack = new Stack<>();

        int bombLen = bomb.length();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
            if (stack.size() >= bombLen) {
                boolean flag = true;
                for (int j = 0; j < bombLen; j++) {
                    if (stack.get(stack.size() - bombLen + j) != bomb.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for(int j=0; j<bombLen; j++){
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder answer=new StringBuilder();
        for(Character c : stack){
            answer.append(c);
        }
        String ans = answer.toString();
        ans = ans.length() ==0 ? "FRULA" : ans;
        System.out.println(ans);
    }
}
