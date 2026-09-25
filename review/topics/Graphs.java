import java.util.*;

public class Graphs {
    public static void dfs(List<List<Integer>> adj, int start){
        boolean[] vis = new boolean[adj.size()];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while(!stack.isEmpty()){
            int u = stack.pop();
            if(vis[u]) continue;
            vis[u]=true; System.out.print(u + " ");
            for(int v: adj.get(u)) if(!vis[v]) stack.push(v);
        }
        System.out.println();
    }

    public static void main(String[] args){
        int n=5;
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(0).add(2);
        adj.get(1).add(3);
        adj.get(2).add(4);
        System.out.print("DFS: "); dfs(adj,0);
    }
}