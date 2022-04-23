import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N,L;
    static ArrayList<Integer> arrayList = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

         N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
         L = Integer.parseInt(st.nextToken());



        st = new StringTokenizer(br.readLine()," ");

        for(int i =0 ; i< N ; i++){
          arrayList.add(Integer.parseInt(st.nextToken()));
        }


        arrayList.add(0);
        arrayList.add(L);

        Collections.sort(arrayList);

        int lo = 1;
        int high = L;

        //최대 거리의 최소값을 이분 탐색으로 찾는다.
        while (lo <= high){
            int mid =(lo+high)/2;

         //   System.out.println(lo+" "+ " "+ mid+" "+high);
            if(canInstall(mid)<=M){
                high = mid-1;
            }else {
              
                lo = mid+1;
            }
        }


        System.out.println(lo);
    }

    public static int canInstall(int mid){

        int count = 0;

        for(int i = 1 ; i < arrayList.size();i++){

           count+=(arrayList.get(i)-arrayList.get(i-1)-1)/mid;

        }

        return count;
    }
}

