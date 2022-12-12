package BlackBoxNumOfTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.*;
import com.puppycrawl.tools.checkstyle.api.*;

import NumOfChecks.OperatorCheck;


public class BlackOperatorTest {
	int results = 0;
	
	@Test
	void test1() throws IOException, CheckstyleException {
		// Build File
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "EmptyClass.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);	
		DetailAST root = JavaParser.parse(fc);
		OperatorCheck check = new OperatorCheck();
		int result;
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		check.beginTree(root);
		result = helper(check,root);
		check.finishTree(root);
		assertTrue(result == 5);
	}
	
	@Test
	void test2() throws IOException, CheckstyleException {
		// Build File
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "Operator1.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);	
		DetailAST root = JavaParser.parse(fc);
		int result;
		OperatorCheck check = new OperatorCheck();
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		check.beginTree(root);
		result = helper(check,root);
		check.finishTree(root);
		assertTrue(result == 18);
	}
	
	public int helper(AbstractCheck b, DetailAST a) {
		int[] tokens = b.getAcceptableTokens();
		while(a != null) {
			if (contains(tokens, a.getType())) {
					results++;
			}
				
			b.visitToken(a);
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
		}
		//System.out.println(tokens);
		return results;
	}
	
    public boolean contains(int[] array, int key) {
        return Arrays.stream(array).anyMatch(i -> i == key);
    }

}
