package LibrarySystemWithGUI;

public abstract class LibraryMaterial implements LibraryData {
	
	double cost;
	double overdueFee = 0.00;
	
	abstract double getCost();
	
	abstract void setCost(double cost);

}
