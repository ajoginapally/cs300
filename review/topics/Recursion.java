public class Recursion {
    public static int factorial(int n){
        if (n<=1) return 1;
        return n * factorial(n-1);
    }

    public static int fib(int n){
        if (n<=1) return n;
        return fib(n-1) + fib(n-2);
    }

    public static int fibDP(int n){
        if (n<=1) return n;
        int[] dp=new int[n+1]; dp[0]=0; dp[1]=1;
        for(int i=2;i<=n;i++) dp[i]=dp[i-1]+dp[i-2];
        return dp[n];
    }

    public static void main(String[] args){
        System.out.println("5!=" + factorial(5));
        System.out.println("fib(10) naive=" + fib(10));
        System.out.println("fib(50) DP=" + fibDP(50));
    }
}