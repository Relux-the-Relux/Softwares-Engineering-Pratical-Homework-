
public class Book {

	
	public boolean available; //is the book available in the library?
	public final String BOOKNAME; 
	public final String AUTHOR;
	public final int ISBN;
	int timesLentOut; //taken out of assortment after 50 times, and ordered again if  
	//					ordered twice or more; 
	User lender; 
	int timesOrderedByUsers = 0; //changed name for clarity
	
	//pointers for implementing binary tree to represent library assortment
	Book parent;
	Book rightChild;
	Book leftChild;
	
	//constructor
	public Book(String name, int isbn, String author) {
		
		this.BOOKNAME = name;
		this.ISBN = isbn;
		this.AUTHOR = author;
		this.available = true;
		
	}
	
	public void setParent(Book futureParent) {
		this.parent = futureParent;
	}
	
	public void setRightChild (Book rightChild) {
		this.rightChild = rightChild;
	}
	
	public void setLeftChild (Book leftChild) {
		this.leftChild = leftChild;
	}
	
	//function to lend out book
	public void lendOut(User user) {
	
		
		this.available = false;
		this.timesLentOut++;
		//TODO: add book to user's list of lent books
	}	
	
	
	public void returnBook(User user) {
		
		if(!available) {
			
			//TODO delete from user's list of lent books
			
			if (this.timesLentOut >= 50) {
				
				//TODO buy book if ordered twice or more 
				if (timesOrderedByUsers >= 2) orderBook();
				
			}
			
			//what is to be done elsewise?
			
		}
		
	}
	
	/* this function should probably be used for actually ordering a book, whereas 
	 * we wanted to implement a user asking for a book to be ordered
	 * therefore: move functionality to function bookOrderRequest 
	 * and change local int timesOrdered to timesOrderedByUsers
	 */
	public void orderBook() {
		
		this.available = true;
		//guidelines in course say we should make functions as simple/granular as possible
		//is it worth it to create functions solely for resetting these next two counters?
		this.timesLentOut = 0; 
		this.timesOrderedByUsers = 0;
		
	}
	
	public void bookOrderRequest (User user) {
		
		//TODO: implement boolean function that searches user's array of ordered books?
		
		
	}
}
