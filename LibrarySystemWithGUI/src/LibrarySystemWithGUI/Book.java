package LibrarySystemWithGUI;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Comparator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Book extends LibraryMaterial {
		
	String nameOfBook;
	String ISBNNumber;
	Date dueDate;
	static int bookCount = 0;
	double feeByDay = 50.0;
	
	static ArrayList<Book> bookArray = new ArrayList<>();
	
	static public Scanner scanner = new Scanner(System.in);
	
	static JFrame addBookMenu = new JFrame("Add a new book.");
	static JDialog addBookISBNExceptionPage = new JDialog();
	static JDialog successfullyAddedBook = new JDialog();
	
	static JFrame bookCheckoutMenu = new JFrame("Checkout a book.");
	static JDialog successfullyCheckedoutBook = new JDialog();
	
	static JFrame bookReturnMenu = new JFrame("Return a book.");
	static JDialog successfullyReturnedBook = new JDialog();
	
	Book(){
		
	}
	
	Book(String name, String ISBN, double bookCost){
		this.nameOfBook = name;
		this.ISBNNumber = ISBN;
		this.dueDate = new Date(0,0,0);
		this.cost = bookCost;
		
		
	}
	
	/*Book(String name, String ISBN, Date dateDue){
		this.nameOfBook = name;
		this.ISBNNumber = ISBN;
		this.dueDate = dateDue;
	}*/
	
	
	static void addBook() {
		/*System.out.println("Enter the book name please: ");
		String bookName = scanner.nextLine();
		String numberOfISBN;
		do {
			System.out.println("Enter the ISBN Number of the book please: ");
			numberOfISBN = scanner.nextLine();
		}while(!authenticateISBN(numberOfISBN));
		System.out.println("Enter the price please: ");
		double costOfBook = scanner.nextDouble();
		scanner.nextLine();
		bookArray.add(new Book(bookName, numberOfISBN, costOfBook));
		bookCount++;
		System.out.println("A new book is added to the library with the name " + bookName + ", ISBN# " + numberOfISBN + " and a cost of " + costOfBook + " TRL.");
		System.out.println("Total number of books in the library: " + bookCount);*/
		
		addBookMenu.setSize(280,230);
		addBookMenu.setLayout(new GridLayout(4,2));
		addBookMenu.add(new JLabel("Book Name:"));
		JTextField addBookNameField = new JTextField();
		addBookMenu.add(addBookNameField);
		addBookMenu.add(new JLabel("ISBN:"));
		JTextField addBookISBNField = new JTextField();
		addBookMenu.add(addBookISBNField);
		addBookMenu.add(new JLabel("Price:"));
		JTextField addBookPriceField = new JTextField();
		addBookMenu.add(addBookPriceField);
		addBookMenu.add(new JLabel(""));
		Button addBookSubmitButton = new Button("Submit");
		addBookSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookName = addBookNameField.getText();
				String numberOfISBN = addBookISBNField.getText();
				String costOfBookString = addBookPriceField.getText();
				double costOfBook = Double.parseDouble(costOfBookString);
				if(authenticateISBN(numberOfISBN)) {
					bookArray.add(new Book(bookName, numberOfISBN, costOfBook));
					bookCount++;
					successfullyAddedBook.setSize(900,100);
					addBookMenu.setVisible(false);
					successfullyAddedBook.add(new JLabel("A new book is added to the library with the name " + bookName + ", ISBN# " + numberOfISBN + " and a cost of " + costOfBook + " TRL.\n There are a total of " + bookCount + " book(s) in the library.\n You can close the tab now."));
					successfullyAddedBook.setVisible(true);
					addBookMenu.setVisible(false);
				};
			}
		});
		addBookMenu.add(addBookSubmitButton);
		addBookMenu.setVisible(true);
			
	}
	
	/*
	static void displayInfo() {
		int index = 0;
		while(index < bookCount) {
			System.out.println("Book titled " + bookArray[index].nameOfBook + " that has ISBN# " + bookArray[index].ISBNNumber + " is due " + bookArray[index].dueDate + ".");
			index++;
		}
	}
	*/
	
	static void bookCheckout() {
		/*System.out.println("Enter the ID of the member please: ");
		int IDofMember = scanner.nextInt();
		scanner.nextLine();
		Iterator<RegularMember> memberIterator = RegularMember.memberArray.iterator();
		while(memberIterator.hasNext()) {
			RegularMember memberToCheckABookOut = memberIterator.next();
			if(memberToCheckABookOut.memberID == IDofMember) {
				Iterator<Book> bookIterator = bookArray.iterator();
				System.out.println("Search for a Book with ISBN: ");
				String searchedBookISBN = scanner.nextLine();
				while(bookIterator.hasNext()) {
					Book bookToGetCheckedOut = bookIterator.next();
					if(bookToGetCheckedOut.ISBNNumber.equals(searchedBookISBN)) {
						if(memberToCheckABookOut.checkedOutBookCount < memberToCheckABookOut.bookQuota) {
							System.out.println("Enter a due year (YYYY) please: ");
							int dueYear = scanner.nextInt();
							scanner.nextLine();
							System.out.println("Enter a due month (MM) please: ");
							int dueMonth = scanner.nextInt();
							scanner.nextLine();
							System.out.println("Enter a due day (DD) please: ");
							int dueDay = scanner.nextInt();
							scanner.nextLine();
							Date dueDateToSet = Date.dateValidator(dueDay, dueMonth, dueYear);
							bookToGetCheckedOut.dueDate.setDate(dueDateToSet.day, dueDateToSet.month, dueDateToSet.year);
							memberToCheckABookOut.setCheckedOutBook(bookToGetCheckedOut);
							System.out.println("The book with the name " + bookToGetCheckedOut.nameOfBook + " (ISBN number: " + bookToGetCheckedOut.ISBNNumber + ") is checked out by the user " + memberToCheckABookOut.memberName + ".");
							Menu menu = new Menu();
							menu.menu();
						}
						else{
							System.out.println("The user's book check out quota is full.");
							Menu menu = new Menu();
							menu.menu();
						}
					}
				}
				System.out.println("There is no book with the given ISBN number.");
				Menu menu = new Menu();
				menu.menu();
			}
		}
		System.out.println("There is no account with the given ID.");
		Menu menu = new Menu();
		menu.menu();*/
		
		bookCheckoutMenu.setLayout(new GridLayout(6,2));
		bookCheckoutMenu.setSize(300,200);
		bookCheckoutMenu.add(new JLabel("Member ID:"));
		JTextField memberIDField = new JTextField();
		bookCheckoutMenu.add(memberIDField);
		bookCheckoutMenu.add(new JLabel("Book ISBN:"));
		JTextField bookISBNField = new JTextField();
		bookCheckoutMenu.add(bookISBNField);
		bookCheckoutMenu.add(new JLabel("Due Year (YYYY):"));
		JTextField dueYearField = new JTextField();
		bookCheckoutMenu.add(dueYearField);
		bookCheckoutMenu.add(new JLabel("Due Month (MM):"));
		JTextField dueMonthField = new JTextField();
		bookCheckoutMenu.add(dueMonthField);
		bookCheckoutMenu.add(new JLabel("Due Day (DD):"));
		JTextField dueDayField = new JTextField();
		bookCheckoutMenu.add(dueDayField);
		bookCheckoutMenu.add(new JLabel(""));
		Button submitButton = new Button("Submit");
		bookCheckoutMenu.add(submitButton);
		
		successfullyCheckedoutBook.setSize(1000,100);
		successfullyCheckedoutBook.setLayout(new FlowLayout());
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int memberID = Integer.parseInt(memberIDField.getText());
				String bookISBN = bookISBNField.getText();
				int dueYear = Integer.parseInt(dueYearField.getText());
				int dueMonth = Integer.parseInt(dueMonthField.getText());
				int dueDay = Integer.parseInt(dueDayField.getText());
				
				Iterator<RegularMember> memberIterator = RegularMember.memberArray.iterator();
				while(memberIterator.hasNext()) {
					RegularMember memberToCheckABookOut = memberIterator.next();
					if(memberToCheckABookOut.memberID == memberID) {
						Iterator<Book> bookIterator = bookArray.iterator();
						while(bookIterator.hasNext()) {
							Book bookToGetCheckedOut = bookIterator.next();
							if(bookToGetCheckedOut.ISBNNumber.equals(bookISBN)) {
								if(memberToCheckABookOut.checkedOutBookCount < memberToCheckABookOut.bookQuota) {
									Date dueDateToSet = Date.dateValidator(dueDay, dueMonth, dueYear);
									bookToGetCheckedOut.dueDate.setDate(dueDateToSet.day, dueDateToSet.month, dueDateToSet.year);
									memberToCheckABookOut.setCheckedOutBook(bookToGetCheckedOut);
									successfullyCheckedoutBook.add(new JLabel("The book with the name " + bookToGetCheckedOut.nameOfBook + " (ISBN number: " + bookToGetCheckedOut.ISBNNumber + ") is checked out by the user " + memberToCheckABookOut.memberName + ".\n You can close the tab now."));
									successfullyCheckedoutBook.setVisible(true);
									bookCheckoutMenu.setVisible(false);
								}
								successfullyCheckedoutBook.add(new JLabel("The user's book check out quota is full."));
								successfullyCheckedoutBook.setVisible(true);
							}
						}
						successfullyCheckedoutBook.add(new JLabel("There is no book with the given ISBN number."));
						successfullyCheckedoutBook.setVisible(true);
					}
				}
				successfullyCheckedoutBook.add(new JLabel("There is no account with the given ID."));
				successfullyCheckedoutBook.setVisible(true);
			}
		});
		
		bookCheckoutMenu.setVisible(true);
	}
	
	static void bookReturn() {
		/*System.out.println("Enter the ID of the member please: ");
		int IDofMember = scanner.nextInt();
		scanner.nextLine();
		Iterator<RegularMember> memberIterator = RegularMember.memberArray.iterator();
		while(memberIterator.hasNext()) {
			RegularMember memberToReturnABook = memberIterator.next();
			if(memberToReturnABook.memberID == IDofMember) {
				System.out.println("Enter the ISBN Number of the book you want to return please: ");
				String returnedBookISBN = scanner.nextLine();
				Iterator<Book> checkedOutBookIterator = memberToReturnABook.getCheckedOutBooks().iterator();
				while(checkedOutBookIterator.hasNext()) {
					Book bookToGetRemoved = checkedOutBookIterator.next();
					if(bookToGetRemoved.ISBNNumber.equals(returnedBookISBN)) {
						memberToReturnABook.removeCheckedOutBook(bookToGetRemoved);
						System.out.println("The book with the name " + bookToGetRemoved.nameOfBook + " (ISBN Number: " + bookToGetRemoved.ISBNNumber + ") is returned by the user " + memberToReturnABook.memberName + ".");
						Menu menu = new Menu();
						menu.menu();
					}
				}
				System.out.println("The user does not have any books checked out with the given ISBN Number.");
				Menu menu = new Menu();
				menu.menu();
			}
		}
		System.out.println("There is no account with the given ID.");
		Menu menu = new Menu();
		menu.menu();*/
		
		bookReturnMenu.setSize(250,150);
		bookReturnMenu.setLayout(new GridLayout(3,2));
		bookReturnMenu.add(new JLabel("Member ID:"));
		JTextField memberIDField = new JTextField();
		bookReturnMenu.add(memberIDField);
		bookReturnMenu.add(new JLabel("Book ISBN:"));
		JTextField bookISBNField = new JTextField();
		bookReturnMenu.add(bookISBNField);
		bookReturnMenu.add(new JLabel(""));
		Button submitButton = new Button("Submit");
		bookReturnMenu.add(submitButton);
		successfullyReturnedBook.setSize(1000,100);
		successfullyReturnedBook.setLayout(new FlowLayout());
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int memberID = Integer.parseInt(memberIDField.getText());
				String bookISBN = bookISBNField.getText();
				Iterator<RegularMember> memberIterator = RegularMember.memberArray.iterator();
				while(memberIterator.hasNext()) {
					RegularMember memberToReturnABook = memberIterator.next();
					if(memberToReturnABook.memberID == memberID) {
						Iterator<Book> checkedOutBookIterator = memberToReturnABook.getCheckedOutBooks().iterator();
						while(checkedOutBookIterator.hasNext()) {
							Book bookToGetRemoved = checkedOutBookIterator.next();
							if(bookToGetRemoved.ISBNNumber.equals(bookISBN)) {
								memberToReturnABook.removeCheckedOutBook(bookToGetRemoved);
								successfullyReturnedBook.add(new JLabel("The book with the name " + bookToGetRemoved.nameOfBook + " (ISBN Number: " + bookToGetRemoved.ISBNNumber + ") is returned by the user " + memberToReturnABook.memberName + ".\n You can close the tab now."));
								successfullyReturnedBook.setVisible(true);
								bookReturnMenu.setVisible(false);
							}
						}
						successfullyReturnedBook.add(new JLabel("The user does not have any books checked out with the given ISBN Number."));
						successfullyReturnedBook.setVisible(true);
					}
				}
				successfullyReturnedBook.add(new JLabel("There is no account with the given ID."));
				successfullyReturnedBook.setVisible(true);
			}
		});
		bookReturnMenu.setVisible(true);
	}
	
	public double calculateCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	static String[] displayBookDatabase() {
		/*Iterator<Book> bookDisplayIterator = bookArray.iterator();
		while(bookDisplayIterator.hasNext()) {
			Book bookToDisplay = bookDisplayIterator.next();
			System.out.println("Book Name: " + bookToDisplay.nameOfBook);
			System.out.println("ISBN Number: " + bookToDisplay.ISBNNumber);
			System.out.println("The cost of the book is " + bookToDisplay.cost + ".");
			System.out.println(" ");
		}*/
		
		String[] arrayToReturnBook = new String[bookCount];
		Iterator<Book> bookToAddToArrayIterator = bookArray.iterator();
		int index = 0;
		while(bookToAddToArrayIterator.hasNext()) {
			Book bookToAddToArray = bookToAddToArrayIterator.next();
			arrayToReturnBook[index] = "Book Name: " + bookToAddToArray.nameOfBook + "\n ISBN Number: " + bookToAddToArray.ISBNNumber + "\n The cost is " + bookToAddToArray.cost + ".";
			index++;
		}
		
		return arrayToReturnBook;
	}
	
	static boolean authenticateISBN(String ISBN) {
		boolean isValidFromTheStart = true;
		boolean isValidLength = true;
		boolean isValidSum = true;
		int total = 0;
		int index1 = 0;
		int index2 = 0;
		String newISBNNumberOne = "";
		String newISBNNumberTwo = "";
		String newISBNNumberThree = "";
		String ISBNWithoutOtherCharacters = ISBN.replaceAll("\\D","");
		ArrayList<String> numbersList = new ArrayList<String>(Arrays.asList(ISBNWithoutOtherCharacters.split("")));
		ArrayList<Integer> numbersOfISBN = new ArrayList<Integer>();
		if(ISBNWithoutOtherCharacters.length() != 13) {
			isValidLength = false;
		}
		while(index1 < numbersList.size()) {
			String numberToAddAsString = numbersList.get(index1);
			int numberToAddAsInteger = Integer.parseInt(numberToAddAsString);
			numbersOfISBN.add(numberToAddAsInteger);
			index1++;
		}
		while(index2 < numbersOfISBN.size()) {
			if((index2 + 1) % 2 == 1) {
				total += 1 * numbersOfISBN.get(index2);
				index2++;
			}
			else {
				total += 3 * numbersOfISBN.get(index2);
				index2++;
			}
		}
		if((total % 10) != 0) {
			isValidSum = false;
		}
		if(!(isValidLength && isValidSum)) {
			isValidFromTheStart = false;
		}
		if(!isValidLength && !isValidSum) {
			try {
				throw new ISBNMismatchException("Exception: The ISBN number must be 13 digits.");
			}
			catch(ISBNMismatchException e1) {
				addBookISBNExceptionPage.add(new JLabel(e1.getMessage()));
				addBookISBNExceptionPage.setSize(300,100);
				addBookISBNExceptionPage.setVisible(true);
			}
			finally {
				return false;
			}
		}
		else if(!isValidLength) {
			try {
				throw new ISBNMismatchException("Exception: The ISBN number must be 13 digits.");
			}
			catch(ISBNMismatchException e2) {
				addBookISBNExceptionPage.add(new JLabel(e2.getMessage()));
				addBookISBNExceptionPage.setSize(300,100);
				addBookISBNExceptionPage.setVisible(true);
			}
			finally {
				return false;
			}
		}
		else if(!isValidSum) {
			try {
				throw new ISBNMismatchException("Exception: Invalid ISBN-13 number - checksum failed.");
			}
			catch(ISBNMismatchException e3) {
				addBookISBNExceptionPage.add(new JLabel(e3.getMessage()));
				addBookISBNExceptionPage.setSize(300,100);
				addBookISBNExceptionPage.setVisible(true);
			}
			finally {
				return false;
			}
		}
		else {
			return true;
		}
	}
	
	
}

