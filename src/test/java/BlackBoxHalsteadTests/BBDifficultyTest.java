package BlackBoxHalsteadTests;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import HalsteadChecks.DifficultyCheck;

public class BBDifficultyTest {
	
	double results = 0;

	@Test
	void test1() throws IOException, CheckstyleException {
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "EmptyClass.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);

		DetailAST root = JavaParser.parse(fc);
		DifficultyCheck check = new DifficultyCheck();
		
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		check.beginTree(root);
		helper(check,root);
		check.finishTree(root);

		assertTrue(check.difficulty == 0);
	}
	
	@Test
	void test2() throws IOException, CheckstyleException {
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "Difficulty1.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);
		double result;

		DetailAST root = JavaParser.parse(fc);
		DifficultyCheck check = new DifficultyCheck();

		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		check.beginTree(root);
		result = helper(check,root);
		check.finishTree(root);

		
		assertTrue(result == 40.0);
		System.out.println("NumExpression Check Done!");
	}
	
	public double helper(AbstractCheck b, DetailAST a) {
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
		return results;
	}
	
    public boolean contains(int[] array, int key) {
        return Arrays.stream(array).anyMatch(i -> i == key);
    }
}
