package LibrarySystemWithGUI;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

enum MenuOption{
	
	ADDBOOK(1,"Add a new book."),
	ADDARTICLE(2,"Add a new online article."),
	CREATEACCOUNT(3,"Create a member account."),
	BOOKCHECKOUT(4, "Check out a book."),
	BOOKRETURN(5, "Return a book."),
	ARTICLEACCESS(6, "Give access to an online article."),
	ENDARTICLEACCESS(7, "End an online article access."),
	DISPLAYACCOUNTS(8, "Display all the accounts."),
	DISPLAYDATABASE(9, "Display library database."),
	MEMBERSWITHOVERDUEPAYMENTS(10, "Display members with overdue payments (in descending order)."),
	EXIT(11, "Exit.");
	
	int choicenumber;
	String choicedescription;
	
	MenuOption(int number, String description){
		this.choicenumber = number;
		this.choicedescription = description;
	}
	
	int getChoiceNumber(){
		return this.choicenumber;
	}
	
	String getChoiceDescription() {
		return this.choicedescription;
	}
}

public class Menu {
	
	/* static public Scanner scanner = new Scanner(System.in);   old code
	
	void menu() {
		System.out.println("Library Management System Menu");
		for(MenuOption menuoptions : MenuOption.values()) {
			System.out.println(menuoptions.getChoiceNumber() + " - " + menuoptions.getChoiceDescription());
		}
		System.out.println("Enter your choice please:");
		int choice = scanner.nextInt();
		scanner.nextLine();
		if(choice == MenuOption.ADDBOOK.choicenumber) {
			Book.addBook();
			menu();
		}
		else if(choice == MenuOption.ADDARTICLE.choicenumber) {
			OnlineArticle.addArticle();
			menu();
		}
		else if(choice == MenuOption.CREATEACCOUNT.choicenumber) {
			System.out.println("1. Regular Member");
			System.out.println("2. Student Member");
			System.out.println("3. Academic Member");
			System.out.println("Please enter the type of member you would like to create: ");
			int memberTypeChoice = scanner.nextInt();
			if(memberTypeChoice == 1) {
				RegularMember.createAccount();
				menu();
			}
			else if(memberTypeChoice == 2) {
				Student.createAccount();
				menu();
			}
			else if(memberTypeChoice == 3) {
				Academic.createAccount();
				menu();
			}
		}
		else if(choice == MenuOption.BOOKCHECKOUT.choicenumber) {
			Book.bookCheckout();
			menu();
		}
		else if(choice == MenuOption.BOOKRETURN.choicenumber) {
			Book.bookReturn();
			menu();
		}
		else if(choice == MenuOption.ARTICLEACCESS.choicenumber) {
			OnlineArticle.articleAccess();
			menu();
		}
		else if(choice == MenuOption.ENDARTICLEACCESS.choicenumber) {
			OnlineArticle.endArticleAccess();
			menu();
		}
		else if(choice == MenuOption.DISPLAYACCOUNTS.choicenumber) {
			RegularMember.displayAllAccounts();
			menu();
		}
		else if(choice == MenuOption.DISPLAYDATABASE.choicenumber) {
			Book.displayBookDatabase();
			OnlineArticle.displayOnlineArticleDatabase();
			menu();
		}
		else if(choice == MenuOption.MEMBERSWITHOVERDUEPAYMENTS.choicenumber) {
			RegularMember.displayOverduePaymentsDescending();
			menu();
		}
		else if(choice == MenuOption.EXIT.choicenumber) {
			System.out.println("Goodbye.");
			System.exit(1);
		}
		else {
			System.out.println("Please select a valid option from menu (1-11).");
			menu();
		}
	}  */
	
	
	JOptionPane notSelected = new JOptionPane("Please select an option to continue with and than click OK.");
	JDialog notSelectedDialog = notSelected.createDialog("Error");
	
	JFrame displayDatabaseFrame = new JFrame("Display Database");
	
	public void menu() {
		JFrame menu = new JFrame("Library Management System");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(400,430);
		menu.setLayout(new BorderLayout());
		String[] menuOptionsArray = new String[11];
		int menuAddingIndex = 0;
		for(MenuOption menuoptions : MenuOption.values()) {
			menuOptionsArray[menuAddingIndex] = menuoptions.getChoiceDescription();
			menuAddingIndex++;
		}
		JList<String> menuOptionsList = new JList<String>(menuOptionsArray);
		menu.add(menuOptionsList);
		Button okButton = new Button("OK");
		menu.add(okButton, BorderLayout.SOUTH);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(menuOptionsList.getSelectedValue() == null) {
					notSelectedDialog.setVisible(true);
				}
				else if(menuOptionsList.getSelectedValue().equals(MenuOption.ADDBOOK.choicedescription)) {
					Book.addBook();
				}
				else if(menuOptionsList.getSelectedValue().equals(MenuOption.ADDARTICLE.choicedescription)) {
					OnlineArticle.addArticle();
				}
				else if(menuOptionsList.getSelectedValue().equals(MenuOption.CREATEACCOUNT.choicedescription)) {
					RegularMember.createAccount();
				}
				else if(menuOptionsList.getSelectedValue().equals(MenuOption.BOOKCHECKOUT.choicedescription)) {
					Book.bookCheckout();
				}
				else if(menuOptionsList.getSelectedValue().equals(MenuOption.BOOKRETURN.choicedescription)) {
					Book.bookReturn();
				}
				else if(menuOptionsList.getSelectedValue().equals(MenuOption.ARTICLEACCESS.choicedescription)) {
					OnlineArticle.articleAccess();
				}
				else if(menuOptionsList.getSelectedValue().equals(MenuOption.ENDARTICLEACCESS.choicedescription)) {
					OnlineArticle.endArticleAccess();
				}
				else if(menuOptionsList.getSelectedValue().equals(MenuOption.DISPLAYACCOUNTS.choicedescription)) {
					RegularMember.displayAllAccounts();
				}
				else if(menuOptionsList.getSelectedValue().equals(MenuOption.DISPLAYDATABASE.choicedescription)) {
					String[] infosToAddToList = new String[Book.bookCount + OnlineArticle.articleCount];
					int index1 = 0;
					int index2 = 0;
					int index3 = 0;
					while(index1 < Book.bookCount) {
						infosToAddToList[index1] = Book.displayBookDatabase()[index2];
						index1++;
						index2++;
					}
					while(index1 < Book.bookCount + OnlineArticle.articleCount) {
						infosToAddToList[index1] = OnlineArticle.displayOnlineArticleDatabase()[index3];
						index1++;
						index3++;
					}
					JList<String> displayDatabaseList = new JList<String>(infosToAddToList);
					displayDatabaseFrame.setSize(500,300);
					displayDatabaseFrame.setLayout(new BorderLayout());
					displayDatabaseFrame.add(displayDatabaseList, BorderLayout.NORTH);
					Button closeButton = new Button("Close");
					closeButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							displayDatabaseFrame.setVisible(false);
						}
					});
					displayDatabaseFrame.add(closeButton, BorderLayout.SOUTH);
					displayDatabaseFrame.setVisible(true);
				}
				else if(menuOptionsList.getSelectedValue().equals(MenuOption.MEMBERSWITHOVERDUEPAYMENTS.choicedescription)) {
					RegularMember.displayOverduePaymentsDescending();
				}
				else if(menuOptionsList.getSelectedValue().equals(MenuOption.EXIT.choicedescription)) {
					System.exit(1);
				}
			}
		});
		
		menu.setVisible(true);
		
	}
	
}
