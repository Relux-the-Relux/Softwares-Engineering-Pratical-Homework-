
public class BookTree {

	//TODO: create a tree to organise books in RAM
	Book root;
	
	public BookTree(Book futureRoot) {
		
		this.root = futureRoot;
		
	}
	
	
	public void addNewBook(Book newBook) {
		
		/* check if tree has been built yet 
		 * TODO: Nikolay: I put this in because the assumption was that we only need to implement
		 * the data structure used to organise the Book objects in RAM, so one function can technically 
		 * account for both new books being added to the assortment and books being loaded into the RAM
		 * from a persistent data carrier (data bank, hard drive etc.) 
		 */
		if (this.root == null) {
			this.root = newBook;
			return;
		}
		
		
		int isbNum = newBook.ISBN;
		
		Book courserPointer = this.root, inserter = null;
		
		while(courserPointer != null) {
			
			inserter = courserPointer;
			
			if(courserPointer.ISBN < isbNum) {
				courserPointer = courserPointer.rightChild;
			}
			
			//if entered isbn is smaller than isbn of current Book instance, go left in the tree
			else if (courserPointer.ISBN > isbNum) {
				courserPointer = courserPointer.leftChild;
			}
			
			//TODO: else throw an exception, because book is already in the tree?
			
		}
		
		if (inserter.ISBN > isbNum) {
			inserter.setLeftChild(newBook);
			newBook.setParent(inserter);
		}
		else {
			inserter.setRightChild(newBook);
			newBook.setParent(inserter);
		}
		
	}
	
	
	/**
	 * Searches for a book by its ISB (International Standard Book) number in the tree saved in RAM
	 * and returns the book if found and null otherwise. 
	 * @param ISBN the International Standard Book Number of the book to be looked up, saved as an integer
	 * @return book as an instance of the class Book, if found; null otherwise
	 */
	public Book searchForBook(int ISBN) {
		
		//pointer used to course through the tree; lands on required book if the book is in the tree
		Book courserPointer = this.root;
		
		//loop to course through the tree
		while(courserPointer != null) {
			
			/* 
			 * tree is ordered by the isb numbers of the books
			 * left children have smaller numbers than their parents
			 * and right children conversely have larger numbers (ordered by pure numerical value)
			 * isb numbers are unique, so no collisions possible
			 */
			
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
		
		return courserPointer;
		
	}
	
}
