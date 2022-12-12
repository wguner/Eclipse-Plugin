package NumOfChecks;

import com.puppycrawl.tools.checkstyle.api.*;

public class ExpressionCheck extends AbstractCheck {

  public int count = 0;
  
  @Override
  public int[] getAcceptableTokens() {
	    return getRequiredTokens();
	    
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[] { TokenTypes.EXPR };
  }

  @Override
  public int[] getDefaultTokens() {
	    return getAcceptableTokens();
  }


  @Override
  public void beginTree(DetailAST rootAST)
  {
    count = 0;
  }

  @Override
  public void finishTree(DetailAST rootAST)
  {
	  log(rootAST.getLineNo(),"Number of expressions = " + count);
	  //count = 0;
  }
  
  @Override
  public void visitToken(DetailAST ast) {
	  if(ast.getType() == TokenTypes.EXPR)
	  {
		  // count expressions
		  count++;	  
	  }
  }
  
}