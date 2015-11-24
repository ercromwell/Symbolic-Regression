

// Representation of mathematical expression in binary tree form
public class ExpressionTree
{
    /*Node in expression tree. Represents either integer, binary operation, 
      unary operation, or variable

      Binary operators: 0 = addition, 1 = subtraction, 2 = multuplication,
      3 = division, 4 = power

      Unary operators: 0 = cosine, 1 = sine, 2 = tangent

      if variable node, value = which variable it represents
      OR add dummy right child, need to figure out which is more efficient
      or if makes no difference

    */

    
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

	//vairable node
	public Node(int v, boolean b)
	{
	    this(v, null, null, b);
	}

	//binary operator
	public Node(int op, Node left, Node right)
	{	       	 
	    this(op , left, right, false);
	}

       	//unary operator
	public Node(int op, Node left)
	{
	    this(op, left, null, false);
	}

	public Node(int v, Node l, Node r, boolean b)
	{
	    this.value = v;
	    this.left = l;
	    this.right = r;
	    this.variable = b;
	}

	public boolean isVariable()
	{
	    return variable;
	}
	
	public boolean isInteger()
	{
	    return !variable && left == null && right == null;
	}

	public boolean isBinaryOp()
	{
	    return left!=null && right != null;
	}

	public boolean isUnaryOp()
	{
	    return left!= null && right == null;
	}

	public String toString()
	{
	    String str ="";
	    	// variable
	    if( this.isVariable() )
		str =  String.valueOf( (char)(value + 97) );

		//integer constant
	    else if( this.isInteger() )
		str =  String.valueOf( value) ;

		//Binary operation
	    else if(this.isBinaryOp() )
	    {
		switch(value)
		{
		    //subtraction
		    case 0: str =  "-";
			break;
	    
		    //addition
		    case 1: str =  "+";
			break;

		    //division
		    case 2: str =  "/";
			break;

		    //mulitplication 
		    case 3:  str =  "*";
			break;
		 
		    //power
		    case 4:  str =  "^";
			break;
		     //Invalid operator, throw exception
		    default: System.out.println("invalid operator");
			break;
	        }
	    }
	    //Unary operation
	    else
	    {
		switch(value)
		{
		    //cosine
		    case 0: str =  "cos";
			break;
	    
		    //sine
		    case 1: str =  "sin";
			break;

		    //tangent
		    case 2: str =  "tan";
			break;

		     //Invalid operator, throw exception
		    default: System.out.println("invalid operator");
			break;
	        }
	    }

	    return str;
	}
    }

    //Variable
    private Node root;

    //Constructors

    //Build random mathematical expression, depth 3 or 4
    //choose random value for node, perhaps choice based on current tree depth
    //if integer constant or variable, stop, base case

    //if binary operator, create new expression for left and right subtree depth -1

    //if unary operator, create new expressions for left subtree, depth -1;


    
    //Build tree based on list of operations, post-order
    //E.g, 9 + 7*a - 6 = 9 7 a * + 6 - 
    public ExpressionTree(String postOrder)
    {
	String[] list = postOrder.split(" ");

	root = listHelper(list);
    }

    //
    private Node listHelper(String[] postOrder)
    {

	Deque<Node> stack = new LinkedList<Node>();

	for(int i = 0; i < postOrder.size(); ++i)
	{
	   
	    //if binary operator
	    if( postOrder[i]  )
	    {
		//pop of first two nodes from stack, construct new node w/ approp value
		//two nodes as its children
		//add node to stack

		Node right = stack.pop();
		Node left = stack.pop();

		//apropriate binary operator
		    
		
	    }
	    //unary operator
	    else if( )
	    {
		//pop of node from stack , make it left child of new node
		//add node to stack
		Node child = stack.pop();
	    }
	    //variable
	    else if( )
	    {
		//make new node, add to stack
	    }
	    //integer constant
	    else
		stack.push( new Node( Integer.parseInt( postOrder[i] ) ) );
	    
	    
        }

	//if stack is not empty, throw exception
    }

    private static int binaryOperator(String op)
    {
         int v = 0;
	 if( op.equals("-") )
	      v = 0;
	 else if( op.equals("+") )
	     v = 1;
	 else if( op.equals("/") )
	     v = 2;
	 else if( op.equals("*") )
	     v = 3;
	 else if( op.equals("^") )
	     v = 4;
	 else	 
	     v = -1;

	 return v;
    }

    private static int unaryOperator(String op)
    {
         int v = 0;
	 if( op.equals("cos") )
	      v = 0;
	 else if( op.equals("sin") )
	     v = 1;
	 else if( op.equals("tan") )
	     v = 2;
	 else	 
	     v = -1;

	 return v;
    }
       
    
    
    //Evaluation function with given input array for variables
    public double evaluate(double[] input)
    {
    	if(root == null)
    	{
    	    System.out.println("empty expression tree");
    	    //Throw exception here
	    return Double.MAX_VALUE;
    	}
	
    	return evalHelper(root, input);
    }

    //Post-order evaluation of expression tree
    //If illegal operation, immediately terminate tree and returns Double.MAX_VALUE
    private double evalHelper(Node n, double[] input)
    {
    	// variable
    	if(n.isVariable())
    	    return input[n.value];

	//integer constant
    	else if( n.isInteger() )
	    return n.value;

    	//Binary operation
    	else if(n.isBinaryOp() )
    	{
	    double leftVal = evalHelper(n.left, input);

	    if(leftVal == Double.MAX_VALUE )
		return leftVal;
	    
	    double rightVal = evalHelper(n.right, input);

	    if(rightVal == Double.MAX_VALUE )
		return rightVal;

	    return binaryOp(n.value, leftVal, rightVal);
    	}
    	//Unary operation
    	else
	{
	    double leftVal = evalHelper(n.left, input);

	    if(leftVal == Double.MAX_VALUE )
		return leftVal;
	    
	    return unaryOp(n.value, leftVal); 
        }
    }

    //For illegal operations, return Double.MAX_VALUE
    //Include print statement for where error is?
    private double binaryOp(int op, double left, double right)
    {
	double result = 0;
	
	switch (op)
	{
	    //subtraction
	    case 0: result = left - right;
		break;
	    
	     //addition
	     case 1: result = left + right;
		break;

	     //division
	     case 2:
		 //divide by 0
		 if( right == 0) 
		     result = Double.MAX_VALUE;
		 else
		     result = left / right;
		break;

	     //Mulitplication 
	     case 3: result = left * right;
		 break;
		 
	    //power
	    case 4:
		//raise negative number to fractional value
		if( left < 0 && ( right % 1 ) < 1 )
		     result = Double.MAX_VALUE;
	        // raise 0 to negative power, equivalent to divide by 0
		else if (left == 0 && right < 0 )
		     result = Double.MAX_VALUE;
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

    public String toString()
    {
	StringBuilder sb = new StringBuilder();
	stringHelper(root, sb);
	return sb.toString();
    }

    //in-order traversal
    public void stringHelper(Node n, StringBuilder sb)
    {
       	// variable or integer constant
    	if(n.isVariable() || n.isInteger() )
	    sb.append( n.toString() );

	//Binary operation
    	else if(n.isBinaryOp() )
    	{
	    //if node has child w/ lower order operator than current node,
	    //add parenthesis
	    if( higherOrder(n, n.left) )
	    {
		sb.append( "(" );
		stringHelper(n.left, sb);
		sb.append( ")" );
	    }
	    else
		stringHelper(n.left, sb) ;

	    //Current node
	    sb.append(" ");
	    sb.append( n.toString() );
	    sb.append(" ") ;

	    //Right subtree
	    if( higherOrder(n, n.right) )
	    {
		sb.append( "(" );
		stringHelper(n.right, sb);
		sb.append( ")" );
	    }
	    else
		stringHelper(n.right, sb);

    	}
    	//Unary operation
    	else
	{
	    sb.append( n.toString());
	    sb.append( "( ");
	    stringHelper(n.left, sb);
	    sb.append( " )");
        }
    
    }

    //Whether parent node has higher order operator than child node
    private static boolean higherOrder(Node parent, Node child)
    {
	//child Node is operator node
	if( child.isBinaryOp() )
	    return child.value < parent.value;
	else
	    return false;
    }


}
