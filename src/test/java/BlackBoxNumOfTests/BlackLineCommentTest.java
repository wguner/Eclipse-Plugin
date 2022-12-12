package BlackBoxNumOfTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

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

import NumOfChecks.LineCommentCheck;

public class BlackLineCommentTest {
	int results = 0;
	
	@Test
	void test1() throws IOException, CheckstyleException {
		int result;
		// Build File
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "EmptyClass.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);	
		
		DetailAST root = JavaParser.parseFile(file, JavaParser.Options.WITH_COMMENTS);
		LineCommentCheck check = new LineCommentCheck();
		
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		check.beginTree(root);
		result = helper(check,root);
		check.finishTree(root);
		
		//System.out.print(check.count + " = Comments\n");
		assertTrue(results == 0);
	}
	
	@Test
	void test2() throws IOException, CheckstyleException {
		int result;
		// Build File
		String filePath = "src/test/java/TestFiles/";
		File file = new File(filePath + "Comments.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);	
		DetailAST root = JavaParser.parseFile(file, JavaParser.Options.WITH_COMMENTS);
		
		LineCommentCheck check = new LineCommentCheck();
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		check.beginTree(root);
		result = helper(check,root);
		check.finishTree(root);
		//System.out.print(check.count + " = 4Comments\n");
		
		
		//System.out.print(check.count + " = Comments\n");
		assertTrue(result == 1);
	}
	
	public int helper(AbstractCheck b, DetailAST a) {
		while(a != null) {
			  if(a.getType() == TokenTypes.SINGLE_LINE_COMMENT)
			  {
				  results++;
			  }
			b.visitToken(a);
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
		}
		
		//System.out.print(results + " = results\n");
		return results;
		
	}
	
	

}
