
public class Book {

	
	public boolean available; //is the book available in the library?
	public final String BOOKNAME; 
	public final int ISBN;
	int timesLentOut; //taken out of sortiment after 50 times, and ordered again if  
	//ordered twice or more; 
	User lender; 
	int timesOrdered;
	
	//constructor
	public Book(String name, int isbn) {
		
		this.BOOKNAME = name;
		this.ISBN = isbn;
		this.available = true;
		
	}
	
	//function to lend out book
	public void lendOut(String user) {
	
		this.available = false;
		this.timesLentOut++;
		
	}	
	
	
	public void returnBook(User user) {
		
		if(!available) {
			
			if (this.timesLentOut >= 50) {
				//TODO delete from user's list of lent books
				//TODO buy book if ordered twice or more 
				if (timesOrdered >= 2) orderBook(user);
				
			}
			
		}
		
	}
	
	public void orderBook(User user) {
		
		//TODO check if user has ordered this book before
		this.timesOrdered++;
	}
}
