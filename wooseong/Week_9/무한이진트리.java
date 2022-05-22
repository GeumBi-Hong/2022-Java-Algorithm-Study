package 스터디.Week_9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 무한이진트리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long left =0,right=0;

        while(!(a==1 && b==1)){
            if(a<b){
                b-=a;
                right+=1;
            } else {
                a-=b;
                left+=1;
            }
        }

        System.out.print(left +" "+right);
    }
}
