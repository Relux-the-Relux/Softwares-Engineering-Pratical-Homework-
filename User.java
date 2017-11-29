public class User {

	String userName;
	Book[] lentBooks;
	Book[] orderedBooks;
	
	public User(String name) {
		
		this.userName = name;
		
	}
	
/**
 * \\TODO commentary
 * 
 * @param book
 */

	public void addBookToLendArray(Book book) {
		
		//TODO: throw exception if user already has 10 books?
		//not allowed to lend out more than 10
		//if(lentBooks.length == 10)
		
		Book[] newLentArray = new Book[lentBooks.length+1];
		
		for(int i = 0; i < lentBooks.length; i++) {
			newLentArray[i] = lentBooks[i];
		}
		newLentArray[lentBooks.length] = book;
		lentBooks = newLentArray;
	
		return;
	}	
	
	public void removeBookFromLendArray(Book book) {
		
		//if user has lent no books out, nothing needs to be done
		if (lentBooks.length == 0) return;
		
		//if user has lent one book out, replace with an empty array instead
		if(lentBooks.length == 1) {
			lentBooks = new Book[0];
			return;
		}
		
		//used later to signify position of required book in local array
		int positionOfBook = 0;
		
		//find the position of the book in the array of user's lent out books
		for (; positionOfBook < lentBooks.length; positionOfBook++) {
			
			if (book == lentBooks[positionOfBook]) break;
			
		}
		
		//new array to replace old one
		Book[]	newLentArray = new Book[lentBooks.length - 1];
		
		//copy books up to book to be removed, if any
		if (positionOfBook > 0) {
			for (int courser = 0; courser < positionOfBook; courser++) {
				newLentArray[courser] = lentBooks[courser];
			}
		}
		
		//copy books after the book to be removed, if any
		if (positionOfBook < lentBooks.length - 1) {
			for (int courser = positionOfBook + 1; courser < lentBooks.length; courser++) {
				newLentArray[courser - 1] = lentBooks[courser];
			}	
		}
		
	}
	
	
	public boolean searchOrderedBooks(Book book) {
		
		for (int courser = 0; courser < orderedBooks.length; courser++) {
			if (orderedBooks[courser] == book) return true;
		}
		
		return false;
		
	}

}
