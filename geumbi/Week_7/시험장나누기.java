import java.util.*;

class Solution {
    int  root, groupCount;
    int[] parent, left, right, people;
    
    public int solution(int k, int[] num, int[][] links) {
   	
       	parent = new int[num.length];
        left = new int[num.length];
        right = new int[num.length];
       	people = num;
        
        Arrays.fill(parent, -1);
        
        for(int i = 0; i < links.length; i++) {
            left[i] = links[i][0];
            right[i] = links[i][1];
            
            if(left[i] != -1){
                parent[left[i]] = i;
            }
           		
            if(right[i] != -1){
                parent[right[i]] = i;
            }
            	
        }
    	
       	for(int i = 0; i < num.length; i++)
            if(parent[i] == -1) {
                root = i;
                break;
            }
        
        int lo = 0, hi = (int) 1e9;
        for(int i = 0; i < num.length; i++)
            lo = Math.max(lo, people[i]);
        
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            if(getGroupCount(mid) <= k) {
              	hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        
        return hi + 1;
    }
    
    public int getGroupCount(int target) {
       	groupCount = 0; 
        dfs(root, target);
        return groupCount + 1;
    }
    
    public int dfs(int curr, int target) {
        int lv = 0, rv = 0;
        if(left[curr] != -1)
            lv = dfs(left[curr], target);
        if(right[curr] != -1)
            rv = dfs(right[curr], target);
        
        if(people[curr] + lv + rv <= target)
            return people[curr] + lv + rv;
        
        if(people[curr] + Math.min(lv, rv) <= target) {
            groupCount++;
            return people[curr] + Math.min(lv, rv);
        }
       	
        groupCount += 2;
        return people[curr];
    }
}
