
public class Book {

	
	
	public final String BOOKNAME; 
	public final String AUTHOR;
	public final int ISBN;
	
	protected boolean available; //is the book available in the library?
	protected boolean onceAvailable; // if the book was vailable at least once before
	protected int timesLentOut; //taken out of assortment after 50 times, and ordered again if  
	//					ordered twice or more; 
	protected User lender; 
	protected int timesOrderedByUsers = 0; //changed name for clarity
	
	//pointers for implementing binary tree to represent library assortment
	protected Book parent;
	protected Book rightChild;
	protected Book leftChild;
	
	//constructor
	protected Book(String name, int isbn, String author, boolean Available) {
		
		this.BOOKNAME = name;
		this.ISBN = isbn;
		this.AUTHOR = author;
		
		this.available = Available;
		this.onceAvailable = Available;
		this.lender = null;
		this.timesLentOut = 0;
		this.timesOrderedByUsers = 0;
		
	}
	
	/**
	 * Functions to lend out books.
	 * It sets the book as unavailable and increases the number of times it was lent out.
	 * 
	 */
	protected void lendOut() {
		
		this.available = false;
		this.timesLentOut++;
		
		return;
	}	
	
	/**
	 * Return a book to the library. The book is made unavailable by the library if it has been lent out 
	 * 50 times or more and then ordered if it has been requested twice or more.
	 */
	protected void returnBook() {
		//if this book has been lent out 50 times or more, either:
		if (this.timesLentOut >= 50) {
			//order a new copy if it has been requested twice or more already
			if (timesOrderedByUsers >= 2) {
				orderBook();
				return;
			}
			
			//or simply remove it from the active book assortment
			available = false;
			return;
			
		}
		
		//otherwise book is now in the library and available
		this.available = true;
		
	}
	
	/* this function should probably be used for actually ordering a book, whereas 
	 * we wanted to implement a user asking for a book to be ordered
	 * therefore: move functionality to function bookOrderRequest 
	 * and change local int timesOrdered to timesOrderedByUsers
	 */
	private void orderBook() {
		
		this.available = true;
		this.onceAvailable = true;
		this.timesLentOut = 0; 
		this.timesOrderedByUsers = 0;
		
	}
	
	protected void bookOrderRequest () {
		
		this.timesOrderedByUsers++;
		
		if(this.timesOrderedByUsers >= 5||(this.onceAvailable && this.timesOrderedByUsers >= 5)) {
			if(this.available == false) {
				orderBook();
			}
		}
		
		return;
		
	}
}
