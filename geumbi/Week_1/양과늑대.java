import java.util.ArrayList;
import java.util.List;

class Solution {
    static int [] Info;
    static ArrayList<Integer> [] childNode ;
    static int max=0;
    public int solution(int[] info, int[][] edges) {
        Info = info;

        childNode = new ArrayList[info.length];

        for (int i = 0 ; i < edges.length;i++){
            int v1 = edges[i][0];
            int v2 = edges[i][1];

            if(childNode[v1]==null){
                childNode[v1] = new ArrayList<Integer>();
            }
            childNode[v1].add(v2);

        }
        List <Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0,0,0,list);

        return max;
    }

    public static void dfs (int v,int sheep ,int wolf, List<Integer>nextNode){
        if(Info[v]==0) sheep++;
        else wolf++;

        if(wolf>=sheep)return;
        max=Math.max(sheep,max);


        List<Integer> list  = new ArrayList<>();
        list.addAll(nextNode);
        list.remove(Integer.valueOf(v));

        if(childNode[v]!=null){
            for(int i = 0 ; i <childNode[v].size();i++){
                list.add(childNode[v].get(i));
            }
        }

        for (int i =0; i< list.size();i++){
            dfs(list.get(i),sheep,wolf,list);
        }
    }

}