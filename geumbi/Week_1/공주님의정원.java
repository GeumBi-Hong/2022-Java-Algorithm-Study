import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static HashMap<Integer, Integer> hashMap = new HashMap<>();
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        for (int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = getCalDate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            int finish = getCalDate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            if (hashMap.get(start) == null || hashMap.get(start) < finish) {
                hashMap.put(start, finish);
            }
        }

        System.out.print(getMinNumberOfFlower());
    }

    public static int getCalDate(int month , int date){

        return month*100+date;
    }

    public static int getMinNumberOfFlower(){

        final int start_Date = 301;
        final int finish_Date = 1201;

        int answer = 0 ;


        int current = start_Date;
        boolean isPassed =  false;

        while (current < finish_Date){

            int max = current;

            for (int key: hashMap.keySet()){
                if(key <= current && max <hashMap.get(key)){
                    max = hashMap.get(key);
                    isPassed = true;
                }
            }

            if(isPassed){
                answer++;
                current=max;
                isPassed =false;
            }else {
                answer=0;
                break;
            }
        }

        return answer;
    }
}

