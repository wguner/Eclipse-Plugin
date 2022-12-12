package NumOfChecks;

import com.puppycrawl.tools.checkstyle.api.*;

public class CommentsCheck extends AbstractCheck {

  public int count = 0;

  @Override
  public boolean isCommentNodesRequired()
  {
    return true;
  }
  
  @Override
  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.COMMENT_CONTENT};
  }

  @Override
  public int[] getRequiredTokens() {
    return getAcceptableTokens();
  }

  @Override
  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.COMMENT_CONTENT};
  }


  @Override
  public void beginTree(DetailAST rootAST)
  {
    count = 0;
  }

  @Override
  public void finishTree(DetailAST rootAST)
  {
	  log(rootAST.getLineNo(),"Number of total comments = " + count);
	  //count = 0;
  }
  
  @Override
  public void visitToken(DetailAST ast) {
	count++;

  }

}