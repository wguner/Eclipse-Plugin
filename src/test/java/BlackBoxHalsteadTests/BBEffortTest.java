package BlackBoxHalsteadTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.*;

import HalsteadChecks.EffortCheck;

public class BBEffortTest {
	
	double results = 0;
			
	@Test
	void test1() throws IOException, CheckstyleException {
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "Loop1.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);

		DetailAST root = JavaParser.parse(fc);
		EffortCheck check = new EffortCheck();
		
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		check.beginTree(root);
		helper(check,root);
		check.finishTree(root);

		System.out.print(check.effort + " = Effort\n");
		assertTrue(check.effort == 24);
	}
	
	@Test
	void test2() throws IOException, CheckstyleException {
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "Effort1.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);

		DetailAST root = JavaParser.parse(fc);
		EffortCheck check = new EffortCheck();

		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		check.beginTree(root);
		helper(check,root);
		check.finishTree(root);

		System.out.print(check.effort + " = Effort\n");
		assertTrue(check.effort == 1704);
	}
	
	@Test
	void test3() throws IOException, CheckstyleException {
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "Loop2.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);

		DetailAST root = JavaParser.parse(fc);
		EffortCheck check = new EffortCheck();

		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		check.beginTree(root);
		helper(check,root);
		check.finishTree(root);

		System.out.print(check.effort + " = Effort\n");
		assertTrue(check.effort == 205);
	}
	
	public void helper(AbstractCheck b, DetailAST a) {
		int[] tokens = b.getAcceptableTokens();
		
		while(a != null) {
			if(contains(tokens, a.getType())) {
				results++;
			}
			
			b.visitToken(a);
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
		}
		//System.out.print(results + " = rezzz\n");
	}
	
    public boolean contains(int[] array, int key) {
        return Arrays.stream(array).anyMatch(i -> i == key);
    }

}
