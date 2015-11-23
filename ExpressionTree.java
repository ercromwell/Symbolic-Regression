

// Representation of mathematical expression in binary tree form
public class ExpressionTree
{
    //Node in expression tree. Represents either integer, binary operation, 
    //unary operation, or variable

    //if variable node, value = which variable it represents
    private class Node
    {
	public int value;
	public Node left;
	public Node right;
	public boolean variable;

	//integer node
	public Node(int v)
	{
	    this(v, null, null, false);
	}

	public Node(int v, Node l, Node r, boolean b)
	{
	    this.value = v;
	    this.left = l;
	    this.right = r;
	    this.variable = b;
	}

	//integers exist only as leaf nodes
	public boolean isInteger()
	{
	    return left == null && right == null;
	}

	public boolean isBinaryOp()
	{
	    return left!=null && right != null;
	}

	public boolean isUnaryOp()
	{
	    return left!= null && right == null;
	}
    }


    //Variables
    private Node root;

    //Evaluation function with given input array for variables
    public double evaluate(double[] input)
    {
    	if(root == null)
    	{
    	    System.out.println("empty expression tree");
    	    //Throw exception here
    	}
	
    	return evalHelper(root, input);
    }

    //Post-order traversal of expression tree
    private double evalHelper(Node n, double[] input)
    {
    	// variable
    	if(n.variable)
    	    return input[n.value];

    	else if( n.isInteger() )
	    return n.value;

    	//Binary operation
    	else if(n.isBinaryOp() )
    	{
	    double leftVal = evalHelper(n.left, input);
	    double rightVal = evalHelper(n.right, input);

	    return binaryOp(n.value, leftVal, rightVal);
    	}
    	//Unary operation
    	else
	{
	    double leftVal = evalHelper(n.left, input);
	    return unaryOp(n.value, leftVal); 
        }
    }

    //Need to look back at division, power, just poison the tree?
    private double binaryOp(int op, double left, double right)
    {

	double result = 0;
	
	switch (op)
	{
	    //addition
	    case 0: result = left + right;
		break;
	    
	     //subtraction
	     case 1: result = left - right;
		break;

	     //multiplication
	     case 2: result = left * right;
		break;

	     //division (rejects division by 0 ) or remove such trees before hand
	     case 3:
		 //divide by 0
		 if( right == 0) //or some better evaluator of equality for double, or some hack
		     result = Integer.MAX_VALUE;
		 else
		     result = left / right;
		 break;
	    //power
	    case 4:
		//raise negative number to fraction
		if( left < 0 && ( right % 1 ) < 1 )


	        // raise 0 to negative power
		else if (left == 0 && right < 0 )


		else if

		else
		    result = Math.pow(left, right);
		break;
	    //Invalid operator, throw exception
	default: System.out.println("invalid operator");
		break;

	}

	return result;
    }

    private double unaryOp(int op, double val)
    {
      	double result = 0;
	
	switch (op)
	{
	    //cosine
	case 0: result = Math.cos(val);
		break;
	    
	     //sine
	case 1: result = Math.sin(val);
		break;

	     //tangent
	case 2: result = Math.tan(val);
		break;
	    //Invalid operator, throw exception
	default: System.out.println("invalid operator");
		break;

	}

	return result;
    }


}
