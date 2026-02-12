package LibrarySystemWithGUI;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnlineArticle extends LibraryMaterial {
	String nameOfArticle;
	String DOI;
	static int articleCount;
	String publisher;
	Date accessDate;
	Date dueDate;
	
	static ArrayList<OnlineArticle> articleArray = new ArrayList<>();
	
	static public Scanner scanner = new Scanner(System.in);
	
	static JFrame addArticleMenu = new JFrame("Add a new article.");
	static JDialog successfullyAddedArticle = new JDialog();
	
	static JFrame articleAccessMenu = new JFrame("Give access to an article.");
	static JDialog successfullyAccessedArticle = new JDialog();
	
	static JFrame endArticleAccessMenu = new JFrame("Return a book.");
	static JDialog successfullyEndedArticleAccess = new JDialog();
	
	OnlineArticle(){
		
	}
	
	OnlineArticle(String name, String DoI, String nameOfPublisher){
		this.nameOfArticle = name;
		this.DOI = DoI;
		this.accessDate = new Date(0, 0, 0);
		this.dueDate = new Date(0, 0, 0);
		this.publisher = nameOfPublisher;
		if(this.publisher.equals("ACM")) {
			this.setCost(150.0);
		}
		else if(this.publisher.equals("IEEE")) {
			this.setCost(200.0);
		}
		else {
			this.setCost(100.0);
		}
	}
	
	
	static void addArticle() {
		/*System.out.println("Enter article name please: ");
		String articleName = scanner.nextLine();
		System.out.println("Enter DOI please: ");
		String doi = scanner.nextLine();
		System.out.println("Enter the name of the online article publisher please: ");
		String publisherName = scanner.nextLine();
		articleArray.add(new OnlineArticle(articleName, doi, publisherName));
		articleCount++;
		System.out.println("A new article is added to the library with name " + articleName + " and DOI# " + doi + ".");
		System.out.println("Total number of online articles: " + articleCount);*/
		
		
		addArticleMenu.setSize(280,230);
		addArticleMenu.setLayout(new GridLayout(4,2));
		addArticleMenu.add(new JLabel("Article Name:"));
		JTextField addArticleNameField = new JTextField();
		addArticleMenu.add(addArticleNameField);
		addArticleMenu.add(new JLabel("DOI:"));
		JTextField addArticleDOIField = new JTextField();
		addArticleMenu.add(addArticleDOIField);
		addArticleMenu.add(new JLabel("Publisher Name:"));
		JTextField addArticlePublisherNameField = new JTextField();
		addArticleMenu.add(addArticlePublisherNameField);
		addArticleMenu.add(new JLabel(""));
		Button addArticleSubmitButton = new Button("Submit");
		addArticleSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String articleName = addArticleNameField.getText();
				String doi = addArticleDOIField.getText();
				String publisherName = addArticlePublisherNameField.getText();
				articleArray.add(new OnlineArticle(articleName, doi, publisherName));
				articleCount++;
				successfullyAddedArticle.setSize(900, 100);
				successfullyAddedArticle.add(new JLabel("A new article is added to the library with name " + articleName + " and DOI# " + doi + ".\n There are a total of " + articleCount + " online article(s) in the library.\n You can close the tab now."));
				successfullyAddedArticle.setVisible(true);
				addArticleMenu.setVisible(false);
			}
		});
		addArticleMenu.add(addArticleSubmitButton);
		addArticleMenu.setVisible(true);
	}
	
	static void articleAccess() {
		/*System.out.println("Enter the ID of the member please: ");
		int IDofMember = scanner.nextInt();
		scanner.nextLine();
		Iterator<RegularMember> memberIterator = RegularMember.memberArray.iterator();
		while(memberIterator.hasNext()) {
			RegularMember memberToGiveAccessTo = memberIterator.next();
			if(memberToGiveAccessTo.memberID == IDofMember) {
				Iterator<OnlineArticle> articleIterator = OnlineArticle.articleArray.iterator();
				System.out.println("Enter the DOI of the article please: ");
				String takenDOI = scanner.nextLine();
				while(articleIterator.hasNext()) {
					OnlineArticle articleToGetAccessTo = articleIterator.next();
					if(articleToGetAccessTo.DOI.equals(takenDOI)) {
						if(memberToGiveAccessTo.accessableOnlineArticleCount < memberToGiveAccessTo.onlineArticleQuota) {
							System.out.println("Enter a due year (YYYY) please: "); // In the instructions we get the overdue fee according to the access date
							int dueYear = scanner.nextInt(); //                        but I did this with due date because it was how it was done in the walkthrough
							scanner.nextLine();
							System.out.println("Enter a due month (MM) please: ");
							int dueMonth = scanner.nextInt();
							scanner.nextLine();
							System.out.println("Enter a due day (DD) please: ");
							int dueDay = scanner.nextInt();
							scanner.nextLine();
							Date dueDateToSet = Date.dateValidator(dueDay, dueMonth, dueYear);
							articleToGetAccessTo.dueDate.setDate(dueDateToSet.day, dueDateToSet.month, dueDateToSet.year);
							memberToGiveAccessTo.setAccessedArticle(articleToGetAccessTo);
							System.out.println("The online article entitled " + articleToGetAccessTo.nameOfArticle + " (DOI: " + articleToGetAccessTo.DOI + ") is accessed by the user " + memberToGiveAccessTo.memberName + ".");
							Menu menu = new Menu();
							menu.menu();
						}
						else{
							System.out.println("The user's accessable online article quota is full.");
							Menu menu = new Menu();
							menu.menu();
						}
					}
				}
				System.out.println("There is no article with the given DOI number.");
				Menu menu = new Menu();
				menu.menu();
			}
		}
		System.out.println("There is no account with the given ID number.");
		Menu menu = new Menu();
		menu.menu();*/
		
		articleAccessMenu.setLayout(new GridLayout(6,2));
		articleAccessMenu.setSize(300,200);
		articleAccessMenu.add(new JLabel("Member ID:"));
		JTextField memberIDField = new JTextField();
		articleAccessMenu.add(memberIDField);
		articleAccessMenu.add(new JLabel("Article DOI:"));
		JTextField articleDOIField = new JTextField();
		articleAccessMenu.add(articleDOIField);
		articleAccessMenu.add(new JLabel("Due Year (YYYY):"));
		JTextField dueYearField = new JTextField();
		articleAccessMenu.add(dueYearField);
		articleAccessMenu.add(new JLabel("Due Month (MM):"));
		JTextField dueMonthField = new JTextField();
		articleAccessMenu.add(dueMonthField);
		articleAccessMenu.add(new JLabel("Due Day (DD):"));
		JTextField dueDayField = new JTextField();
		articleAccessMenu.add(dueDayField);
		articleAccessMenu.add(new JLabel(""));
		Button submitButton = new Button("Submit");
		articleAccessMenu.add(submitButton);
		
		successfullyAccessedArticle.setSize(1000,100);
		successfullyAccessedArticle.setLayout(new FlowLayout());
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int memberID = Integer.parseInt(memberIDField.getText());
				String articleDOI = articleDOIField.getText();
				int dueYear = Integer.parseInt(dueYearField.getText());
				int dueMonth = Integer.parseInt(dueMonthField.getText());
				int dueDay = Integer.parseInt(dueDayField.getText());
				
				Iterator<RegularMember> memberIterator = RegularMember.memberArray.iterator();
				while(memberIterator.hasNext()) {
					RegularMember memberToGiveAccessTo = memberIterator.next();
					if(memberToGiveAccessTo.memberID == memberID) {
						Iterator<OnlineArticle> articleIterator = OnlineArticle.articleArray.iterator();
						while(articleIterator.hasNext()) {
							OnlineArticle articleToGetAccessTo = articleIterator.next();
							if(articleToGetAccessTo.DOI.equals(articleDOI)) {
								if(memberToGiveAccessTo.accessableOnlineArticleCount < memberToGiveAccessTo.onlineArticleQuota) {
									Date dueDateToSet = Date.dateValidator(dueDay, dueMonth, dueYear);
									articleToGetAccessTo.dueDate.setDate(dueDateToSet.day, dueDateToSet.month, dueDateToSet.year);
									memberToGiveAccessTo.setAccessedArticle(articleToGetAccessTo);
									successfullyAccessedArticle.add(new JLabel("The online article entitled " + articleToGetAccessTo.nameOfArticle + " (DOI: " + articleToGetAccessTo.DOI + ") is accessed by the user " + memberToGiveAccessTo.memberName + ".\n You can close the tab now."));
									successfullyAccessedArticle.setVisible(true);
									articleAccessMenu.setVisible(false);
								}
								else {
									successfullyAccessedArticle.add(new JLabel("The user's accessable online article quota is full."));
									successfullyAccessedArticle.setVisible(true);
								}
							}
						}
						successfullyAccessedArticle.add(new JLabel("There is no article with the given DOI number."));
						successfullyAccessedArticle.setVisible(true);
					}
				}
				successfullyAccessedArticle.add(new JLabel("There is no account with the given ID number."));
				successfullyAccessedArticle.setVisible(true);
			}
		});
		
		articleAccessMenu.setVisible(true);
	}
	
	static void endArticleAccess() {
		/*System.out.println("Enter the ID of the member please: ");
		int IDofMember = scanner.nextInt();
		scanner.nextLine();
		Iterator<RegularMember> memberIterator = RegularMember.memberArray.iterator();
		while(memberIterator.hasNext()) {
			RegularMember memberToEndTheAccessOf = memberIterator.next();
			if(memberToEndTheAccessOf.memberID == IDofMember) {
				System.out.println("Enter the DOI of the online article you want to end the access to please: ");
				String accessEndedOnlineArticleDOI = scanner.nextLine();
				Iterator<OnlineArticle> OnlineArticleToEndTheAccessToIterator = memberToEndTheAccessOf.getAccessableOnlineArticles().iterator();
				while(OnlineArticleToEndTheAccessToIterator.hasNext()) {
					OnlineArticle OnlineArticleToEndTheAccessTo = OnlineArticleToEndTheAccessToIterator.next();
					if(OnlineArticleToEndTheAccessTo.DOI.equals(accessEndedOnlineArticleDOI)) {
						memberToEndTheAccessOf.removeAccessedOnlineArticle(OnlineArticleToEndTheAccessTo);
						System.out.println("The online article entitled " + OnlineArticleToEndTheAccessTo.nameOfArticle + " (DOI: " + OnlineArticleToEndTheAccessTo.DOI + ") is removed from the user " + memberToEndTheAccessOf.memberName + "'s access list.");
						Menu menu = new Menu();
						menu.menu();
					}
				}
				System.out.println("The member does not have access to any online article with the given DOI.");
				Menu menu = new Menu();
				menu.menu();
			}
		}
		System.out.println("There is no account with the given ID.");
		Menu menu = new Menu();
		menu.menu();*/
		
		endArticleAccessMenu.setSize(250,150);
		endArticleAccessMenu.setLayout(new GridLayout(3,2));
		endArticleAccessMenu.add(new JLabel("Member ID:"));
		JTextField memberIDField = new JTextField();
		endArticleAccessMenu.add(memberIDField);
		endArticleAccessMenu.add(new JLabel("Article DOI:"));
		JTextField articleDOIField = new JTextField();
		endArticleAccessMenu.add(articleDOIField);
		endArticleAccessMenu.add(new JLabel(""));
		Button submitButton = new Button("Submit");
		endArticleAccessMenu.add(submitButton);
		successfullyEndedArticleAccess.setSize(1000,100);
		successfullyEndedArticleAccess.setLayout(new FlowLayout());
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int memberID = Integer.parseInt(memberIDField.getText());
				String articleDOI = articleDOIField.getText();
				Iterator<RegularMember> memberIterator = RegularMember.memberArray.iterator();
				while(memberIterator.hasNext()) {
					RegularMember memberToEndTheAccessOf = memberIterator.next();
					if(memberToEndTheAccessOf.memberID == memberID) {
						Iterator<OnlineArticle> OnlineArticleToEndTheAccessToIterator = memberToEndTheAccessOf.getAccessableOnlineArticles().iterator();
						while(OnlineArticleToEndTheAccessToIterator.hasNext()) {
							OnlineArticle OnlineArticleToEndTheAccessTo = OnlineArticleToEndTheAccessToIterator.next();
							if(OnlineArticleToEndTheAccessTo.DOI.equals(articleDOI)) {
								memberToEndTheAccessOf.removeAccessedOnlineArticle(OnlineArticleToEndTheAccessTo);
								successfullyEndedArticleAccess.add(new JLabel("The online article entitled " + OnlineArticleToEndTheAccessTo.nameOfArticle + " (DOI: " + OnlineArticleToEndTheAccessTo.DOI + ") is removed from the user " + memberToEndTheAccessOf.memberName + "'s access list.\n You can close the tab now."));
								successfullyEndedArticleAccess.setVisible(true);
								endArticleAccessMenu.setVisible(false);
							}
						}
						successfullyEndedArticleAccess.add(new JLabel("The member does not have access to any online article with the given DOI."));
						successfullyEndedArticleAccess.setVisible(true);
					}
				}
				successfullyEndedArticleAccess.add(new JLabel("There is no account with the given ID."));
				successfullyEndedArticleAccess.setVisible(true);
			}
		});
		endArticleAccessMenu.setVisible(true);
	}
	
	public double calculateCost() {
		if(this.publisher.equals("ACM")) {
			this.setCost(150.0);
			return 150.0;
		}
		else if(this.publisher.equals("IEEE")) {
			this.setCost(200.0);
			return 200.0;
		}
		else {
			this.setCost(100.0);
			return 100.0;
		}
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	static String[] displayOnlineArticleDatabase() {
		/*Iterator<OnlineArticle> onlineArticleDisplayIterator = articleArray.iterator();
		while(onlineArticleDisplayIterator.hasNext()) {
			OnlineArticle onlineArticleToDisplay = onlineArticleDisplayIterator.next();
			System.out.println("Online Article Name: " + onlineArticleToDisplay.nameOfArticle);
			System.out.println("Online Article DOI: " + onlineArticleToDisplay.DOI);
			System.out.println("The cost of the online article is " + onlineArticleToDisplay.cost + ".");
			System.out.println(" ");
		}*/
		
		String[] arrayToReturnArticle = new String[articleCount];
		Iterator<OnlineArticle> articleToAddToArrayIterator = articleArray.iterator();
		int index = 0;
		while(articleToAddToArrayIterator.hasNext()) {
			OnlineArticle articleToAddToArray = articleToAddToArrayIterator.next();
			arrayToReturnArticle[index] = "Article Name: " + articleToAddToArray.nameOfArticle + "\n DOI Number: " + articleToAddToArray.DOI + "\n The cost is " + articleToAddToArray.cost + ".";
			index++;
		}
		
		return arrayToReturnArticle;
	}
	
}
