import java.io.*;
import java.lang.*;
import java.util.*;

public class sudoku
{
    private static Integer[][][] data;

    public static void main(String args[])
    {
        readInput();
        try
        {
            Integer [][][] result = solve();
            printOutput(result);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }

    private static Boolean solveSudoku(Integer[][] mat, Integer row, Integer col) throws Exception
    {
        if(row==9) return true;
        if(col==9) return solveSudoku(mat,row+1,0);
        if(mat[row][col] != 0) return solveSudoku(mat, row, col+1);
        for(int k = 1; k <= 9; ++k)
        {
            if(possible(mat, row, col, k))
            {
                mat[row][col] = k;
                if(solveSudoku(mat, row, col))
                    return true;
                mat[row][col] = 0;
            }
        }
        return false;
    }

    private static Boolean possible(Integer[][] mat, Integer row, Integer col, Integer val)
    {
        for(int i = 0; i < mat.length; ++i)
        {
            if(mat[row][i] == val || mat[i][col] == val)
                return false;
        }
        Integer lowRow = (int)( (Math.floor(row/3.0))*3);
        Integer lowCol = (int)( (Math.floor(col/3.0))*3);
        for(int i = lowRow; i < lowRow+3; ++i)
        {
            for(int j = lowCol; j < lowCol+3; ++j)
            {
                if(mat[i][j] == val)
                    return false;
            }
        }
        return true;
    }

    private static void printOutput(Integer[][][] res)
    {
        for(int i = 0; i < res.length; ++i)
        {
            printMat(res[i]);
        }
    }

    private static void printMat(Integer[][] mat)
    {
        for(int j = 0; j < mat.length; ++j)
        {
            for(int k = 0; k < mat[0].length; ++k)
            {
                if(k > 0)
                {
                    System.out.print(" ");
                }
                System.out.print(mat[j][k]);
            }
            System.out.println("");
        }
    }

    private static Integer[][][] solve()  throws Exception
    {
        Integer[][][] result = new Integer[data.length][9][9];
        copyData(result);
        //printOutput(result);
        for(int i = 0; i < result.length; ++i)
        {
            // Solve each sudoke one by one
            solveSudoku(result[i], 0, 0);
        }
        return result;
    }

    private static void copyData(Integer[][][] clone)
    {
        for(int i = 0; i < data.length; ++i)
        {
            for(int j = 0; j < 9; ++j)
            {
                for(int k = 0; k < 9; ++k)
                {
                    clone[i][j][k] = data[i][j][k];
                }
            }
        }
    }

    private static void readInput()
    {
        //Scanner s = new Scanner(System.in);
        String filePath = System.getProperty("user.dir") + File.separator + "sudokuInput.txt";
        File file = new File(filePath);
        try
        {
            Scanner s = new Scanner(file);
            int T = s.nextInt();
            data = new Integer[T][9][9];
            for(int i = 0; i < T; ++i)
            {
                for(int j = 0; j < 9; ++j)
                {
                    for(int k = 0; k < 9; ++k)
                    {
                        data[i][j][k] = s.nextInt();
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error reading input");
            data = null;
        }
    }
}