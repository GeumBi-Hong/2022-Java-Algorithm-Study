
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 휴게소세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        ArrayList<Integer> highway = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int place = Integer.parseInt(st.nextToken());
            highway.add(place);
        }
        highway.add(0);
        highway.add(l);
        Collections.sort(highway);

        int lo=1;
        int hi=l;
        while(lo<=hi){
            int mid = (lo +hi) /2;
            int stationCnt=0;
            for(int i=0; i< highway.size()-1; i++){
                stationCnt+=(highway.get(i + 1) - highway.get(i)-1)/mid;
            }
            if (stationCnt > m) lo = mid+1;
            else hi = mid-1;
        }

        System.out.println(lo);

    }

}
