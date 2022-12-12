package NumOfTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.*;
import NumOfChecks.ExpressionCheck;

public class ExpressionTest {

	@Test
	public void getDefaultTokensTest() {
		ExpressionCheck check = new ExpressionCheck();
		int[] arr = new int[] { TokenTypes.EXPR };
		assertArrayEquals(check.getDefaultTokens(), arr);
	}
	
	
	@Test
	public void getAcceptableTokensTest() {
		ExpressionCheck check = new ExpressionCheck();
		int[] arr = new int[] { TokenTypes.EXPR };
		assertArrayEquals(check.getAcceptableTokens(), arr);
	}
	
	@Test
	public void getRequiredTokensTest() {
		ExpressionCheck check = new ExpressionCheck();
		int[] arr = new int[] { TokenTypes.EXPR };
		assertArrayEquals(check.getRequiredTokens(), arr);

	}
	
	@Test
	public void beginTreeTest() {
		ExpressionCheck check = new ExpressionCheck();
		ExpressionCheck spyCheck = Mockito.spy(check);
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
		ExpressionCheck check = new ExpressionCheck();
		ExpressionCheck spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		
		// if statement
		when(mockAST.getType()).thenReturn(TokenTypes.EXPR);
		spyCheck.visitToken(mockAST);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(2)).visitToken(mockAST);
		assertEquals(2, spyCheck.count);
		
		// else
		when(mockAST.getType()).thenReturn(TokenTypes.ABSTRACT);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(3)).visitToken(mockAST);
		assertEquals(2, spyCheck.count);
	}
}
