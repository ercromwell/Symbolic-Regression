import java.util.*;
import java.io.*;

public class SymReg
{
    public static final int POP_SIZE = 1000;
    public static final int NUM_VARS = 1;
    public static final int NUM_GENS = 50;
    public static final double EPSILON = 0.0000001;
    
    public static void main(String[] args)
    {
	//0: Import data, perhaps from argument of java file
	//file format: first line is X Y, where X = number of data points, Y = number of variables
	//data should be in form of a b c d .... z f(a,b,c,d,...,z)

	String dataFile = args[0];
	double[][] input;
	double[] output;

       	try
	{
	    BufferedReader br = new BufferedReader( new FileReader(dataFile) );

	    String info[] = br.readLine().split(" ");
	    int size = Integer.parseInt( info[0] );
	    int vars = Integer.parseInt( info[1] );
	    
	    input = new double[size][vars];
	    output = new double[size];

	    for(int i=0; i < size; ++i)
	    {
		String line[] = br.readLine().split(" ");

		for(int j = 0; j < vars; ++j)
		{
		    input[i][j] = Double.parseDouble( line[j] ) ;
	        }

		output[i] = Double.parseDouble( line[vars] );
	    }
	    
	    br.close();
        }
	catch(IOException e)
	{
	    e.printStackTrace();
        }
	//1: Generate initial random population of expressions

	//2:Calculate fitness of expressions
	//  fitness = sum of least squares error

	//3: if there exist funciton whose fitness is within acceptable bounds,
	//   return function

	//4: Crossover
	// Figure out method for creating next generation

	//repeat until generation limit
	
    }
    
}


	 
