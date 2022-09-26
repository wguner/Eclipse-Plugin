package HalsteadChecks;
import com.puppycrawl.tools.checkstyle.api.*;


public class HalsteadLengthCheck extends AbstractCheck {

	private int length = 0;
	
	@Override
	public int[] getDefaultTokens() {
		// getting all operator tokens 
		return new int[] { TokenTypes.EXPR, TokenTypes.NUM_INT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT,
	    		TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR, TokenTypes.MOD, 
	    		TokenTypes.LT, TokenTypes.GT, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.RPAREN, 
	    		TokenTypes.LPAREN, TokenTypes.EQUAL, TokenTypes.ASSIGN };
	}

	@Override
	public int[] getAcceptableTokens() {
		// getting all operator tokens 
		return new int[] { TokenTypes.EXPR, TokenTypes.NUM_INT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT,
	    		TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR, TokenTypes.MOD, 
	    		TokenTypes.LT, TokenTypes.GT, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.RPAREN, 
	    		TokenTypes.LPAREN, TokenTypes.EQUAL, TokenTypes.ASSIGN };
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	  @Override
	  public void beginTree(DetailAST rootAST)
	  {
		  length = 0;
	  }

	  @Override
	  public void finishTree(DetailAST rootAST)
	  {  
		  // output to console
		  log(rootAST.getLineNo(),"Halstead Length: " + length);
	    
		  // setting it back to zero, we are done
		  length = 0;
	  }
	  
	  @Override
	  public void visitToken(DetailAST ast) {
		  if(ast.getType() == TokenTypes.EXPR)
		  {	  
			  countOperatorsOperands(ast);
		  }
	  }
	  
	  //counting operators using DetailAST
	  private void countOperatorsOperands(DetailAST astToken)
	  { 
		  if (astToken.getFirstChild() != null) 
		  {
			  countOperatorsOperands(astToken.getFirstChild());
		  }

		  
		  if (astToken.getNextSibling() != null) 
		  {
			  countOperatorsOperands(astToken.getNextSibling());
		  }
		  
		  // token is operator and increment
		  checkIfOperatorOperand(astToken);
	  }
	  
	  // to check is token is operator or operand
	  private void checkIfOperatorOperand(DetailAST token)
	  {
		  int check = token.getType();
		  
		  // token is operator and increment
		  if(check == TokenTypes.PLUS || check == TokenTypes.MINUS || check == TokenTypes.DIV ||
				  check == TokenTypes.STAR || check == TokenTypes.MOD || check == TokenTypes.LT ||
				  check == TokenTypes.GT || check == TokenTypes.BAND || check == TokenTypes.BOR || 
				  check == TokenTypes.RPAREN || check == TokenTypes.LPAREN || check == TokenTypes.EQUAL ||
				  check == TokenTypes.ASSIGN)
		  {
			  length++;
		  } 	  
		  // token is operand and increment
		  else if (check == TokenTypes.NUM_DOUBLE || check == TokenTypes.NUM_INT || check == TokenTypes.NUM_FLOAT)
		  {
			  length++;
		  }
	  }
}
