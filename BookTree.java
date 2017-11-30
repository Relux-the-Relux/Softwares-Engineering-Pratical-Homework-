
/**
 * Binary tree to organized the books within.
 * ordered by the books' ISBN numbers. Lower numbers in the left subtree and higher in the right.
 * 
 */

public class BookTree {

	protected Book root;
	final public UserTree userTree;
	
	/**
	 * constructor for the tree if empty
	 */
	public BookTree() {
		root = null;
		userTree = new UserTree();
	}
	/**
	 * constructor if the tree is to have elements when initialized.
	 * The position of names in the Arrays and isbns should match.
	 * behavior is not defined is arrays of mismatched length are incorrectly given.
	 * 
	 * @param bookNames
	 * @param isbns
	 * @param authorNames
	 */
	public BookTree(String[] bookNames, int[] isbns, String[] authorNames) {
		
		for(int i = 0; i < isbns.length; i++) {
			addNewBook(new Book(bookNames[i], isbns[i], authorNames[i], true));
		}
		userTree = new UserTree();
	}
	
	
	/**
	 * used to add a root to a empty tree. 
	 * 
	 * @param newRoot new root of the tree
	 */
	private void addRoot(Book newRoot) {
		
		this.root = newRoot;
		newRoot.parent = null;
		newRoot.rightChild = null;
		newRoot.leftChild = null;
		return;
	}
	
	/**
	 * Searches for a book by its ISB (International Standard Book) number in the tree saved in RAM
	 * and returns the book if found and null otherwise. 
	 * @param ISBN the International Standard Book Number of the book to be looked up, saved as an integer
	 * @return book as an instance of the class Book, if found; null otherwise
	 */
	public Book searchForBook(int ISBN) {
		
		if(this.root == null) {
			return null;
		}
		
		//pointer used to course through the tree; lands on required book if the book is in the tree
		Book courserPointer = this.root;
		
		//loop to course through the tree
		while(courserPointer != null) {
			
			//if entered isbn is larger than isbn of current Book instance, go right in the tree
			if (courserPointer.ISBN < ISBN) {
				courserPointer = courserPointer.rightChild;
			}
			
			//if entered isbn is smaller than isbn of current Book instance, go left in the tree
			else if (courserPointer.ISBN > ISBN) {
				courserPointer = courserPointer.leftChild;
			}
			
			//the only option left is that the required book has been found
			else return courserPointer;
		}
		
		//return a null pointer if book has not been found
		return courserPointer;
		
	}
	
	/**
	 * this function adds  new book to the tree as a leaf.
	 * 
	 * @param book book to be added to the tree.
	 */
	private void addNewBook(Book newBook) {
		
		/* check if tree has been built yet 
		 * TODO: Nikolay: I put this in because the assumption was that we only need to implement
		 * the data structure used to organise the Book objects in RAM, so one function can technically 
		 * account for both new books being added to the assortment and books being loaded into the RAM
		 * from a persistent data carrier (data bank, hard drive etc.) 
		 */
		if (this.root == null) {
			addRoot(newBook);
			return;
		}
		
		int isbNum = newBook.ISBN;
		
		Book courserPointer = this.root; //used to traverse the tree
		Book inserter = null; //used as point of reference to add the Book onto the tree
		
		while(courserPointer != null) {
			
			inserter = courserPointer;
			
			//if entered isbn is bigger than isbn of current Book instance, go right in the tree
			if(courserPointer.ISBN < isbNum) {
				courserPointer = courserPointer.rightChild;
			}
			
			//if entered isbn is smaller than isbn of current Book instance, go left in the tree
			else if (courserPointer.ISBN > isbNum) {
				courserPointer = courserPointer.leftChild;
			}
			
		}
		
		if (inserter.ISBN > isbNum) {
			inserter.leftChild = newBook;
			newBook.parent = inserter;
		}
		else {
			inserter.rightChild = newBook;
			newBook.parent = inserter;
		}
		
	}
	/**
	 * Function used to lend books out.
	 * Finds the book and user and then sets the book to unavailible and adds to the list of lent books of
	 * the user. Also, adds the book to the list of books lent out by the user.
	 * 
	 * @param ISBN ISBN of the book
	 * @param userName name of the user as string
	 * 
	 * @return return true if successful. returns false if book is not available, user does not exist
	 * 			or user has already lent 10 books.
	 */
	
	public boolean lendOutBook(int ISBN, String userName){
		
		Book book = searchForBook(ISBN);
		
		//if the book is not in the database, or is unavailable
		if (book == null || book.available == false) {
			return false;
		}
		
		User user = userTree.findUser(userName);
		
		//if the user is not in the database, or lentout 10 books already.
		if (user == null || user.lentBooks.length >= 10) {
			return false;
		}
		
		book.lendOut();
		user.addBookToLendArray(book);
		
		return true;
	}
	
	/**
	 * Function for returning books from the user to the library.
	 * 
	 * @param ISBN isbn of the book as an integer.
	 * @param userName userName name of the user as string
	 * 
	 * @return returns true if successful. returns false if book is already available, user does not exist
	 * 			or user has has not lent the book
	 */
	
	public boolean returnBook(int ISBN, String userName) {
		
		Book book = searchForBook(ISBN);
		
		//if the book is not in the database, or is unavailable
		if (book == null || book.available == true) {
			return false;
		}
		
		User user = userTree.findUser(userName);
		
		//if the user is not in the database or has not lent the book
		if (user == null || user.searchLentBooks(book) == false) {
			return false;
		}
		
		book.returnBook();
		
		//remove book from user array.
		user.removeBookFromLendArray(book);
		
		return true;
	}
	
	/**
	 * Function for requesting books.
	 * Increases the counter of how many times the book was ordered and adds it to the array
	 * of books ordered by the user.
	 * In case the book is not yet in the database it is added to it and marked as unavailable.
	 * 
	 * @param bookName
	 * @param ISBN
	 * @param author
	 * @param userName
	 */
	public void bookRequest(String bookName, int ISBN, String author, String userName) {
		
		Book book = searchForBook(ISBN);
		
		//if the book is not in the database
		if (book == null) {
			book = new Book(bookName, ISBN, author, false);
			this.addNewBook(book);
		}
		
		User user = userTree.findUser(userName);
		
		//if user has not been registered yet or has requested the book before
		if (user == null || user.searchOrderedBooks(book) == true) {
			//TODO what now? simply return?
			return;
		}
		
		//save a request for this book
		book.bookOrderRequest();
		
		//add book to the array of ordered book by the user
		user.addBookToOrderedArray(book);
		
		return;
		
	}
	
	
}
