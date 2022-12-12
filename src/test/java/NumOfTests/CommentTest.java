package NumOfTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.*;
import NumOfChecks.CommentsCheck;

public class CommentTest {
	@Test
	public void isCommentNodesRequiredTest() {
		CommentsCheck check = new CommentsCheck();
		assertTrue(check.isCommentNodesRequired());
	}
	
	@Test
	public void getDefaultTokensTest() {
		CommentsCheck check = new CommentsCheck();
		int[] arr = new int[] { TokenTypes.COMMENT_CONTENT};
		assertArrayEquals(check.getDefaultTokens(), arr);
	}
	
	@Test
	public void getAcceptableTokensTest() {
		CommentsCheck check = new CommentsCheck();
		int[] arr = new int[] { TokenTypes.COMMENT_CONTENT};
		assertArrayEquals(check.getAcceptableTokens(), arr);
	}
	
	@Test
	public void getRequiredTokensTest() {
		CommentsCheck check = new CommentsCheck();
		int arr[] = new int[] { TokenTypes.COMMENT_CONTENT};
		assertArrayEquals(check.getRequiredTokens(), arr);

	}
	
	@Test
	public void beginTreeTest() {
		CommentsCheck check = new CommentsCheck();
		CommentsCheck spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		verify(spyCheck, times(3)).beginTree(mockAST);
		assertEquals(0, spyCheck.count);
	}
	
	@Test
	public void finishTreeTest() {

	}
	
	@Test
	public void visitTokenTest() {
		CommentsCheck check = new CommentsCheck();
		CommentsCheck spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		
		// if statement
		when(mockAST.getType()).thenReturn(TokenTypes.SINGLE_LINE_COMMENT);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(1)).visitToken(mockAST);
		assertEquals(1, spyCheck.count);
		spyCheck.count -= 1;
		
		// else if statement
		when(mockAST.getType()).thenReturn(TokenTypes.COMMENT_CONTENT);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(2)).visitToken(mockAST);
		assertEquals(1, spyCheck.count);
	}
}
