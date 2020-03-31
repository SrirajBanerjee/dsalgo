import java.util.Scanner;

public class Fibonacci {
  private static long calc_fibSlow(int n) {
    if (n <= 1)
      return n;

    return calc_fibSlow(n - 1) + calc_fibSlow(n - 2);
  }
  
  private static long calc_fib(int n) {
    if (n <= 1)
      return n;
    long fn=1,fn1=0,fn2=0;
    for(int i=2; i<=n;++i) {
    	fn2 = fn1;
    	fn1 = fn;
    	fn = fn1 + fn2;
    }
    return fn;
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
