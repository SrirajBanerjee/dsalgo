import java.io.*;
import java.lang.*;
import java.util.*;

public class MinimumSumPartition
{
    private static ArrayList<ArrayList<Integer>> data;

    public static void main(String args[])
    {
        readInput();
        ArrayList<Integer> result = solve();
        for(int i = 0; i < result.size(); ++i)
        {
            System.out.println(result.get(i));
        }
    }

    private static Integer msp(ArrayList<Integer> intArr)
    {
        ArrayList<Integer> arrCopy = new ArrayList<Integer>();
        Iterator<Integer> iterator = intArr.iterator();
        while(iterator.hasNext())
        {
            arrCopy.add( new Integer(iterator.next()) );  
        }
        Collections.sort(arrCopy, Collections.reverseOrder());
        //System.out.println(arrCopy.toString());

        TreeSet<Integer> bst = new TreeSet<Integer>();

        for(int i = 0; i < arrCopy.size(); ++i)
        {
            Integer num = arrCopy.get(i);
            if(bst.size() == 0)
            {
                bst.add(num);
            }
            else
            {
                TreeSet<Integer> newBst = new TreeSet<Integer>();
                while(bst.size() > 0)
                {
                    Integer el = bst.pollFirst();
                    newBst.add(Math.abs(el - num));
                    if(el < num*(arrCopy.size()-i))
                    {
                        newBst.add(el + num);
                    }
                }
                bst = newBst;
            }
        }
        return bst.pollFirst();
    }

    private static ArrayList<Integer> solve()
    {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < data.size(); ++i)
        {
            Integer val = msp(data.get(i)); 
            result.add(val);
        }
        return result;
    }

    private static void readInput()
    {
        data = new ArrayList<ArrayList<Integer>>();
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int i = 0; i < t; ++i)
        {
            int n = s.nextInt();
            ArrayList<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j < n; ++j)
            {
                row.add(s.nextInt());
            }
            data.add(row);
        }
    }
}
