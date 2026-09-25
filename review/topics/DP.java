public class DP {
    public static int knapSack0_1(int[] wt, int[] val, int W){
        int n = wt.length;
        int[] dp = new int[W+1];
        for(int i=0;i<n;i++){
            for(int w=W; w>=wt[i]; w--){
                dp[w] = Math.max(dp[w], val[i] + dp[w-wt[i]]);
            }
        }
        return dp[W];
    }

    public static void main(String[] args){
        int[] wt = {1,3,4};
        int[] val = {15,50,60};
        System.out.println("knap 5 -> " + knapSack0_1(wt,val,5));
    }
}