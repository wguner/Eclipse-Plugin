package TestFiles;

public class Difficulty1 {
	public void test(int[] a) {
		int temp = a[0];
		a[0] = a[a.length-1];
		a[a.length-1] = temp;
	}
}
