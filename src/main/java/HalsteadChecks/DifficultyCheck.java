package HalsteadChecks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
/*
 * This check to show how difficult to handle the program is. 
 * 
 */
public class DifficultyCheck extends AbstractCheck {

	public int count = 0;
	public double parameters = 0, difficulty = 0;
	private int[] operands = {
			TokenTypes.CHAR_LITERAL, 	TokenTypes.LITERAL_BOOLEAN, 	TokenTypes.LITERAL_BYTE,
			TokenTypes.LITERAL_CHAR, 	TokenTypes.LITERAL_INT,  		TokenTypes.LITERAL_LONG,
			TokenTypes.LITERAL_SHORT,	TokenTypes.LITERAL_VOID,	 	TokenTypes.STRING_LITERAL, 
			TokenTypes.NUM_DOUBLE,		TokenTypes.NUM_FLOAT, 			TokenTypes.NUM_INT,
			TokenTypes.NUM_LONG, 		TokenTypes.ARRAY_DECLARATOR 	

	};
	private int[] operators ={	TokenTypes.LCURLY, 			TokenTypes.LPAREN, 			TokenTypes.ARRAY_DECLARATOR, 
			TokenTypes.LITERAL_TRY, 	TokenTypes.LITERAL_CATCH, 
			TokenTypes.LITERAL_FINALLY, TokenTypes.PLUS_ASSIGN,	 	TokenTypes.MINUS_ASSIGN, 
			TokenTypes.STAR_ASSIGN, 	TokenTypes.DIV_ASSIGN,	 	TokenTypes.MOD_ASSIGN, 	TokenTypes.SR_ASSIGN,
			TokenTypes.BSR_ASSIGN, 		TokenTypes.SL_ASSIGN, 		TokenTypes.BAND_ASSIGN, 
			TokenTypes.BXOR_ASSIGN, 	TokenTypes.BOR_ASSIGN, 		TokenTypes.QUESTION, 	TokenTypes.LOR, 
			TokenTypes.LAND, 			TokenTypes.BOR, 			TokenTypes.BXOR, 		TokenTypes.BAND, TokenTypes.NOT_EQUAL, 
			TokenTypes.EQUAL, 			TokenTypes.LT, 				TokenTypes.GT, 			TokenTypes.LE, TokenTypes.GE, 
			TokenTypes.LITERAL_INSTANCEOF, TokenTypes.SL, 			TokenTypes.SR, 			TokenTypes.BSR, 
			TokenTypes.PLUS, 			TokenTypes.MINUS,			TokenTypes.DIV, 		TokenTypes.MOD, TokenTypes.INC, 
			TokenTypes.DEC,				TokenTypes.BNOT, 			TokenTypes.LNOT, 		TokenTypes.LITERAL_TRUE, 
			TokenTypes.LITERAL_FALSE, 	TokenTypes.LITERAL_NULL,	TokenTypes.LITERAL_NEW, 
			TokenTypes.LITERAL_ASSERT,	TokenTypes.STATIC_IMPORT,	TokenTypes.ENUM, 
			TokenTypes.LITERAL_FOR, 	TokenTypes.LITERAL_BREAK, 	TokenTypes.ELLIPSIS, 	TokenTypes.LAMBDA,
			TokenTypes.ABSTRACT, 		TokenTypes.ASSIGN, 			TokenTypes.DOUBLE_COLON, TokenTypes.COMMA, 
			TokenTypes.STAR, 			TokenTypes.LITERAL_DO, 		TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_IF, 
			TokenTypes.LITERAL_ELSE, 	TokenTypes.LITERAL_THROW, 	TokenTypes.LITERAL_THROWS, 
			TokenTypes.LITERAL_INTERFACE, TokenTypes.UNARY_PLUS, 	TokenTypes.UNARY_MINUS, 
			TokenTypes.METHOD_CALL, 	TokenTypes.LITERAL_THIS,	TokenTypes.LITERAL_VOLATILE,
			TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER, 
			TokenTypes.LITERAL_TRANSIENT, TokenTypes.SEMI, 			TokenTypes.STRICTFP, 	TokenTypes.POST_DEC, 
			TokenTypes.POST_INC, 		TokenTypes.LITERAL_CLASS, 	TokenTypes.PACKAGE_DEF, 
			TokenTypes.LITERAL_RETURN, TokenTypes.LITERAL_PRIVATE, 	TokenTypes.LITERAL_PUBLIC, 
			TokenTypes.LITERAL_PROTECTED, TokenTypes.ABSTRACT, 		TokenTypes.FINAL, 
			TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_SYNCHRONIZED,
			TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_DEFAULT, TokenTypes.DOT, 			TokenTypes.DO_WHILE, 
			TokenTypes.SLIST, 			TokenTypes.IMPORT
};
	private Set<String> uniqueOperators = new HashSet<String>();
	private Set<String> uniqueOperands = new HashSet<String>();

	  @Override
	  public int[] getAcceptableTokens() {
		    return this.getRequiredTokens();
	  }

	  
	  @Override
	  public int[] getRequiredTokens() {
		  return new int[] {TokenTypes.LCURLY, TokenTypes.LPAREN, 
		            TokenTypes.ARRAY_DECLARATOR, TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_CATCH, 
		            TokenTypes.LITERAL_FINALLY, TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, 
		            TokenTypes.STAR_ASSIGN, TokenTypes.DIV_ASSIGN, TokenTypes.MOD_ASSIGN, TokenTypes.SR_ASSIGN,
		            TokenTypes.BSR_ASSIGN, TokenTypes.SL_ASSIGN, TokenTypes.BAND_ASSIGN, 
		            TokenTypes.BXOR_ASSIGN, TokenTypes.BOR_ASSIGN, TokenTypes.QUESTION, TokenTypes.LOR, 
		            TokenTypes.LAND, TokenTypes.BOR, TokenTypes.BXOR, TokenTypes.BAND, TokenTypes.NOT_EQUAL, 
		            TokenTypes.EQUAL, TokenTypes.LT, TokenTypes.GT, TokenTypes.LE, TokenTypes.GE, 
		            TokenTypes.LITERAL_INSTANCEOF, TokenTypes.SL, TokenTypes.SR, TokenTypes.BSR, 
		            TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.MOD, TokenTypes.INC, 
		            TokenTypes.DEC, TokenTypes.BNOT, TokenTypes.LNOT, TokenTypes.LITERAL_TRUE, 
		            TokenTypes.LITERAL_FALSE, TokenTypes.LITERAL_NULL, TokenTypes.LITERAL_NEW, 
		            TokenTypes.LITERAL_ASSERT, TokenTypes.STATIC_IMPORT, TokenTypes.ENUM, 
		            TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_BREAK, TokenTypes.ELLIPSIS, TokenTypes.LAMBDA,
		            TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.DOUBLE_COLON, TokenTypes.COMMA, 
		            TokenTypes.STAR, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_IF, 
		            TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, 
		            TokenTypes.LITERAL_INTERFACE, TokenTypes.UNARY_PLUS, TokenTypes.UNARY_MINUS, 
		            TokenTypes.METHOD_CALL, TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_VOLATILE,
		            TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER, 
		            TokenTypes.LITERAL_TRANSIENT, TokenTypes.SEMI, TokenTypes.STRICTFP, TokenTypes.POST_DEC, 
		            TokenTypes.POST_INC, TokenTypes.LITERAL_CLASS, TokenTypes.PACKAGE_DEF, 
		            TokenTypes.LITERAL_RETURN, TokenTypes.LITERAL_PRIVATE, TokenTypes.LITERAL_PUBLIC, 
		            TokenTypes.LITERAL_PROTECTED, TokenTypes.ABSTRACT, TokenTypes.FINAL, 
		            TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_SYNCHRONIZED,
		            TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_DEFAULT, TokenTypes.DOT, TokenTypes.DO_WHILE, 
		            TokenTypes.SLIST, TokenTypes.IMPORT, TokenTypes.LITERAL_VOID, TokenTypes.LITERAL_BOOLEAN, TokenTypes.LITERAL_BYTE, 
		            TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_INT, 
		            TokenTypes.LITERAL_FLOAT, TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_DOUBLE, 
		            TokenTypes.IDENT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, 
		            TokenTypes.NUM_LONG, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL
		    };
	  }


	  @Override
	  public int[] getDefaultTokens() {
		    return this.getRequiredTokens();
	  }

	@Override
	public void beginTree(DetailAST rootAST)
	{
		parameters = 0;
		difficulty = 0;
		count = 0;
	}
	

	@Override
  	public void finishTree(DetailAST rootAST)
	{
		if (parameters == 0) {
			difficulty = 0;
		} else {
			// (n1 / 2) * (N2 / n2) 
			difficulty = (uniqueOperators.size() / 2) * ((parameters)/ (uniqueOperands.size()));
		}
		//System.out.print(uniqueOperators.size() + " = operator\n");
		//System.out.print(uniqueOperands.size() + " = operand-u\n");
		//System.out.print(parameters + " = param\n");
		log(rootAST.getLineNo(),"Halstead Difficulty: " + difficulty);
	}
  
	@Override
	public void visitToken(DetailAST rootAST) {
	    int type = rootAST.getType();

	    if (Arrays.stream(operators).anyMatch(token -> type == token)) {
	        uniqueOperators.add(rootAST.getText());
	        count++;
	    }

	    if (Arrays.stream(operands).anyMatch(token -> type == token)) {
	        uniqueOperands.add(rootAST.getText());
	        parameters++;
	        count++;
	    }
	    
	}

  
}