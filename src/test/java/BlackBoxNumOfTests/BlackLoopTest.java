package BlackBoxNumOfTests;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.*;
import com.puppycrawl.tools.checkstyle.api.*;

import NumOfChecks.LoopCheck;

public class BlackLoopTest {
	int results = 0;
	
	@Test
	void test1() throws IOException, CheckstyleException {
		// no loop test
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "Loop1.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);
		DetailAST root = JavaParser.parse(fc);
		
		LoopCheck check = new LoopCheck();
		
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		check.beginTree(root);
		helper(check,root);
		check.finishTree(root);
	
		assertTrue(results == 0);
		
	}
	
	@Test
	void test2() throws IOException, CheckstyleException {
		// yes loop test
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "Loop2.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);
		DetailAST root = JavaParser.parse(fc);
		LoopCheck check = new LoopCheck();
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		check.beginTree(root);
		helper(check,root);
		check.finishTree(root);
		
		assertTrue(results == 2);
		
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
		}
	}
	
    public boolean contains(int[] array, int key) {
        return Arrays.stream(array).anyMatch(i -> i == key);
    }
}
