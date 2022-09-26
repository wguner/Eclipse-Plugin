package NumOfChecks;

import com.puppycrawl.tools.checkstyle.api.*;

public class NumOfCommentsCheck extends AbstractCheck {

	private int comment = 0;
	
	@Override
	public boolean isCommentNodesRequired()
	{
		return true;
	}
	  
	@Override
	public int[] getAcceptableTokens() {
		// getting all acceptable token, single line comments
		return new int[] { TokenTypes.SINGLE_LINE_COMMENT };
	}
	
	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.SINGLE_LINE_COMMENT };
	}
	
	
	@Override
	public void beginTree(DetailAST rootAST)
	{
		comment = 0;
	}
	
	@Override
	public void finishTree(DetailAST rootAST)
	{
		// log results
		log(rootAST.getLineNo(),"number of single line comments: " + comment);
	    
		// re-initialize values
		comment = 0;
	}
	  
	@Override
	public void visitToken(DetailAST ast) {
		comment++;
	}
}