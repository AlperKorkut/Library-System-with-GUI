package LibrarySystemWithGUI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegularMember implements LibraryData, Comparable<RegularMember> {
	
	String memberName;
	int memberID;
	private ArrayList<Book> checkedOutBooks = new ArrayList<>();
	private ArrayList<OnlineArticle> accessableOnlineArticles = new ArrayList<>();
	static int memberCount;
	int checkedOutBookCount;
	int accessableOnlineArticleCount;
	int memberClass;
	int bookQuota;
	int onlineArticleQuota;
	double MemberOverdueFee = 0.00;

	
	static ArrayList<RegularMember> memberArray = new ArrayList<>();
	
	static public Scanner scanner = new Scanner(System.in);
	
	static JFrame memberCreationScreen = new JFrame("Member Creation Screen");
	static JDialog successfullyCreatedMember = new JDialog();
	
	static JFrame displayAccountsScreen = new JFrame("All Member Accounts");
	
	static JFrame overduePaymentsScreen = new JFrame("Overdue Payments (Descending)");
	
	
	RegularMember(){
		
	}
	
	RegularMember(String name, int id, int quota){
		this.memberName = name;
		this.memberID = id;
		this.memberClass = 1;
		this.bookQuota = quota;
		this.onlineArticleQuota = quota;
		

	}
	
	void setCheckedOutBook(Book bookToCheckOut) {
		this.checkedOutBooks.add(bookToCheckOut);
		this.checkedOutBookCount++;
	}
	
	void setAccessedArticle(OnlineArticle articleToAccess) {
		this.accessableOnlineArticles.add(articleToAccess);
		this.accessableOnlineArticleCount++;
	}
	
	ArrayList<Book> getCheckedOutBooks(){
		return this.checkedOutBooks;
	}
	
	ArrayList<OnlineArticle> getAccessableOnlineArticles(){
		return this.accessableOnlineArticles;
	}
	
	void removeCheckedOutBook(Book bookToRemove) {
		Iterator<Book> bookToRemoveIterator = this.checkedOutBooks.iterator();
		while(bookToRemoveIterator.hasNext()) {
			Book bookThatWillGetRemoved = bookToRemoveIterator.next();
			if(bookThatWillGetRemoved.ISBNNumber.equals(bookToRemove.ISBNNumber)) {
				bookToRemoveIterator.remove();
			}
		}
		this.checkedOutBookCount--;
	}
	
	void removeAccessedOnlineArticle(OnlineArticle articleToRemove) {
		Iterator<OnlineArticle> articleToRemoveIterator = this.accessableOnlineArticles.iterator();
		while(articleToRemoveIterator.hasNext()) {
			OnlineArticle onlineArticleThatWillGetRemoved = articleToRemoveIterator.next();
			if(onlineArticleThatWillGetRemoved.DOI.equals(articleToRemove.DOI)) {
				articleToRemoveIterator.remove();
			}
		}
		this.accessableOnlineArticleCount--;
	}
	
	
	static void createAccount() {
		/*System.out.println("Enter new member name please: ");
		String accountName = scanner.nextLine();
		System.out.println("Enter new member ID please: ");
		int accountID = scanner.nextInt();
		scanner.nextLine();
		memberArray.add(new RegularMember(accountName, accountID, 1));
		memberCount++;
		System.out.println("A new member is added to the library with name " + accountName + " and member ID# " + accountID + ".");
		System.out.println("Total number of members: " + memberCount);*/
		
		String[] memberTypes = {"Regular Member", "Student", "Academic"};
		memberCreationScreen.setSize(250,200);
		memberCreationScreen.setLayout(new GridLayout(4,2));
		memberCreationScreen.add(new JLabel("Member Type:"));
		JComboBox memberTypeBox = new JComboBox(memberTypes);
		memberCreationScreen.add(memberTypeBox);
		memberCreationScreen.add(new JLabel("Member Name:"));
		JTextField accountNameField = new JTextField();
		memberCreationScreen.add(accountNameField);
		memberCreationScreen.add(new JLabel("Member ID:"));
		JTextField accountIDField = new JTextField();
		memberCreationScreen.add(accountIDField);
		memberCreationScreen.add(new JLabel(""));
		Button submitButton = new Button("Submit");
		memberCreationScreen.add(submitButton);
		memberCreationScreen.setVisible(true);
		
		successfullyCreatedMember.setLayout(new FlowLayout());
		successfullyCreatedMember.setSize(1000,100);
		
		

		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String accountName = accountNameField.getText();
				String accountIDString = accountIDField.getText();
				int accountID = Integer.parseInt(accountIDString);
				if(memberTypeBox.getSelectedItem().equals("Regular Member")) {
					memberArray.add(new RegularMember(accountName, accountID, 1));
					memberCount++;
					successfullyCreatedMember.add(new JLabel("A new member is added to the library with name " + accountName + " and member ID# " + accountID + ".\n The total number of member(s) is " + memberCount + ".\n You can close the tab now."));
					successfullyCreatedMember.setVisible(true);
					memberCreationScreen.setVisible(false);
				}
				else if(memberTypeBox.getSelectedItem().equals("Student")) {
					memberArray.add(new Student(accountName, accountID, 2));
					memberCount++;
					successfullyCreatedMember.add(new JLabel("A new member is added to the library with name " + accountName + " and member ID# " + accountID + ".\n The total number of member(s) is " + memberCount + ".\n You can close the tab now."));
					successfullyCreatedMember.setVisible(true);
					memberCreationScreen.setVisible(false);
				}
				else if(memberTypeBox.getSelectedItem().equals("Academic")) {
					memberArray.add(new Academic(accountName, accountID, 3));
					memberCount++;
					successfullyCreatedMember.add(new JLabel("A new member is added to the library with name " + accountName + " and member ID# " + accountID + ".\n The total number of member(s) is " + memberCount + ".\n You can close the tab now."));
					successfullyCreatedMember.setVisible(true);
					memberCreationScreen.setVisible(false);
				}
			}
		});
		
	}
	
	@Override
	public int compareTo(RegularMember otherMember) {
		int modf1 = (int) (this.MemberOverdueFee * 1000);
		int modf2 = (int) (otherMember.MemberOverdueFee * 1000);
		return modf1 - modf2;
	}
	
	
	public double calculateCost() {
		double totalBookCost = 0.00;
		double totalOnlineArticleCost = 0.00;
		double overdueBookFee = 0.00;
		double overdueOnlineArticleFee = 0.00;
		LocalDate today = LocalDate.now();
		Iterator<Book> booksToCalculateTheCostOfIterator = this.getCheckedOutBooks().iterator();
		Iterator<OnlineArticle> onlineArticlesToCalculateTheCostOfIterator = this.getAccessableOnlineArticles().iterator();
		while(booksToCalculateTheCostOfIterator.hasNext()) {
			Book bookToGetTheCostOf = booksToCalculateTheCostOfIterator.next();
			LocalDate bookDueDate = LocalDate.of(bookToGetTheCostOf.dueDate.year, bookToGetTheCostOf.dueDate.month, bookToGetTheCostOf.dueDate.day);
			totalBookCost += bookToGetTheCostOf.cost;
			int bookOverdueDays = (int) ChronoUnit.DAYS.between(bookDueDate, today);
			if(bookOverdueDays > 0) {
				totalBookCost += bookOverdueDays * bookToGetTheCostOf.feeByDay;
				bookToGetTheCostOf.overdueFee = bookOverdueDays * bookToGetTheCostOf.feeByDay;
				overdueBookFee += bookToGetTheCostOf.overdueFee;
			}
		}
		while(onlineArticlesToCalculateTheCostOfIterator.hasNext()) {
			OnlineArticle onlineArticleToGetTheCostOf = onlineArticlesToCalculateTheCostOfIterator.next();
			// Overdue fees were calculated according to the access date in the instructions but in the walkthrough it made the user get the due date
			// for the article so I wrote it with due days and made the one with access date a comment.
			/*  This part was with access date but it seems like we give articles overdue fees by due dates so this is here as a comment now
			LocalDate onlineArticleAccessDate = LocalDate.of(onlineArticleToGetTheCostOf.accessDate.year, onlineArticleToGetTheCostOf.accessDate.month, onlineArticleToGetTheCostOf.accessDate.day);
			totalOnlineArticleCost += onlineArticleToGetTheCostOf.cost;
			int onlineArticleOverdueDays = (int) ChronoUnit.DAYS.between(onlineArticleAccessDate, today) - 30;
			if(onlineArticleOverdueDays > 0) {
				totalOnlineArticleCost += onlineArticleOverdueDays * 10.0;
				onlineArticleToGetTheCostOf.overdueFee = onlineArticleOverdueDays * 10.0;
			}
			*/
			LocalDate onlineArticleDueDate = LocalDate.of(onlineArticleToGetTheCostOf.dueDate.year, onlineArticleToGetTheCostOf.dueDate.month, onlineArticleToGetTheCostOf.dueDate.day);
			totalOnlineArticleCost += onlineArticleToGetTheCostOf.cost;
			int onlineArticleOverdueDays = (int) ChronoUnit.DAYS.between(onlineArticleDueDate, today);
			if(onlineArticleOverdueDays > 0) {
				totalOnlineArticleCost += onlineArticleOverdueDays * 10.0;
				onlineArticleToGetTheCostOf.overdueFee = onlineArticleOverdueDays * 10.0;
				overdueOnlineArticleFee += onlineArticleToGetTheCostOf.overdueFee;
			}
		}
		MemberOverdueFee = overdueBookFee + overdueOnlineArticleFee;
		return totalBookCost + totalOnlineArticleCost;
	}
	
	/*
	static void displayAllAccountsOld() {
		Iterator<RegularMember> memberIterator = memberArray.iterator();
		while(memberIterator.hasNext()) {
			RegularMember memberToPrint = memberIterator.next();
			Iterator<Book> checkedOutBookIterator = memberToPrint.checkedOutBooks.iterator();
			Iterator<OnlineArticle> accessableOnlineArticleIterator = memberToPrint.accessableOnlineArticles.iterator();
			if(memberToPrint.checkedOutBookCount != 0) {
				System.out.println(memberToPrint.memberName + " has the following books checked out: ");
				while(checkedOutBookIterator.hasNext()) {
					Book bookToPrint = checkedOutBookIterator.next();
					System.out.println("Book titled " + bookToPrint.nameOfBook + " until " + bookToPrint.dueDate + ".");
				}
				System.out.println(" ");
			}
			if(memberToPrint.accessableOnlineArticleCount != 0) {
				System.out.println(memberToPrint.memberName + " has access to the following online articles: ");
				while(accessableOnlineArticleIterator.hasNext()) {
					OnlineArticle onlineArticleToPrint = accessableOnlineArticleIterator.next();
					System.out.println("Online article entitled " + onlineArticleToPrint.nameOfArticle + " with DOI " + onlineArticleToPrint.DOI + ".");
				}
				System.out.println(" ");
			}
		}
	}
	*/
	
	
	static void displayAllAccounts() {
		/*Iterator<RegularMember> memberIterator = memberArray.iterator();
		while(memberIterator.hasNext()) {
			RegularMember memberToPrint = memberIterator.next();
			memberToPrint.calculateCost();
			System.out.println("User Name: " + memberToPrint.memberName);
			System.out.println("User ID: " + memberToPrint.memberID);
			System.out.println(" ");
			System.out.println("Checked Out Books: ");
			if(memberToPrint.checkedOutBookCount != 0) {
				System.out.println(" ");
				Iterator<Book> checkedOutBookIterator = memberToPrint.getCheckedOutBooks().iterator();
				while(checkedOutBookIterator.hasNext()) {
					Book checkedOutBookToPrint = checkedOutBookIterator.next();
					System.out.println("Book Name: " + checkedOutBookToPrint.nameOfBook);
					System.out.println("Book ISBN Number: " + checkedOutBookToPrint.ISBNNumber);
					if(checkedOutBookToPrint.overdueFee != 0.0) {
						System.out.println("The book entitled " + checkedOutBookToPrint.nameOfBook + " has an overdue charge of " + checkedOutBookToPrint.overdueFee + " TRL.");
					}
					System.out.println(" ");
				}
			}
			else {
				System.out.println("None.");
				System.out.println(" ");
			}
			System.out.println("Obtained Online Articles: ");
			if(memberToPrint.accessableOnlineArticleCount != 0) {
				System.out.println(" ");
				Iterator<OnlineArticle> accessableOnlineArticleIterator = memberToPrint.getAccessableOnlineArticles().iterator();
				while(accessableOnlineArticleIterator.hasNext()) {
					OnlineArticle accessableOnlineArticleToPrint = accessableOnlineArticleIterator.next();
					System.out.println("Online Article Name: " + accessableOnlineArticleToPrint.nameOfArticle);
					System.out.println("Online Article DOI: " + accessableOnlineArticleToPrint.DOI);
					if(accessableOnlineArticleToPrint.overdueFee != 0.0) {
						System.out.println("The online article entitled " + accessableOnlineArticleToPrint.nameOfArticle + " has an overdue charge of " + accessableOnlineArticleToPrint.overdueFee + " TRL.");
					}
					System.out.println(" ");
				}
			}
			else {
				System.out.println("None.");
				System.out.println(" ");
			}
		}*/
		
		displayAccountsScreen.setSize(400,500);
		displayAccountsScreen.setLayout(new BorderLayout());
		Button closeButton = new Button("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				displayAccountsScreen.setVisible(false);
			}
		});
		displayAccountsScreen.add(closeButton, BorderLayout.SOUTH);
		
		String[] memberInfos = new String[memberCount];
		int infoIndex = 0;
		Iterator<RegularMember> memberIterator = memberArray.iterator();
		while(memberIterator.hasNext()) {
			RegularMember memberToPrint = memberIterator.next();
			memberToPrint.calculateCost();
			memberInfos[infoIndex] = "Member Name: " + memberToPrint.memberName + ", Member ID: " + memberToPrint.memberID;
			infoIndex++;
		}
		JList<String> membersList = new JList<String>(memberInfos);
		displayAccountsScreen.add(membersList, BorderLayout.NORTH);
		
		displayAccountsScreen.setVisible(true);
	}

	static void displayOverduePaymentsDescending() {
		/*ArrayList<RegularMember> membersInDescendingOrder = new ArrayList<>();
		Iterator<RegularMember> memberArrayIterator = memberArray.iterator();
		while(memberArrayIterator.hasNext()) {
			RegularMember memberToAdd = memberArrayIterator.next();
			membersInDescendingOrder.add(memberToAdd);
		}
		Collections.sort(membersInDescendingOrder);
		Collections.reverse(membersInDescendingOrder);
		Iterator<RegularMember> membersWithOverduePaymentsIterator = membersInDescendingOrder.iterator();
		while(membersWithOverduePaymentsIterator.hasNext()) {
			RegularMember memberToCheck = membersWithOverduePaymentsIterator.next();
			memberToCheck.calculateCost();
			if(memberToCheck.MemberOverdueFee != 0.00) {
				System.out.println("Member " + memberToCheck.memberName + " with user ID " + memberToCheck.memberID + " is due to pay " + memberToCheck.MemberOverdueFee + " TRL as overdue fees.");
				System.out.println(" ");
				
			}
		}
		System.out.println(" ");*/
		
		int overdueMembersCount = 0;
		ArrayList<String> overduePaymentInfos = new ArrayList<String>();
		Iterator<String> overduePaymentInfosIterator = overduePaymentInfos.iterator();
		ArrayList<RegularMember> membersInDescendingOrder = new ArrayList<>();
		Iterator<RegularMember> memberArrayIterator = memberArray.iterator();
		while(memberArrayIterator.hasNext()) {
			RegularMember memberToAdd = memberArrayIterator.next();
			membersInDescendingOrder.add(memberToAdd);
		}
		Collections.sort(membersInDescendingOrder);
		Collections.reverse(membersInDescendingOrder);
		Iterator<RegularMember> membersWithOverduePaymentsIterator = membersInDescendingOrder.iterator();
		while(membersWithOverduePaymentsIterator.hasNext()) {
			RegularMember memberToCheck = membersWithOverduePaymentsIterator.next();
			memberToCheck.calculateCost();
			if(memberToCheck.MemberOverdueFee != 0.00) {
				overduePaymentInfos.add("User Name: " + memberToCheck.memberName + ", ID: " + memberToCheck.memberID + ", Cost: " + memberToCheck.MemberOverdueFee + " TL");
				overdueMembersCount++;
			}
		}
		String[] overduePayments = new String[overdueMembersCount];
		int index = 0;
		while(overduePaymentInfosIterator.hasNext()) {
			overduePayments[index] = overduePaymentInfosIterator.next();
			index++;
		}
		JList<String> overduePaymentInfosList = new JList<String>(overduePayments);
		overduePaymentsScreen.setSize(500,300);
		overduePaymentsScreen.setLayout(new BorderLayout());
		overduePaymentsScreen.add(overduePaymentInfosList, BorderLayout.NORTH);
		Button closeButton = new Button("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				overduePaymentsScreen.setVisible(false);
			}
		});
		overduePaymentsScreen.add(closeButton, BorderLayout.SOUTH);
		overduePaymentsScreen.setVisible(true);
	}

	
}
