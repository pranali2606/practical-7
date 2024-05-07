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
    String displayDetails() {
        return sNo + "\t" + bookName + "\t" + authorName + "\t" + bookQty + "\t" + bookQtyCopy + "\n";
    }

    // toString method to print book and its author whenever we try to print object
    @Override
    public String toString() {
        return "Book - " + bookName + " by - " + authorName;
    }
}

class LibraryManagementSystem {

	static final int MAX = 50;

	static Book[] theBooks = new Book[MAX];

	static int count_of_book = 0;

	static Scanner sc = new Scanner(System.in);

	// Function to compare books
	public static boolean compareBookObjects(Book b1, Book b2) {

		// If book name or serial number matches
		if (b1.bookName.equalsIgnoreCase(b2.bookName) || b1.sNo == b2.sNo) {
			System.out.println("Book with this Name or Serial Number Already Exists.");
			return true;
		}
		return false;
	}

	// Function to add books to the library
	public static void addBook(Book book) {

		if (count_of_book < MAX) {
			boolean bookExists = false;
			for (int i = 0; i < count_of_book; i++) {
				if (compareBookObjects(theBooks[i], book)) {
					bookExists = true;
					break;
				}
			}

			if (!bookExists) {
				theBooks[count_of_book] = book;
				count_of_book++;
				System.out.println("Added Book: " + book);
			}
		} else {
			System.out.println("No Space to Add More Books.");
		}
    }
    
    // Function to show how many and which books are available in the library
    public static void showAvailableBooks() {
        System.out.println("Available Books are: ");
        System.out.println("S.No\tName\tAuthor\tAvailable Qty\tTotal Qty");
        for (int i = 0; i < count_of_book; i++) {
            System.out.println(theBooks[i].displayDetails());
        }
    }

    // Function to upgrade the number of books available using serial number
    public static void upgradeBookQty() {

        System.out.println("UPGRADE QUANTITY OF A BOOK\n");
        System.out.println("Enter Serial No of Book");

        int serial = sc.nextInt();
        for (int i = 0; i < count_of_book; i++) {
            if (serial == theBooks[i].sNo) {

                System.out.println("Enter No of Books to be Added:");
                int addingQty = sc.nextInt();
                theBooks[i].bookQty += addingQty;
                theBooks[i].bookQtyCopy += addingQty;

                return;
            }
        }

        System.out.println("No Book with Serial No " + serial + " Found.");
    }

    // Function to search the book by its serial number or name
    public static void search() {
        System.out.println("SEARCH BOOK");
        System.out.println("1. Search by Serial Number");
        System.out.println("2. Search by Book Name");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.println("SEARCH BY SERIAL NUMBER\n");
                System.out.println("Enter Serial No of Book:");
                int sNo = sc.nextInt();
                boolean foundBySerial = false;
                for (int i = 0; i < count_of_book; i++) {
                    if (sNo == theBooks[i].sNo) {
                        System.out.println(theBooks[i].displayDetails());
                        foundBySerial = true;
                        break;
                    }
                }
                if (!foundBySerial) {
                    System.out.println("No Book with Serial No " + sNo + " Found.");
                }
                break;

            case 2:
                System.out.println("SEARCH BY BOOK NAME\n");
                sc.nextLine();
                System.out.println("Enter Book Name:");
                String bookName = sc.nextLine();
                boolean foundByName = false;
                for (int i = 0; i < count_of_book; i++) {
                    if (bookName.equalsIgnoreCase(theBooks[i].bookName)) {
                        System.out.println(theBooks[i].displayDetails());
                        foundByName = true;
                    }
                }
                if (!foundByName) {
                    System.out.println("No Books with name \"" + bookName + "\" Found.");
				}
				break;

			default:
				System.out.println("Invalid choice!");
				break;
		}
	}

	// Function to issue a book to a student
	public static void issueBook() {
		System.out.println("ISSUE BOOK");
		System.out.println("Enter Serial No of Book to be Issued:");
		int sNo = sc.nextInt();
		sc.nextLine();

		boolean bookFound = false;
		for (int i = 0; i < count_of_book; i++) {
			if (sNo == theBooks[i].sNo) {
				if (theBooks[i].bookQtyCopy > 0) {
					theBooks[i].bookQtyCopy--;
					System.out.println("Book " + theBooks[i].bookName + " has been issued.");
					bookFound = true;
				} else {
					System.out.println("Book is not available for issue.");
				}
				break;
			}
		}

		if (!bookFound) {
			System.out.println("No Book with Serial No " + sNo + " Found.");
		}
    }
    
    // Function to return a book
	public static void returnBook() {
        System.out.println("RETURN BOOK");
        System.out.println("Enter Serial No of Book to be Returned:");
        int sNo = sc.nextInt();
        sc.nextLine();

        boolean bookFound = false;
        for (int i = 0; i < count_of_book; i++) {
            if (sNo == theBooks[i].sNo) {
                theBooks[i].bookQtyCopy++;
                System.out.println("Book " + theBooks[i].bookName + " has been returned.");
                bookFound = true;
                break;
            }
        }

        if (!bookFound) {
            System.out.println("No Book with Serial No " + sNo + " Found.");
        }
    }

	public static void main(String[] args) {

		int choice;

		do {

			System.out.println("LIBRARY MANAGEMENT SYSTEM");
			System.out.println("1. Add new Book");
			System.out.println("2. Upgrade Quantity of a Book");
			System.out.println("3. Search a Book");
			System.out.println("4. Show All Books");
			System.out.println("5. Issue a Book");
			System.out.println("6. Return a Book");
			System.out.println("0. Exit Application");

			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
				case 1:
					Book book = new Book();
					addBook(book);
					break;

				case 2:
					upgradeBookQty();
					break;

				case 3:
					search();
					break;

				case 4:
					showAvailableBooks();
					break;

				case 5:
					issueBook();
					break;

				case 6:
                    returnBook();
                    break;
                    
                case 0:
                    System.out.println("Exiting the application...");
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        } while (choice != 0);

        sc.close();
    }
}
