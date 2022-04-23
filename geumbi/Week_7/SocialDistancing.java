import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// !문제에서 시작 지점과 끝 지점 범위를 보면 최대 10^18이며 int형 범위를 넘는것에 주의
public class Main {

    static class Grass  {
        long start;
        long end;

        public Grass(long start, long end) {
            this.start = start;
            this.end = end;
        }

    }


    static  int N,M;
    static List<Grass> arrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        for(int i = 0 ; i  < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            long start =Integer.parseInt(st.nextToken());
            long end = Integer.parseInt(st.nextToken());
            arrayList.add(new Grass(start, end));
        }

        //시작 인덱스 순으로 정렬시킨다.
        //문제에서 입력값들이 정렬되서 온다는 보장이 없음.
        Comparator<Grass> comparator = new Comparator<Grass>() {
            @Override
            public int compare(Grass o1, Grass o2) {
                if (o1.start > o2.start) {
                    return 1;
                } else if (o1.start == o2.start) {
                    return 0;
                } else {
                    return -1;
                }
            }
        };

        //정렬하기
        Collections.sort(arrayList,comparator);


        long lo = 1;
        long high = (long)1e18; //10^18

        while (lo<=high){

            long mid = (lo+high)/2;

            if(canPutCow(mid)>=N){
                lo  = mid+1;
            }else {
                high = mid-1;
            }
            
        }
       System.out.println(lo-1);

    }

    public static long canPutCow(long d){


       //첫번째 좌표에 소가 무조건 하나 있다고 가정
        long count = 1;
        //이전 소의 좌표
        long lastCowIndex = arrayList.get(0).start;

        for(int i = 0 ; i < arrayList.size();i++){
            
            //이전 소의좌표에서 거리 d 만큼 더했을때 잔디의 끝 좌표보다 작거나 같다면 소를 거리 d만큼 배치 할 수 있다.
            while (lastCowIndex + d  <= arrayList.get(i).end){
                count++; //배치된 소의 마리 수를 저장한다.
                
                // ex )  0 (소) 2(소) 
                
                //소의 좌표를 갱신한다.
                //ex ) d=2
                // 잔디 0 2 였을때 소의 이전 좌표는 2이다. (0과 2에 소 배치함)
                //만약 다음 잔디가 5 7 이라면 다음 좌표인 (이전좌표 2+  거리 2 )=4 잔디가 없기때문에  소가 배치될 수 없다.
                //따라서 잔디의 시작점(5)과 이전소의좌표+d (4)를 비교하여 갈 수있는 다음좌표보다 잔디의 시작점이 크다면 잔디의 시작점으로 소의 이전위치를 갱신시킨다.
                lastCowIndex = Math.max(arrayList.get(i).start,lastCowIndex+d);
            }
        }
      return count;
    }
}

