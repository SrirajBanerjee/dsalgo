import java.io.*;
import java.lang.*;
import java.util.*;

public class InputWays
{
    public static void main(String args[])
    {
        // String line = scannerLine();
        // System.out.println("line = " + line);

        Integer n = scannerInt();
        System.out.println("n = " + n);
        // ArrayList<String> strArr = scannerStrArray(n);
        // System.out.println("strArr = " + strArr.toString());

        ArrayList<Integer> intArr = scannerIntArray(n);
        System.out.println("intArr = " + intArr.toString());
    }

    private static String scannerLine()
    {
        Scanner s = new Scanner(System.in);
        String line = s.nextLine();
        return line;
    }

    private static ArrayList<String> scannerStrArray(Integer n)
    {
        ArrayList<String> retval = new ArrayList<String>();
        Scanner s = new Scanner(System.in);
        for(int i = 0; i < n; ++i)
        {
            retval.add(s.next());
        }
        return retval;
    }

    private static Integer scannerInt()
    {
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }

    private static ArrayList<Integer> scannerIntArray(Integer n)
    {
        ArrayList<Integer> retval = new ArrayList<Integer>();
        Scanner s = new Scanner(System.in);
        for(int i = 0; i < n; ++i)
        {
            retval.add(s.nextInt());
        }
        return retval;
    }

}