import java.io.*;
import java.util.*;

public class BOJ_3107 {
    public static String addZeroPrefix(String section) {
        return "0" + section;
    }
    public static String addZeroPadding(String section) {
        String zero = "0".repeat(4 - section.length());
        return zero + section;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ip = br.readLine();
        if (ip.charAt(ip.length() - 1) == ':') {
            ip += '0';
        }
        String[] sections = ip.split(":");
        int last = sections.length - 1;
        sections[0] = addZeroPrefix(sections[0]);
        sections[last] = addZeroPrefix(sections[last]);
        String answer = "";
        for (String section : sections) {
            String tmp = section;
            if (section.length() > 4) {
                tmp = section.substring(1, 5);
            }
            if (tmp.length() != 0) {
                answer += addZeroPadding(tmp) + ":";
            }
            else {
                int zeroSectionCount = 8 - (sections.length - 1);
                for (int i = 1; i <= zeroSectionCount; i++) {
                    answer += addZeroPadding(tmp) + ":";
                }
            }
        }
        System.out.println(answer.substring(0, answer.length() - 1));
    }
}