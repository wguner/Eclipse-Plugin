package NumOfChecks;

import com.puppycrawl.tools.checkstyle.api.*;
import java.util.*;

public class OperandCheck extends AbstractCheck {

  public int count = 0;
  private int[] operands = {
			TokenTypes.CHAR_LITERAL, 	TokenTypes.LITERAL_BOOLEAN, 	TokenTypes.LITERAL_BYTE,
			TokenTypes.LITERAL_CHAR, 	TokenTypes.LITERAL_INT,  		TokenTypes.LITERAL_LONG,
			TokenTypes.LITERAL_SHORT,	TokenTypes.LITERAL_VOID,	 	TokenTypes.STRING_LITERAL, 
			TokenTypes.NUM_DOUBLE,		TokenTypes.NUM_FLOAT, 			TokenTypes.NUM_INT,
			TokenTypes.NUM_LONG
	};

 
  @Override
  public int[] getAcceptableTokens() {
	  return getDefaultTokens();
  }

  @Override
  public int[] getRequiredTokens() {
    return getDefaultTokens();
  }

  @Override
  public int[] getDefaultTokens() {
	    return operands;
  }


  @Override
  public void beginTree(DetailAST rootAST)
  {
    count = 0;
  }

  @Override
  public void finishTree(DetailAST rootAST)
  {
	  log(rootAST.getLineNo(),"Number of operands = " + count);
  }
  
  
  @Override
  public void visitToken(DetailAST ast) {
	  count++;
  }

}