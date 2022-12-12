package NumOfChecks;

import com.puppycrawl.tools.checkstyle.api.*;

public class LineCommentCheck extends AbstractCheck {

  public int count = 0;

  @Override
  public boolean isCommentNodesRequired()
  {
    return true;
  }
  
  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.SINGLE_LINE_COMMENT };
  }

  @Override
  public int[] getRequiredTokens() {
    return getAcceptableTokens();
  }

  @Override
  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.SINGLE_LINE_COMMENT };
  }


  @Override
  public void beginTree(DetailAST rootAST)
  {
    count = 0;
  }

  @Override
  public void finishTree(DetailAST rootAST)
  {
	  log(rootAST.getLineNo(),"Number of single line comments = " + count);
	  //count = 0;
  }
  
  @Override
  public void visitToken(DetailAST ast) {
	  count++;
  }

}