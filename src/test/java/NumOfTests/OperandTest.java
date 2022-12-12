package NumOfTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import NumOfChecks.OperandCheck;

import com.puppycrawl.tools.checkstyle.api.*;

public class OperandTest {
	
	int[] tokens = new int[] {
			TokenTypes.CHAR_LITERAL, 	TokenTypes.LITERAL_BOOLEAN, 	TokenTypes.LITERAL_BYTE,
			TokenTypes.LITERAL_CHAR, 	TokenTypes.LITERAL_INT,  		TokenTypes.LITERAL_LONG,
			TokenTypes.LITERAL_SHORT,	TokenTypes.LITERAL_VOID,	 	TokenTypes.STRING_LITERAL, 
			TokenTypes.NUM_DOUBLE,		TokenTypes.NUM_FLOAT, 			TokenTypes.NUM_INT,
			TokenTypes.NUM_LONG
	};

	@Test
	public void getDefaultTokensTest() {
		OperandCheck check = new OperandCheck();
		assertArrayEquals(check.getDefaultTokens(), tokens);
	}
	
	@Test
	public void getAcceptableTokensTest() {
		OperandCheck check = new OperandCheck();
		assertArrayEquals(check.getAcceptableTokens(), tokens);
	}
	
	@Test
	public void getRequiredTokensTest() {
		OperandCheck check = new OperandCheck();
		assertArrayEquals(check.getRequiredTokens(), tokens);

	}
	
	@Test
	public void beginTreeTest() {
		OperandCheck check = new OperandCheck();
		OperandCheck spyCheck = Mockito.spy(check);
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
		OperandCheck check = new OperandCheck();
		OperandCheck spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		
		// verify countoperandsOperands is not called
		when(mockAST.getType()).thenReturn(TokenTypes.CHAR_LITERAL);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(1)).visitToken(mockAST);
		
		// verify countoperandsOperands is called
		when(mockAST.getType()).thenReturn(TokenTypes.LITERAL_INT);
		spyCheck.visitToken(mockAST);
		verify(spyCheck,times(2)).visitToken(mockAST);
	}
}
