public class User {

	protected String userName;
	protected Book[] lentBooks; //a record of books the user has currently lent out, max 10
	protected Book[] orderedBooks; //a record of books the user has ordered
	
	//pointers to be used for building UserTree
	protected User rightChild;
	protected User middleChild;
	protected User leftChild;
	protected User parent;
	
	public User(String name) {
		
		userName = name;
		lentBooks = new Book[0];
		orderedBooks = new Book[0];
		
	}
	
	/**
	 * Adds book object in argument to the array of books this user has lent out. 
	 * Each user may only lend out up to 10 books. Used when a book is lent out by a user.
	 * 
	 * @param book book to be added to local user's array of lent out books
 	*/
	protected void addBookToLendArray(Book book) {
		//create new array with one additional unit
		Book[] newLentArray = new Book[lentBooks.length+1];
		
		//copy books from old array into new array
		for(int i = 0; i < lentBooks.length; i++) {
			newLentArray[i] = lentBooks[i];
		}
		
		//place book in argument into the new array
		newLentArray[lentBooks.length] = book;
		
		//make sure local array pointer points to new array
		lentBooks = newLentArray;
	
		return;
	}	
	
	/**
	 * Removes book object in argument from the array of books the user has lent out. 
	 * Used when a user returns a book.
	 * @param book book to be removed from array of user's lent out books
	 */
	protected void removeBookFromLendArray(Book book) {
		
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
	
	/**
	 * Adds book object in argument to the array of books this user has requested. 
	 * Used when a user requests a book they haven't requested before.
	 * 
	 * @param book book to be added to array of user's requested books
 	*/
	protected void addBookToOrderedArray(Book book) {
		//create new array with one additional unit
		Book[] newOrderedArray = new Book[orderedBooks.length+1];
		
		//copy books from old array into new array
		for(int i = 0; i < orderedBooks.length; i++) {
			newOrderedArray[i] = orderedBooks[i];
		}
				
		//place book in argument into the new array
		newOrderedArray[orderedBooks.length] = book;
		
		//make sure local array pointer points to new array
		orderedBooks = newOrderedArray;
	
		return;
	}
	
	/**
	 * Removes book object in argument from the array of books the user has requested. 
	 * Used when a requested book has been ordered by the library and is now available.
	 * @param book book to be removed from the array of user's requested books
	 */
	protected void removeBookFromOrderedArray(Book book) {
		
		//if user has requested no books , nothing needs to be done
		if (orderedBooks.length == 0) return;
		
		//if user has requested one book , replace with an empty array instead
		if(orderedBooks.length == 1) {
			orderedBooks = new Book[0];
			return;
		}
		
		//used later to signify position of required book in local array
		int positionOfBook = 0;
		
		//find the position of the book in the array of user's requested books
		for (; positionOfBook < orderedBooks.length; positionOfBook++) {
			
			if (book == orderedBooks[positionOfBook]) break;
			
		}
		
		//new array to replace old one
		Book[]	newOrderedArray = new Book[orderedBooks.length - 1];
		
		//copy books up to book to be removed, if any
		if (positionOfBook > 0) {
			for (int courser = 0; courser < positionOfBook; courser++) {
				newOrderedArray[courser] = orderedBooks[courser];
			}
		}
		
		//copy books after the book to be removed, if any
		if (positionOfBook < orderedBooks.length - 1) {
			for (int courser = positionOfBook + 1; courser < orderedBooks.length; courser++) {
				newOrderedArray[courser - 1] = orderedBooks[courser];
			}	
		}
		
	}
	
	/**
	 * Search the array that lists the books a user has requested for a specific book. Useful to avoid 
	 * the system interpreting multiple book requests by the same user as different requests.
	 * @param book has the user requested this book before?
	 * @return true if user has requested the book before, false if not
	 */
	protected boolean searchOrderedBooks(Book book) {
		
		//loop to search the array of requested books
		for (int courser = 0; courser < orderedBooks.length; courser++) {
			if (orderedBooks[courser] == book) return true; //if the book is in the array, return true
		}
		
		//only case left: book is not in the array, therefore user has never requested this book before
		return false;
		
	}

}
