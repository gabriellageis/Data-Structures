package Recursion26;

public class Main26 {

	public static void main(String[] args) {
		
		System.out.println(recursiveFactorial(10));

	}
	
	public static int recursiveFactorial(int num) {
		
		if (num == 0) {
			System.out.println(num);
			return 1;
		}
		System.out.println(num);
		return num * recursiveFactorial(num - 1);
	}
	
	public static int iterativeFactorial(int num) {
		
		if (num == 0) {
			return 1;
		}
		
		int factorial = 1;
		
		for(int i = 1; i <= num; i++) {
			factorial *= i;
		}
		
		return factorial;
	}

}
