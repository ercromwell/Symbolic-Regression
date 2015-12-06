

public class GeneticAlgorithm
{

    //Generate initial populaiton of random expressions
    public static Vector<ExpressionTree> randomPopulation(int popSize, int vars,
							  int depth)
    {
	Vector<ExpressionTree> population = new Vector<ExpressionTree>(popSize);

	for(int i = 0; i < popSize; ++i)
	{
	    population.add( new ExpressionTree(depth , vars) );
        }

       	return population;
    }

    //Least square error of corressponding expressions
    //input[x] = [a b c ... z], output[x] = f(a,b,c,...z)
    public static double[] fitnessScore(Vector<ExpressionTree> population,
					double[][] input, double[] output)
    {
	double[] fitness = new double[ population.size() ];

	for(int t = 0; t < population.size(); ++t)
	{
	    double sum = 0;
	    ExpressionTree tree = population.get(t) ;

	    for(int x = 0; x < input.length; ++x)
	    {
		double error = tree.evaluate(input[x]) - output[x];
		sum+= error*error;
	    }
	    

	    fitness[t] = sum;
        }

	return fitness
    }

    public static int existsSolution(double[] fitness, double epsilon)
    {
	int solution = -1;

	for(int i = 0; i < fitness.length; ++i)
	{
	    if( fitness[i] < epsilon)
       	    {
		solution = i;
		break;
	    }
        }
	    
	return solution
    }

    //post: Crossover performed on expressions one and two
    public static void crossover(ExpressionTree one, ExpressionTree two)
    {
	//point of crossover for expression one


	//point of crossover for expression two
	
    }

     

    
	 

    
}
