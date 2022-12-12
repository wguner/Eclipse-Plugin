package BlackBoxNumOfTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.*;
import com.puppycrawl.tools.checkstyle.api.*;

import NumOfChecks.ExpressionCheck;

public class BlackExpressionTest {
	
	private static final String ArrayUtils = null;
	int results = 0;
	
	@Test
	void test1() throws IOException, CheckstyleException {
		// Build File
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "EmptyClass.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);	
		DetailAST root = JavaParser.parse(fc);
		ExpressionCheck check = new ExpressionCheck();
		
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		check.beginTree(root);
		helper(check,root);
		check.finishTree(root);
		System.out.print(check.count + "\n");
		assertTrue(check.count == 0);
	}
	
	@Test
	void test2() throws IOException, CheckstyleException {
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "Expression1.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);	
		
		DetailAST root = JavaParser.parse(fc);
		ExpressionCheck check = new ExpressionCheck();
		
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		check.beginTree(root);
		helper(check,root);
		check.finishTree(root);
		
		System.out.print(check.count + "\n");
		assertTrue(check.count == 1);
		
	}
	
	public void helper(AbstractCheck b, DetailAST a) {
		int[] tokens = b.getAcceptableTokens();
		while(a != null) {
			if (contains(tokens, a.getType())) {
					results++;
			}
				
			b.visitToken(a);
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
			System.out.print(results + "\n");
		}
	}
	
    public boolean contains(int[] array, int key) {
        return Arrays.stream(array).anyMatch(i -> i == key);
    }

}
