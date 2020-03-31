import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class PhoneBook {

    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    //private List<Contact> contacts = new ArrayList<>();
    private ArrayList<Contact>[] contactsHash = null;
    Integer a,b,p,m;

    public static void main(String[] args) {
        new PhoneBook().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        System.out.println(response);
    }


    private void processQuery(Query query) {
        if (query.type.equals("add")) {
            // if we already have contact with such number,
            // we should rewrite contact's name
        	int hashVal = getHashVal(query.number);
        	ArrayList<Contact> l = contactsHash[hashVal];
            boolean wasFound = false;
            if(l == null) {
            	l = new ArrayList<Contact>();
            	contactsHash[hashVal] = l;
            }
            else {
                for (Contact contact : l) {
                    if (contact.number == query.number) {
                        contact.name = query.name;
                        wasFound = true;
                        break;
                    }
                }
            }
            // otherwise, just add it
            if (!wasFound)
                l.add(new Contact(query.name, query.number));
        } else if (query.type.equals("del")) {
        	int hashVal = getHashVal(query.number);
        	ArrayList<Contact> l = contactsHash[hashVal];
            if(l != null && !l.isEmpty()) {
                for (Iterator<Contact> it = l.iterator(); it.hasNext(); ) {
                    if (it.next().number == query.number) {
                        it.remove();
                        break;
                    }
                }
            }
        } else {
            String response = "not found";
            int hashVal = getHashVal(query.number);
        	ArrayList<Contact> l = contactsHash[hashVal];
        	if(l != null && !l.isEmpty()) {
		        for (Contact contact: l) {
		            if (contact.number == query.number) {
		                response = contact.name;
		                break;
		            }
		        }
        	}
            writeResponse(response);
        }
    }
    
    private int getHashVal(int x) {
    	Long val = a.longValue()*x + b.longValue();
    	val = val%p;
    	val = val%m;
    	return val.intValue();
    }

    public void processQueries() {
        int queryCount = in.nextInt();
        m = 2*queryCount;
        contactsHash = new ArrayList[m];
        BigInteger bi = new BigInteger(m.toString());
        p = Integer.parseInt(bi.nextProbablePrime().toString());
        Random rand = new Random();
        a = rand.nextInt(p-2) + 1;
        b = rand.nextInt(p-1);
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery());
    }

    static class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
