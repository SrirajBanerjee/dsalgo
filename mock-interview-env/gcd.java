import java.util.Scanner;

public class gcd 
{
    private static int gcd(int a, int b)
    {
        int big = a >= b ? a:b;
        int small = a < b ? a:b;
        int rem = big % small;
        if(rem == 0)
        {
            return small;
        }
        else
        {
            return gcd(small, rem);
        }
    }

    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        int b = s.nextInt();
        int val = gcd(a,b);
        System.out.println("gcd = " + val);
    }
}