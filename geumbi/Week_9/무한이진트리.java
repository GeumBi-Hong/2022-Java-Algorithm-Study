package Sliver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2078 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());


        int countL = 0 ,  countR = 0;

        while (true){

            if(L==1&&R==1)break;
            if(L>R){ //왼쪽 (5,3) - > (5-3,3)
                L = L-R;
                countL++;

            }else if(L<R){ //오른쪽 (2,3) -> (2,3-2)
                R = R-L;
                countR++;
            }
        }
            System.out.println(countL+" "+countR);
    }
}
