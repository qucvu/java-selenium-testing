package javaTester;

public class Topic_04_And_Or {
	public static void main(String[] args) {
		boolean statusA;
		boolean statusB;
		
		//And: 1 kq sai sẽ ra sai
		//Or: 1 kq đúng sẽ ra đúng
		statusA = true;
		statusB = false;
		System.out.println(statusA && statusB);
		System.out.println(statusA || statusB);
	
	}

}
