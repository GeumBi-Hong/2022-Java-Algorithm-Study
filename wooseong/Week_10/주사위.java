package 스터디.Week_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 주사위 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long n = Long.parseUnsignedLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Long[] dice = new Long[6];
        Long s = new Long(0);
        for(int i=0; i<6; i++){
            dice[i] = Long.parseUnsignedLong(st.nextToken());
        }

        Arrays.sort(dice);
        if( n==1){
            for(int i=0; i<5; i++){
                s+= dice[i];
            }
            System.out.println(s);
            return;
        }
        ArrayList<Long> minLists = new ArrayList<>();

        minLists.add(Math.min(dice[0],dice[5]));
        minLists.add(Math.min(dice[1],dice[4]));
        minLists.add(Math.min(dice[2],dice[3]));

        Long threePlane = minLists.get(0) + minLists.get(1) + minLists.get(2);
        Long twoPlane = minLists.get(0) + minLists.get(1);
        Long onePlane = minLists.get(0);

        Long sum = onePlane*(4 * (n-2)* (n-1) + (n-2)*(n-2));
        sum+= twoPlane*(4 *(n-1) + 4*(n-2));
        sum+= 4*threePlane;

        System.out.println(sum);

    }
}
