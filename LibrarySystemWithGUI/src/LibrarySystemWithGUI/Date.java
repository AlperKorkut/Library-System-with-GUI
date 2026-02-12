package LibrarySystemWithGUI;
import java.util.Scanner;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.DateTimeException;

public class Date {
	int day;
	int month;
	int year;
	
	static public Scanner scanner = new Scanner(System.in);
	
	Date(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	
	void setDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public String toString() {
		return day + "/" + month + "/" + year;
	}
	
	static Date dateValidator(int day, int month, int year) {
		Date dateToCheck = new Date(day, month, year);
		boolean isValidFromTheStart = true;
		int newDay = 0;
		int newMonth = 0;
		int newYear = 0;
		try {
			LocalDate.of(year, month, day);
		}
		catch(DateTimeException e1){
			try {
				throw new NotValidDateException("Exception: Invalid Date: " + dateToCheck + ".");
			}
			catch(NotValidDateException e2) {
				System.out.println(e2.getMessage());
				System.out.println("Enter a due year (YYYY) please: ");
				newYear = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter a due month (MM) please:");
				newMonth = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter a due day (DD) please: ");
				newDay = scanner.nextInt();
				scanner.nextLine();
				isValidFromTheStart = false;
				dateValidator(newDay, newMonth, newYear);
			}
		}
		finally {
			if(isValidFromTheStart) {
				return new Date(day, month, year);
			}
			else {
				return new Date(newDay, newMonth, newYear);
			}
		}
	}
	
}
