import java.util.*;

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
    public ExpressionTree()
    {
	root = new Node(0);
    }

    //Build random mathematical expression, depth 3 or 4
    //choose random value for node, perhaps choice based on current tree depth
    //if integer constant or variable, stop, base case

    //if binary operator, create new expression for left and right subtree depth -1

    //if unary operator, create new expressions for left subtree, depth -1;
    
    public ExpressionTree(int maxDepth)
    {
	root = new Node(0);
    }

    
    //Build tree based on list of operations, post-order
    //E.g, 9 + 7*a - 6 = 9 7 a * + 6 - 
    public ExpressionTree(String list) 
    {
	try
	{
	    root = listHelper(list);
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	    System.exit(-1);
        }
    }

    //Make thrown exception more descriptive
    private Node listHelper(String list) throws Exception
    {
	String[] postOrder = list.split(" ");
	
	Deque<Node> stack = new LinkedList<Node>();

	for(int i = 0; i < postOrder.length; ++i)
	{
	    String symbol = postOrder[i];
	    Node n;

	    //binary operator
	    if( isBinaryOp(symbol)  )
	    {
		Node right;
		if( stack.peek() == null )
		    throw new IllegalArgumentException("Invalid post-order list: "
						       + list);
		right = stack.pop();
		
		Node left;
	      	if( stack.peek() == null )
		    throw new IllegalArgumentException("Invalid post-order list: "
						       + list);
		left = stack.pop();
		n = new Node(getBinaryOp(symbol), left, right);
	    }
	    //unary operator
	    else if( isUnaryOp(symbol) )
	    {
		Node child;
	       	if( stack.peek() == null )
		    throw new IllegalArgumentException("Invalid post-order list: "
					+ list);
	        child = stack.pop();
		n = new Node(getUnaryOp(symbol), child);
	    }
	    //variable
	    else if( isVariable(symbol) )
	    {
		int var = (int)symbol.charAt(0) - 97;
		n = new Node(var, true);
	    }
	    //integer constant
	    else
		n = new Node( Integer.parseInt(symbol) );
	    
	    stack.push(n);
        }

	//if stack is not empty, throw exception
	if( stack.peek() == null )
	    throw new IllegalArgumentException("Invalid post-order list: "
					       + list );	
	return stack.pop();
    }

    //Whether str is a binary operator symbol
    private static boolean isBinaryOp(String str)
    {
	return str.equals("+") || str.equals("-") || str.equals("*")
	    || str.equals("/") || str.equals("^") ;
    }

    //Whethere str is a unary operator symbol
    private static  boolean isUnaryOp(String str)
    {
	return str.equals("cos") || str.equals("sin") || str.equals("tan");
    }

    //Whethere str is a variable symbol ("a"-"z")
    private static  boolean isVariable(String str)
    {
	return str.compareTo("a") >= 0 && str.compareTo("z") <= 0 ;
    }


     //Analogous integer for given binary operator "op"
    private static int getBinaryOp(String op)
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

    //Analogous integer for given unary operator "op"
    private static int getUnaryOp(String op)
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
    //If illegal operation, returns Double.MAX_VALUE
    //Need to deal w/inproper input
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
    //If illegal operation, returns Double.MAX_VALUE
    private double evalHelper(Node n, double[] input) 
    {
    	// variable
    	if(n.isVariable())
        {
    	    return input[n.value];
	}
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

    //Perform binary operation 'left' op 'right
    //For illegal operations, return Double.MAX_VALUE
    private double binaryOp(int op, double left, double right)
    {
	double result;
	
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
		if( left < 0 &&  (right % 1) > 0 && ( right % 1 ) < 1 )
		     result = Double.MAX_VALUE;
	        // raise 0 to negative power, equivalent to divide by 0
		else if (left == 0 && right < 0 )
		     result = Double.MAX_VALUE;
		else
		    result = Math.pow(left, right);
		break;
	    //Invalid operator, throw exception
	    default: System.out.println("invalid operator");
		result = Double.MAX_VALUE;
		break;
	}
	return result;
    }

    private double unaryOp(int op, double val)
    {
      	double result = Double.MAX_VALUE;
	
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

    //TO INCUDE: floating point equivalnce for 0 using rel/abs error
    public boolean isZero(double d)
    {
	return true;
    }

    public String toString()
    {
	if(root == null)
	    return "";
	
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
	return child.isBinaryOp() && child.value < parent.value;
    }


}
