package LibrarySystemWithGUI;

public class Academic extends RegularMember {


	Academic(){
		
	}
	
	Academic(String name, int id, int quota){
		super(name, id, quota);
		this.memberClass = 3;
	}
	
	
	
	static void createAccount() {
		System.out.println("Enter new member name please: ");
		String accountName = scanner.nextLine();
		System.out.println("Enter new member ID please: ");
		int accountID = scanner.nextInt();
		scanner.nextLine();
		memberArray.add(new Academic(accountName, accountID, 3));
		memberCount++;
		System.out.println("A new member is added to the library with name " + accountName + " and member ID# " + accountID + ".");
		System.out.println("Total number of members: " + memberCount);

	}
}
