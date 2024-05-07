import java.util.Scanner;

class Book{
	 int sNo;
	 String bookName;
	 String authorName;
	 int bookQty;
	 int bookQtyCopy;
	 
	 public Book(){
	 
	 Scanner input = new Scanner(System.in);
	 
	 	System.out.println("Enter Serial No of Book:");
		this.sNo = input.nextInt();
		input.nextLine();

		System.out.println("Enter Book Name:");
		this.bookName = input.nextLine();

		System.out.println("Enter Author Name:");
		this.authorName = input.nextLine();

		System.out.println("Enter Quantity of Books:");
		this.bookQty = input.nextInt();
		bookQtyCopy = this.bookQty;
	 }
	 
	 //function to display details of student
	 String displayDetails(int sNo,String bookName,String authorName,int bookQty,int bookQtyCopy){
		this.sNo = sNo;
		this.bookName = bookName;
		this.authorName = authorName;
		this.bookQty = bookQty;
		this.bookQtyCopy = bookQtyCopy;	
		
		return sNo +"\t"+bookName+"\t"+authorName+"\t"+bookQty+"\t"+bookQtyCopy+ "\n";
	 }
	 
	 
	 //to string method to print book and its author whenever we try to print object 
	 @Override
	public String toString(){
		return "Book - " + bookName + "by - " + authorName;
	}
}


public class Books extends Book{

 Scanner sc = new Scanner(System.in);
	Book theBooks[] = new Book[50];
	
	public static int count_of_book;
	
	//function to compare books
		public int compareBookObjects(Book b1, Book b2){

		// If book name matches
		if (b1.bookName.equalsIgnoreCase(b2.bookName) || b1.sNo == b2.sNo){
			System.out.println("Book of this Name Already Exists.");
			return 0;
		}
		return 1;
	}

		//function to add books to the library
		public void addBook(Book book){
		
			if (count_of_book < 50){
				this.theBooks[count_of_book] =book;
				count_of_book++;
				System.out.println(" Added Book :  "+book);
			}
			
			else {
				System.out.println("No Space to Add More Books.");
			}
		}
		
		//function to show how many and which books are available in the library
		void showAvailableBooks(){
			System.out.println("Available Books are: ");
			System.out.println( "S.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
			for (int i = 0; i < count_of_book; i++) {
				System.out.println( theBooks[i].sNo + "\t\t"+ theBooks[i].bookName + "\t\t"+ theBooks[i].authorName + "\t\t"+ theBooks[i].bookQtyCopy + "\t\t"+ theBooks[i].bookQty);
			}
		}
		
		//function to upgrade the number of books available using sr no.
		public void upgradeBookQty(){

		System.out.println("UPGRADE QUANTITY OF A BOOK\n");
		System.out.println("Enter Serial No of Book");

		int serial = sc.nextInt();
		for (int i = 0; i < count_of_book; i++) {
			if (serial == theBooks[i].serial) {
			
				System.out.println( "Enter No of Books to be Added:");
				int addingQty = sc.nextInt();
				theBooks[i].bookQty += addingQty;
				theBooks[i].bookQtyCopy += addingQty;
				//return;
			}
		}
	}
	
	
	//function to search the book by its serial number or its name
	public void search(){
	int choice;
		System.out.println("if you want to search by book name enter 1 if you want to search by sr no enter 0 : ");
		choice = sc.nextInt();
		
		if(choice == 0){
			System.out.println("SEARCH BY SERIAL NUMBER\n");
			// Class data members
			int sNo;
			System.out.println("Enter Serial No of Book:");
			sNo = sc.nextInt();
			int flag = 0;
			System.out.println("S.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
			for (int i = 0; i < count_of_book; i++) {
				if (sNo == theBooks[i].sNo) {
					System.out.println( theBooks[i].sNo + "\t\t" + theBooks[i].bookName + "\t\t"+ theBooks[i].authorName + "\t\t"+ theBooks[i].bookQtyCopy + "\t\t"+ theBooks[i].bookQty);
					flag++;
					return;
				}
			}
			if (flag == 0){
				System.out.println("No Book for Serial No " + sNo + " Found.");
			}
		}
		
		else if(choice == 1){
			System.out.println("SEARCH BY BOOK NAME");
			sc.nextLine();
			System.out.println("Enter book Name:");
			String bookName = sc.nextLine();
			int flag = 0;
			System.out.println("S.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
			for (int i = 0; i < count_of_book; i++) {
				// if author matches any of its book
				if (bookName.equalsIgnoreCase(theBooks[i].bookName)) {
					System.out.println(theBooks[i].sNo + "\t\t"+ theBooks[i].bookName + "\t\t"+ theBooks[i].authorName + "\t\t"+ theBooks[i].bookQtyCopy + "\t\t"+ theBooks[i].bookQty);
					flag++;
				}
			}

			// Else no book matches
			if (flag == 0){
				System.out.println("No Books of name  " + bookName + " Found.");
			}
		}
	}
	
	
	// To search the library
	public int isAvailable(int sNo)
	{

		for (int i = 0; i < count_of_book; i++) {
			if (sNo == theBooks[i].sNo) {
				if (theBooks[i].bookQtyCopy > 0) {
					System.out.println("Book is Available.");
					return i;
				}
				System.out.println("Book is Unavailable");
				return -1;
			}
		}
		System.out.println("No Book of Serial Number " + " Available in Library.");
		return -1;
	}
	
	
	// To remove the book from the library
	public Book checkOut(){

		System.out.println("Enter Serial No of Book to be Checked Out.");
		int sNo = sc.nextInt();
		int bookIndex = isAvailable(sNo);
		if (bookIndex != -1) {
			theBooks[bookIndex].bookQtyCopy--;
			return theBooks[bookIndex];
		}
		return null;
	}


	// To add the Book to the Library
	public void checkIn(Book b)
	{
		for (int i = 0; i < count_of_book; i++) {
			if (b.equals(theBooks[i])) {
				theBooks[i].bookQtyCopy++;
				return;
			}
		}
	}
	
	public void dispMenu(){
		// Displaying menu
		System.out.println(
			"----------------------------------------------------------------------------------------------------------");
		System.out.println("Press 1 to Add new Book.");
		System.out.println("Press 0 to Exit Application.");
		System.out.println("Press 2 to Upgrade Quantity of a Book.");
		System.out.println("Press 3 to Search a Book.");
		System.out.println("Press 4 to Show All Books.");
		System.out.println("Press 5 to Register Student.");
		System.out.println("Press 6 to Show All Registered Students.");
		System.out.println("Press 7 to Check Out Book. ");
		System.out.println("Press 8 to Check In Book");
		System.out.println(
			"-------------------------------------------------------------------------------------------------------");
	}
}




	
// Java Program to Illustrate students Class
// To Do all the Operations related to students:
// add Students,check-in books,check out books,ValidStudent


// Class
 class students extends Books{
 
 	String studentName;
	String regNum;

	Book borrowedBooks[] = new Book[3];
	public int booksCount = 0;

	Scanner sc = new Scanner(System.in);

	// Constructor
	public students()
	{
		System.out.println("Enter Student Name:");
		this.studentName = sc.nextLine();
		
		System.out.println("Enter Registration Number:");
		this.regNum =sc.nextLine();
	}
	
	students theStudents[] = new students[50];

	public static int count = 0;
	
	// To register student for library 
	public String addStudent(students s) {
		for (int i = 0; i < count; i++) {
			if (s.regNum.equalsIgnoreCase(theStudents[i].regNum)) {
				return "Student of Reg Num " + s.regNum + " is Already Registered.";
			}
		}
		
		if (count <= 50) {
			theStudents[count] = s;
			count++;
		}
	}


	// Displaying all students
	
	public void showAllStudents()
	{
		// Printing student name and
		// corresponding registered number
		System.out.println("Student Name\t\tReg Number");
		for (int i = 0; i < count; i++) {
			System.out.println(theStudents[i].studentName+ "\t\t" + theStudents[i].regNum);
		}
	}


	// To check the Student if he is registered or not

	public int isStudent()
	{
		// Display message only
		System.out.println("Enter Reg Number:");

		String regNum = sc.nextLine();

		for (int i = 0; i < count; i++) {
			if (theStudents[i].regNum.equalsIgnoreCase(regNum)) {
				return i;
			}
		}

		System.out.println("Student is not Registered.");
		System.out.println("Get Registered First.");

		return -1;
	}

	// To issue the book
	public int issueBook(Books book)
	{
		int studentIndex = this.isStudent();

		if (studentIndex != -1) {
			System.out.println("the student can issue a book");

			book.showAllBooks();
			book b = book.issueBook();

			System.out.println("issuing book");
			if (b != null) {

				if (theStudents[studentIndex].booksCount<= 3) {

					
					theStudents[studentIndex].borrowedBooks[theStudents[studentIndex].booksCount]= b;
					theStudents[studentIndex].booksCount++;
					System.out.println(" you have total issued book : " +theStudents[studentIndex].booksCount );
					return theStudents[studentIndex].booksCount;
				}
				else {
					System.out.println("Student Can not Borrow more than 3 Books.");
					return -1; 
				}
			}
			System.out.println("Book is not Available.");
		}
		return 0;
	}

	
	// function to return the book
	public void returnBook(Books book){
		int studentIndex = this.isStudent();
		if (studentIndex != -1) {
			// Printing credentials corresponding to student
			System.out.println("S.No\t\t\tBook Name\t\t\tAuthor Name");
			student s = theStudents[studentIndex];
			for (int i = 0; i < s.booksCount; i++) {
				System.out.println(s.borrowedBooks[i].sNo + "\t\t\t"+ s.borrowedBooks[i].bookName + "\t\t\t"+ s.borrowedBooks[i].authorName);
			}
			System.out.println("Enter Serial Number of Book to be returned :");

			int sNo = sc.nextInt();

			for (int i = 0; i < s.booksCount; i++) {
				if (sNo == s.borrowedBooks[i].sNo) {
					book.returnBook(s.borrowedBooks[i]);
					s.borrowedBooks[i] = null;
					return;
				}
			}
			System.out.println("Book of Serial No " + sNo + "not Found");
		}
	}
	

	// Main driver method
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);

		// Displaying menu
		System.out.println( "                        WELCOME TO THE LIBRARY MANAGEMENT SYSTEM                        ");
		System.out.println( "				 Select From The Following Options:			 ");
		
		// Creating object of book class
		Books ob = new Books();
		// Creating object of students class
		students obStudent = new students();

		int choice;
		int searchChoice;

		// Creating menu
		// using do-while loop
		do {

			ob.dispMenu();
			choice = input.nextInt();

			// Switch case
			switch (choice) {

				
			case 1:
				Book b = new Book();
				ob.addBook(b);
				break;

				
			case 2:
				ob.upgradeBookQty();
				break;

			case 3:

				System.out.println(
					" press 1 to Search with Book Serial No.");
				System.out.println(
					" Press 2 to Search with Book's Author Name.");
				searchChoice = input.nextInt();

				// Nested switch
				switch (searchChoice) {

					
				case 1:
					ob.searchBySno();
					break;

					
				case 2:
					ob.searchByAuthorName();
				}
				break;

				
			case 4:
				ob.showAllBooks();
				break;

			
			case 5:
				student s = new student();
				obStudent.addStudent(s);
				break;

				
			case 6:
				obStudent.showAllStudents();
				break;

				
			case 7:
				obStudent.issueBook(ob);
				break;

			
			case 8:
				obStudent.returnBook(ob);
				break;

				// Default case that will execute for sure
				// if above cases does not match
			default:

				// Print statement
				System.out.println("ENTER BETWEEN 0 TO 8.");
			}

		}

		// Checking condition at last where we are
		// checking case entered value is not zero
		while (choice != 0);
	}
}



