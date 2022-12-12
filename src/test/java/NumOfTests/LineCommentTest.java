package NumOfTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.*;
import NumOfChecks.LineCommentCheck;


public class LineCommentTest {
	

	@Test
	public void isCommentNodesRequiredTest() {
		LineCommentCheck check = new LineCommentCheck();
		assertTrue(check.isCommentNodesRequired());
	}
	
	@Test
	public void getDefaultTokensTest() {
		LineCommentCheck check = new LineCommentCheck();
		int[] arr = new int[] { TokenTypes.SINGLE_LINE_COMMENT };
		assertArrayEquals(check.getDefaultTokens(), arr);
	}
	
	@Test
	public void getAcceptableTokensTest() {
		LineCommentCheck check = new LineCommentCheck();
		int[] arr = new int[] { TokenTypes.SINGLE_LINE_COMMENT };
		assertArrayEquals(check.getAcceptableTokens(), arr);
	}
	
	@Test
	public void getRequiredTokensTest() {
		LineCommentCheck check = new LineCommentCheck();
		int arr[] = new int[] { TokenTypes.SINGLE_LINE_COMMENT };
		assertArrayEquals(check.getRequiredTokens(), arr);

	}
	
	@Test
	public void beginTreeTest() {
		LineCommentCheck check = new LineCommentCheck();
		LineCommentCheck spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		verify(spyCheck, times(3)).beginTree(mockAST);
		assertEquals(0, check.count);
	}
	
	@Test
	public void finishTreeTest() {

	}
	
	@Test
	public void visitTokenTest() {
		LineCommentCheck check = new LineCommentCheck();
		DetailAST mockAST = mock(DetailAST.class);
		//doReturn(TokenTypes.PLUS).when(mockAST).getType();
		check.visitToken(mockAST);
		assertEquals(1,check.count);
		
	}
}