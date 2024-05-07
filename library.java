import java.util.Scanner;

class Book {
    int sNo;
    String bookName;
    String authorName;
    int bookQty;
    int bookQtyCopy;

    public Book() {
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

    // Function to display details of a book
    String displayDetails(int sNo, String bookName, String authorName, int bookQty, int bookQtyCopy) {
        this.sNo = sNo;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookQty = bookQty;
        this.bookQtyCopy = bookQtyCopy;

        return sNo + "\t" + bookName + "\t" + authorName + "\t" + bookQty + "\t" + bookQtyCopy + "\n";
    }

    // toString method to print the details of a book
    @Override
    public String toString() {
        return "Book - " + bookName + " by - " + authorName;
    }
}

 class LibraryManagementSystem {

    Scanner sc = new Scanner(System.in);
    Book theBooks[] = new Book[50];
    
    public static int count_of_books;

    // Function to compare books
	public int compareBookObjects(Book b1, Book b2) {

		// If the book name or serial number matches
		if (b1.bookName.equalsIgnoreCase(b2.bookName) || b1.sNo == b2.sNo) {
			System.out.println("Book with this Name or Serial No Already Exists.");
			return 0;
		}
		return 1;
	}

    // Function to add books to the library
    public void addBook(Book book) {

        if (count_of_books < 50) {
            this.theBooks[count_of_books] = book;
            count_of_books++;
            System.out.println("Added Book: " + book);
        } else {
            System.out.println("No Space to Add More Books.");
        }
    }

    // Function to show available books in the library
    void showAvailableBooks() {
        System.out.println("Available Books are: ");
        System.out.println("S.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
        for (int i = 0; i < count_of_books; i++) {
            System.out.println(theBooks[i].sNo + "\t\t" + theBooks[i].bookName + "\t\t" + theBooks[i].authorName
                    + "\t\t" + theBooks[i].bookQtyCopy + "\t\t" + theBooks[i].bookQty);
        }
    }

    // Function to upgrade the quantity of a book using serial number
    public void upgradeBookQty() {

        System.out.println("UPGRADE QUANTITY OF A BOOK\n");
        System.out.println("Enter Serial No of Book");

        int serial = sc.nextInt();
        
        boolean found = false;

        for (int i = 0; i < count_of_books; i++) {
            if (serial == theBooks[i].sNo) {

                System.out.println("Enter No of Books to be Added:");
                int addingQty = sc.nextInt();
                theBooks[i].bookQty += addingQty;
                theBooks[i].bookQtyCopy += addingQty;
                
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No Book with Serial No " + serial + " Found.");
        }
    }

    // Function to search a book by its serial number or name
    public void search() {
        int choice;

        System.out.println("If you want to search by book name, enter 1. If you want to search by serial number, enter 0: ");
        choice = sc.nextInt();

        if (choice == 0) {
            System.out.println("SEARCH BY SERIAL NUMBER\n");
            System.out.println("Enter Serial No of Book:");
            int sNo = sc.nextInt();
            
            boolean found = false;

            for (int i = 0; i < count_of_books; i++) {
                if (sNo == theBooks[i].sNo) {
                    System.out.println(theBooks[i].sNo + "\t\t" + theBooks[i].bookName + "\t\t" + theBooks[i].authorName
                            + "\t\t" + theBooks[i].bookQtyCopy + "\t\t" + theBooks[i].bookQty);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("No Book with Serial No " + sNo + " Found.");
            }
        } else if (choice == 1) {
            sc.nextLine();
            
            System.out.println("SEARCH BY BOOK NAME");
            System.out.println("Enter book Name:");
            
            String bookName = sc.nextLine();
            
            boolean found = false;

            for (int i = 0; i < count_of_books; i++) {
                if (bookName.equalsIgnoreCase(theBooks[i].bookName)) {
                    System.out.println(theBooks[i].sNo + "\t\t" + theBooks[i].bookName + "\t\t" + theBooks[i].authorName
                            + "\t\t" + theBooks[i].bookQtyCopy + "\t\t" + theBooks[i].bookQty);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No Books with name " + bookName + " Found.");
            }
        } else {
            System.out.println("Invalid choice. Please enter either 0 or 1.");
        }
    }

    // To check if a book is available in the library
    public int isAvailable(int sNo) {

        for (int i = 0; i < count_of_books; i++) {
            if (sNo == theBooks[i].sNo) {
                if (theBooks[i].bookQtyCopy > 0) {
                    System.out.println("Book is Available.");
                    return i;
                }
                System.out.println("Book is Unavailable");
                return -1;
            }
        }

        System.out.println("No Book with Serial Number " + sNo + " Available in Library.");
        return -1;
    }

    // To remove a book from the library
    public Book checkOut() {

        System.out.println("Enter Serial No of Book to be Checked Out:");
        int sNo = sc.nextInt();
        
        int bookIndex = isAvailable(sNo);

        if (bookIndex != -1) {
            theBooks[bookIndex].bookQtyCopy--;
            return theBooks[bookIndex];
        }

        return null;
    }

    // To add a book back to the library
    public void checkIn(Book b) {

        for (int i = 0; i < count_of_books; i++) {
            if (b.equals(theBooks[i])) {
                theBooks[i].bookQtyCopy++;
                return;
            }
        }
    }

    // Displaying menu
    public void displayMenu() {
        System.out.println(
                "----------------------------------------------------------------------------------------------------------");
        System.out.println("Press 1 to Add a new Book.");
        System.out.println("Press 0 to Exit Application.");
        System.out.println("Press 2 to Upgrade Quantity of a Book.");
        System.out.println("Press 3 to Search a Book.");
        System.out.println("Press 4 to Show All Books.");
        System.out.println(
                "-------------------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Creating object of LibraryManagementSystem class
        LibraryManagementSystem library = new LibraryManagementSystem();

        int choice;

        // Creating menu using do-while loop
        do {
            library.displayMenu();
            choice = input.nextInt();

            // Switch case
            switch (choice) {

                case 1:
                    Book book = new Book();
                    library.addBook(book);
                    break;

                case 2:
                    library.upgradeBookQty();
                    break;

                case 3:
                    library.search();
                    break;

                case 4:
                    library.showAvailableBooks();
                    break;

                default:
                	System.out.println("ENTER BETWEEN 0 TO 5.");
               }
          }while (choice != 0);
              }
     }
   
