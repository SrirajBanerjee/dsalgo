import java.lang.*;
import java.io.*;
import java.util.*;

public class Trie
{
    private static class Input
    {
        public ArrayList<String> strArr = new ArrayList<String>();
        public String searchStr = null;
    }

    private static class Node
    {
        public char c = 0;
        public ArrayList<Node> children = new ArrayList<Node>();
    }

    private static ArrayList<Input> data = null;

    public static void main(String args[])
    {
        readData();
        ArrayList<Integer> res = solve();
        for(int i = 0; i < res.size(); ++i)
        {
            System.out.println(res.get(i));
        }
    }

    private static ArrayList<Integer> solve()
    {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i < data.size(); ++i)
        {
            Input inp = data.get(i);
            Node head = constructTrie(inp.strArr);
            Integer found = searchTrie(head, inp.searchStr);
            res.add(found);
        }
        return res;
    }

    private static Integer searchTrie(Node head, String s)
    {
        s = s + "$";
        char[] charArr = s.toCharArray();
        Node curNode = head;
        for(int j = 0; j < charArr.length; ++j)
        {
            Node n = null;
            for(int k = 0; k < curNode.children.size(); ++k)
            {
                if(curNode.children.get(k).c == charArr[j])
                {
                    n = curNode.children.get(k);
                    break;
                }
            }
            if(n == null)
            {
                return 0;
            }
            curNode = n;
        }
        return 1;
    }

    private static Node constructTrie(ArrayList<String> strArr)
    {
        Node head = new Node();
        head.c = '$';
        for(int i = 0; i < strArr.size(); ++i)
        {
            Node curNode = head;
            String s = strArr.get(i) + "$";
            char[] charArr = s.toCharArray();
            for(int j = 0; j < charArr.length; ++j)
            {
                Node n = null;
                for(int k = 0; k < curNode.children.size(); ++k)
                {
                    if(curNode.children.get(k).c == charArr[j])
                    {
                        n = curNode.children.get(k);
                        break;
                    }
                }
                if(n == null)
                {
                    n = new Node();
                    n.c = charArr[j];
                    // System.out.println(n.c);
                    curNode.children.add(n);
                }
                curNode = n;
            }
        }
        return head;
    }

    private static void readData()
    {
        data = new ArrayList<Input>();
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            if(line == null)
                return;
            Integer t = Integer.valueOf(line);
            for(int i = 0; i < t; ++i)
            {
                line = reader.readLine();
                Integer numStr = Integer.valueOf(line);
                Input inp = new Input();
                line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line);
                for(int j = 0; j < numStr; ++j)
                {
                    inp.strArr.add(tokenizer.nextToken());
                }
                line = reader.readLine();
                inp.searchStr = line;
                data.add(inp);
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception occurred");
        }
    }
}