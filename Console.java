import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class Console {

	public static void main (String[] args){
		
		//HIER EIGENEN DATAPATH EINGEBEN!!!
		String DatapathBooknames="C:/Users/User/workspace/SE05/bin/Bookname.txt";
		String DatapathAuthornames="C:/Users/User/workspace/SE05/bin/Authornames.txt";
		String DatapathISBN="C:/Users/User/workspace/SE05/bin/ISBN.txt";
		String DatapathUsernames="C:/Users/User/workspace/SE05/bin/names3.txt";
		
	    String Books[]= einlesenString(DatapathBooknames);
	    int ISBN[]= einlesenInt(DatapathISBN);
	    String Author[] = einlesenString(DatapathAuthornames);
	    BookTree Bibo = new BookTree(Books,ISBN,Author);
	    LoadUser(DatapathUsernames,Bibo);
	    eingabe(Bibo);
	}
	
	private static String[] einlesenString(String datName){
		String[] Array= new String[1];
		File file = new File(datName); 
		int count = 0;  
	        if (!file.canRead() || !file.isFile()) {
	            System.exit(0); }
	            BufferedReader in = null; 
	        try { 
	            in = new BufferedReader(new FileReader(datName)); 
	            String zeile = null;
	            while ((zeile = in.readLine()) != null) {
	            	Array[count]= zeile;
	            	String HArray[] = new String[Array.length];
	            	for(int i=0;i<Array.length;i++){
	            		HArray[i]=Array[i];
	            	}
	            	Array = new String[Array.length+1];
	            	for(int i=0;i<HArray.length;i++){
	            		Array[i]=HArray[i];
	            	}
	            	count++;
	            } 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } finally { 
	            if (in != null) 
	                try { 
	                    in.close(); 
	                } catch (IOException e) { 
	                } 
	        }
	        return Array;
	}
	
	private static int[] einlesenInt(String datName){
		int[] Array= new int[1];
		File file = new File(datName); 
		int count = 0;  
	        if (!file.canRead() || !file.isFile()) {
	            System.exit(0); }
	            BufferedReader in = null; 
	        try { 
	            in = new BufferedReader(new FileReader(datName)); 
	            String zeile = null;
	            zeile = in.readLine();
	            while (zeile!= null || testString(zeile)) {
	            	zeile = in.readLine();
	            	if(zeile==null){
	            		break;
	            	}
	            	Array[count]= Integer.parseInt(zeile);
	            	int HArray[] = new int[Array.length];
	            	for(int i=0;i<Array.length;i++){
	            		HArray[i]=Array[i];
	            	}
	            	Array = new int[Array.length+1];
	            	for(int i=0;i<HArray.length;i++){
	            		Array[i]=HArray[i];
	            	}
	            	count++;
            	
	            } 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } finally { 
	            if (in != null) 
	                try { 
	                    in.close(); 
	                } catch (IOException e) { 
	                } 
	        }
	        return Array;
	}
	static private void eingabe(BookTree TBook){
		int choose=-1;
		Scanner scanner = new Scanner(System.in);
		do{
		System.out.println("Bitte auswahltreffen.");
		System.out.println("0: Beenden.");
		System.out.println("1: Neuer Nutzer");
		System.out.println("2: Buch wuensch");
		System.out.println("3: ausleihen");
		System.out.println("4: zurückgeben");
		System.out.println("5: Alle User anzeigen");
		System.out.println("6: Alle Buecher anzeigen");
		System.out.println("7: Buch auf verfügbarkeit prüfen");
		System.out.println("8: Buch Benutzerzahl");
		System.out.println("9: Anzahl der Wuensche fuer ein Buch");
		System.out.println("10: Nutzer suchen");
		System.out.println("Eingabe bestaetigen = Enter");
		System.out.println("Fuers Menu nach Bestaetigung nochmal Enter");
		
		String readString = scanner.nextLine();
		if (readString.equals("0")){
			choose=0;
		    System.exit(0);
		}
		if (readString.equals("1")){
			System.out.println("Tragen sie einen Namen ein.");
			String username = scanner.nextLine();
			User New_User = new User(username);
		    TBook.userTree.addUser(New_User);
		}
		if (readString.equals("2")){
			System.out.println("Bitte eingeben: Buchname");
			String bookName = scanner.nextLine();
			System.out.println("Bitte eingeben: ISBN");
			int ISBN = Integer.parseInt(scanner.nextLine());
			System.out.println("Bitte eingeben: Author");
			String author = scanner.nextLine();
			System.out.println("Bitte eingeben: Username");
			String userName = scanner.nextLine();
			TBook.bookRequest(bookName, ISBN, author, userName);
		    
		}
		if (readString.equals("3")){
			System.out.println("Bitte Username eingben.");
		    String username = scanner.nextLine();
		    System.out.println("Bitte ISBN eingeben.");
		    int ISBN = Integer.parseInt(scanner.nextLine());
		    TBook.lendOutBook(ISBN, username);
		}
		if (readString.equals("4")){
			System.out.println("Bitte Username eingeben.");
			String username = scanner.nextLine();
			System.out.println("Bitte ISBN eingeben.");
			int ISBN = Integer.parseInt(scanner.nextLine());
			if(TBook.returnBook(ISBN, username)==true){
				Book Temp = TBook.searchForBook(ISBN);
				System.out.println("Das Buch "+Temp.BOOKNAME+" wurde erfolgreich zurueckgegeben");
			}
			else{
			System.out.println("Sie haben das Buch nicht ausgeliehen oder der Username ist falsch!");
			}
		}
		if (readString.equals("5")){
		    TBook.userTree.showAllUser();
		}
		//Meine Funktion ueberarbeiten
		if (readString.equals("6")){
		    TBook.ShowAllBooks();
		}
		if (readString.equals("7")){
			System.out.println("Bitte geben Sie die ISBN des Buchs ein.");
			int ISBN = Integer.parseInt(scanner.nextLine());
			Book Temp = TBook.searchForBook(ISBN);
			if(Temp.available){
				System.out.println("Dieses Buch ("+Temp.BOOKNAME+") ist verfügbar.");
			}else{
				System.out.println("Dieses Buch ("+Temp.BOOKNAME+") ist nicht vorhanden.");
			}
		}
		if (readString.equals("8")){
			System.out.println("Bitte geben Sie die ISBN des Buchs ein.");
			int ISBN = Integer.parseInt(scanner.nextLine());
			Book Temp = TBook.searchForBook(ISBN);
			System.out.println("Das buch wurde schon "+Temp.timesLentOut+" ausgeliehen.");
		}
		if (readString.equals("9")){
			System.out.println("Bitte geben Sie die ISBN des Buchs ein.");
			int ISBN = Integer.parseInt(scanner.nextLine());
			Book Temp = TBook.searchForBook(ISBN);
			System.out.println("Das Buch wurde "+Temp.timesOrderedByUsers+" gewuenscht.");
		}
		if (readString.equals("10")){
			System.out.println("Bitte gesuchten Nutzer eingeben.");
			String username = scanner.nextLine();
			if(TBook.userTree.findUser(username)!=null){
				System.out.println("Nutzer gefunden");
			}else{
				System.out.println("Nutzer nicht gefunden");
			}
		}
		if (scanner.hasNextLine()){
		    readString = scanner.nextLine();
		}else{
		    readString = null;
		}
		} while(choose!=0);
		scanner.close();
		}
	private static boolean testString (String a){
		  for( int i = 0, n = a.length(); i<n; i++ ){
		    if( ! Character.isDigit( a.charAt( i ) )){
		      return false;
		    }
		  }
		  return true;
		}
	
    private static void LoadUser (String DatapathUsernames, BookTree Bibo){
    String Name = DatapathUsernames;
	
	  File file = new File(Name); 

        if (!file.canRead() || !file.isFile()) {
            System.exit(0); }
            BufferedReader in = null; 
        try { 
            in = new BufferedReader(new FileReader(Name)); 
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
            	User New_User = new User(zeile);
    			Bibo.userTree.addUser(New_User);
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            if (in != null) 
                try { 
                    in.close(); 
                } catch (IOException e) { 
                } 
        }  
}
}
