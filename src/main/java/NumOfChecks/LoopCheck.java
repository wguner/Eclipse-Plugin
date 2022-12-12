package NumOfChecks;

import com.puppycrawl.tools.checkstyle.api.*;

public class LoopCheck extends AbstractCheck {

  public int count = 0;

  public int[] tokens = new int[] { TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_FOR };
  @Override
  public boolean isCommentNodesRequired()
  {
    return true;
  }
  
  @Override
  public int[] getAcceptableTokens() {
    return tokens;
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[] { TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_FOR };
  }

  @Override
  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_FOR };
  }


  @Override
  public void beginTree(DetailAST rootAST)
  {
    count = 0;
  }

  @Override
  public void finishTree(DetailAST rootAST)
  {
	  log(rootAST.getLineNo(),"Number of loops = " + count);
	  count = 0;
  }
  
  @Override
  public void visitToken(DetailAST ast) {
	  count++;
  }
}