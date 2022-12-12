package NumOfTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.*;
import NumOfChecks.LoopCheck;
public class LoopTest {
	@Test
	public void isCommentNodesRequiredTest() {
		LoopCheck check = new LoopCheck();
		assertTrue(check.isCommentNodesRequired());
	}
	
	
	@Test
	public void getDefaultTokensTest() {
		LoopCheck check = new LoopCheck();
		int[] arr = new int[] { TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_FOR };
		assertArrayEquals(check.getDefaultTokens(), arr);
	}
	
	@Test
	public void getAcceptableTokensTest() {
		LoopCheck check = new LoopCheck();
		int[] arr = new int[] { TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_FOR };
		assertArrayEquals(check.getAcceptableTokens(), arr);
	}
	
	@Test
	public void getRequiredTokensTest() {
		LoopCheck check = new LoopCheck();
		int arr[] = new int[] { TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_FOR };
		assertArrayEquals(check.getRequiredTokens(), arr);

	}
	
	@Test
	public void beginTreeTest() {
		LoopCheck check = new LoopCheck();
		LoopCheck spyCheck = Mockito.spy(check);
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
		LoopCheck check = new LoopCheck();
		LoopCheck spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		
		spyCheck.visitToken(mockAST);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(2)).visitToken(mockAST);
		assertEquals(2, spyCheck.count);
	}
}
