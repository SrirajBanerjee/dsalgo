    private static Boolean checkPossib(Integer[][]mat, LinkedList<PosNode> allPossib, HashMap<String, Integer> tempChoices)
    {
        Boolean violation = true;
        if(allPossib.size() > 0)
        {
            PosNode pn = allPossib.pollLast();
            HashSet<Integer> possib = pn.possib;
            Iterator setItr = possib.iterator(); 
            while(setItr.hasNext())
            {
                Integer val = (Integer)setItr.next();
                tempChoices.put(pn.key, val);
                violation = checkPossib(mat, allPossib, tempChoices);
                if(!violation)
                {
                    return violation;
                }
                tempChoices.remove(pn.key);
            }
            allPossib.add(pn);
        }
        else
        {
            // If any violation is found, return true
            // Iterate through all the tempChoices and check for violation
            Iterator mapItr = tempChoices.entrySet().iterator();
            System.out.println("choices = " + tempChoices.toString());
            System.out.println("attempt = " + (attempt++) + " totalComb = " + totalComb);
            while(mapItr.hasNext())
            {
                Map.Entry mapElement = (Map.Entry)mapItr.next();
                Integer[] rc = extractValFromKey((String)mapElement.getKey());
                Integer row = rc[0];
                Integer col = rc[1];
                violation = checkRowViolation(mat, tempChoices, row);
                if(violation)
                    return true;
                violation = checkColViolation(mat, tempChoices, col);
                if(violation)
                    return true;
                violation = checkGridViolation(mat, tempChoices, row, col);
                if(violation)
                    return true;
            }
            return false;
        }
        return violation;
    }

    private static Boolean checkRowViolation(Integer[][] mat, HashMap<String, Integer> tempChoices, Integer row)
    {
        HashSet<Integer> allNum = new HashSet<Integer>();
        for(int i = 0; i < mat[row].length; ++i)
        {
            Integer num = mat[row][i];
            if(num == 0)
            {
                num = tempChoices.get((getKey(row,i)));
            }
            allNum.add(num);
        }
        if(allNum.size() != 9)
        {
            return true;
        }
        return false;
    }

    private static Boolean checkColViolation(Integer[][] mat, HashMap<String, Integer> tempChoices, Integer col)
    {
        HashSet<Integer> allNum = new HashSet<Integer>();
        for(int i = 0; i < mat.length; ++i)
        {
            Integer num = mat[i][col];
            if(num == 0)
            {
                num = tempChoices.get((getKey(i,col)));
            }
            allNum.add(num);
        }
        if(allNum.size() != 9)
        {
            return true;
        }
        return false;
    }

    private static Boolean checkGridViolation(Integer[][] mat, HashMap<String, Integer> tempChoices, Integer row, Integer col)
    {
        HashSet<Integer> allNum = new HashSet<Integer>();
        Integer lowRow = (int) ((Math.floor(row/3)) * 3);
        Integer highRow = lowRow+2;
        Integer lowCol = (int) ((Math.floor(col/3)) * 3);
        Integer highCol = lowCol+2;
        for(int i = lowRow; i <= highRow; ++i)
        {
            for(int j = lowCol; j < highCol; ++j)
            {
                Integer num = mat[i][j];
                if(num == 0)
                {
                    num = tempChoices.get((getKey(i,j)));
                }
            }
        }
        if(allNum.size() != 9)
        {
            return true;
        }
        return false;
    }

    // Simple Fill
    private static Boolean simpleFill(Integer[][] mat, LinkedList<PosNode> allPossib) throws Exception
    {
        Boolean changed = false;
        Iterator itr = allPossib.iterator();
        LinkedList<PosNode> newAllPossib = new LinkedList<PosNode>();
        while(itr.hasNext())
        {
            PosNode pn = (PosNode)itr.next();
            Integer[] vals = extractValFromKey(pn.key);
            Integer i = vals[0];
            Integer j = vals[1];
            System.out.println("Initially key = " + i + "," + j + " possib = " + pn.possib.toString());
            if(mat[i][j] == 0)
            {
                HashSet<Integer> possib = pn.possib;
                removeRowOptions(mat, possib, i);
                removeColOptions(mat, possib, j);
                removeGridOptions(mat, possib, i, j);
                if(possib.size() == 0)
                {
                    throw new Exception("No possible entry");
                }
                else if(possib.size() == 1)
                {
                    mat[i][j] = possib.iterator().next();
                    allPossib.remove(getKey(i,j));
                    changed = true;
                }
                else
                {
                    newAllPossib.add(pn);
                    totalComb = totalComb*pn.possib.size();
                    System.out.println("key = " + i + "," + j + " possib = " + possib.toString());
                }
            }
        }
        allPossib = newAllPossib;
        return changed;
    }

    private static void removeRowOptions(Integer[][] mat, HashSet<Integer> possib, Integer row)
    {
        for(int i = 0; i < mat[row].length; ++i)
        {
            possib.remove(mat[row][i]);
        }
    }

    private static void removeColOptions(Integer[][] mat, HashSet<Integer> possib, Integer col)
    {
        for(int i = 0; i < mat.length; ++i)
        {
            possib.remove(mat[i][col]);
        }
    }

    private static void removeGridOptions(Integer[][] mat, HashSet<Integer> possib, Integer row, Integer col)
    {
        Integer lowRow = (int) ((Math.floor(row/3)) * 3);
        Integer highRow = lowRow+2;
        Integer lowCol = (int) ((Math.floor(col/3)) * 3);
        Integer highCol = lowCol+2;
        for(int i = lowRow; i <= highRow; ++i)
        {
            for(int j = lowCol; j < highCol; ++j)
            {
                possib.remove(mat[i][j]);
            }
        }
    }

    private static String getKey(Integer row, Integer col)
    {
        return row.toString() + "_" + col.toString();
    }

    private static Integer[] extractValFromKey(String str)
    {
        String[] arr = str.split("_", 2);
        Integer row = Integer.valueOf(arr[0]);
        Integer col = Integer.valueOf(arr[1]);
        return new Integer[]{row,col};
    }

    private static void initAllPossib(Integer[][] mat, LinkedList<PosNode> allPossib)
    {
        for(int i = 0; i < mat.length; ++i)
        {
            for(int j = 0; j < mat[0].length; ++j)
            {
                if(mat[i][j] == 0)
                {
                    HashSet<Integer> possib = new HashSet<Integer>();
                    for(int n = 1; n <= 9; ++n)
                    {
                        possib.add(n);
                    }
                    PosNode pn = new PosNode();
                    pn.key = getKey(i,j);
                    pn.possib = possib;
                    allPossib.add(pn);
                }
            }
        }
    }
