public class User {

	String userName;
	Book[] lentBooks;
	Book[] orderedBooks;
	User parent;
	User rightChild;
	User middleChild;
	User leftChild;
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
	
	public void addBookToLendArry(Book book) {
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
