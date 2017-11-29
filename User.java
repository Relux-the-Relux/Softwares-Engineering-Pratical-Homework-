public class User {

	String userName;
	Book[] lentBooks;
	Book[] orderedBooks;
	
	public User(String name) {
		
		this.userName = name;
		lentBooks = new Book[0];
		orderedBooks = new Book[0];
		
	}
	/**
	 * \\TODO commentary
	 * 
	 * @param book
	 */
	
	public void addBookToLendArray(Book book) {
		//TODO: throw exception if user already has 10 books?
		//not allowed to lend out more than 10
		//if(lentBooks.length == 10) ...
		
		Book[] newLentArray = new Book[lentBooks.length+1];
		
		for(int i = 0; i < lentBooks.length; i++) {
			newLentArray[i] = lentBooks[i];
		}
		newLentArray[lentBooks.length] = book;
		lentBooks = newLentArray;
		
		return;
	}
	
	/* public boolean searchOrderedBooks(Book bookTitle) {
	 * 
	 *
	 * 
	 */
}
