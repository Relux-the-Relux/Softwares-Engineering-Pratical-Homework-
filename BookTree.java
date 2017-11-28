
public class BookTree {

	Book root;
	
	public BookTree() {
		root = null;
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
	 * This function searches the tree for a book using its ISBN number.
	 * 
	 * @param isbn the ISBN number of the book
	 * 
	 * @return null if the book is not in the database.
	 * 			the pointer to the book object if it is.
	 */
	
	public Book findBook(int isbn){
	
		Book searchBook = root;
		
		while(searchBook != null && searchBook.ISBN != isbn) {
			if(searchBook.ISBN < isbn) {
				searchBook = searchBook.leftChild;
			} else {
				searchBook = searchBook.rightChild;
			}
		}
		
		return searchBook;
	}
	
	/**
	 * this function adds  new book to the tree as a leaf.
	 * 
	 * @param book book to be added to the tree.
	 */
	public void addBook(Book book) {
		
		if (root == null) {
			addRoot(book);
			return;
		}
		
		Book searchBook = root;
		
		while(searchBook.ISBN != book.ISBN) {
			
			if(searchBook.ISBN < book.ISBN) {
				if(searchBook.leftChild == null){
					searchBook.leftChild = book;
					book.parent = searchBook;
					return;
				} else {
					searchBook.leftChild = book;
				}
				
			} else {
				if(searchBook.rightChild == null){
					searchBook.rightChild = book;
					book.parent = searchBook;
					return;
				} else {
					searchBook.rightChild = book;
				}
				
			}
		}
		
		return;
	}
}
