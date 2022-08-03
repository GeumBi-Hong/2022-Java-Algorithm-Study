import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();


        Stack<Character> stack = new Stack<>();

        for(int i = 0; i<str.length();i++){
            stack.push(str.charAt(i));



            if(stack.size()>=bomb.length()){
                boolean hasBomb = true;
                for(int k = 0 ; k < bomb.length();k++){
                    if(stack.get(stack.size()-bomb.length()+k)!=bomb.charAt(k)){
                        hasBomb=false;
                        break;
                    }
                }

                if(hasBomb){

                    for(int n = 0 ; n<bomb.length();n++){
                        stack.pop();
                    }
                }

            }
        }


        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i<stack.size();i++){
            sb.append(stack.get(i));
        }

        if(stack.size()==0){
            System.out.println("FRULA");
        }else {
            System.out.println(sb);
        }

    }
}
