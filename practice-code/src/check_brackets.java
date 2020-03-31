import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
            	Bracket b = new Bracket(next, position);
            	opening_brackets_stack.push(b);
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
            	if(opening_brackets_stack.empty()) {
            		System.out.print(position+1);
            		return;
            	}
            	Bracket b = opening_brackets_stack.pop();
            	if(!b.Match(next)) {
            		System.out.print(position+1);
            		return;
            	}
            }
        }

        // Printing answer, write your code here
        if(opening_brackets_stack.isEmpty()) {
        	System.out.print("Success");
        }
        else {
        	System.out.print(opening_brackets_stack.pop().position+1);
        }
    }
}
