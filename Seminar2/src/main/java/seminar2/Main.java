package seminar2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Library library = new Library();
        library.add(new Book("Book 1", "Some description",
                Arrays.asList("Author 1", "Author 2"), 1999, true));
        library.add(new Book("Book 1", "Other description",
                Arrays.asList("Author 1", "Author 2"), 2001, true));
        library.add(new Book("Book 2", "Some description",
                List.of("Author 1"), 2007, true));
        library.add(new Book("Book 3", "Some description",
                Arrays.asList("Author 1", "Author 2", "Author 3"), 2003, true));

        String command;
        do {
            System.out.print("Enter command: ");
            command = in.next();
            switch (command) {
                case "get":
                    String bookToGet = in.next() + in.nextLine();
                    List<Integer> indices = library.getIndices(bookToGet);

                    if (indices.isEmpty()) {
                        System.out.println("No book with this title available!");
                    } else if (indices.size() == 1) {
                        Book book = library.getBook(indices.get(0));
                        System.out.println("You now have this book:");
                        System.out.println(book);
                    } else {
                        System.out.println("Choose a book:");
                        for (Integer index : indices) {
                            System.out.print(index + 1);
                            System.out.print(" : ");
                            System.out.println(library.get(index));
                        }

                        int choice = in.nextInt();

                        if ((choice > indices.size()) || (choice < 1)) {
                            System.out.println("Invalid choice");
                            break;
                        }

                        Book book = library.getBook(indices.get(choice - 1));
                        System.out.println("You now have this book:");
                        System.out.println(book);
                    }
                    break;
                case "put":
                    String bookToPut = in.next() + in.nextLine();
                    List<Integer> missingIndices = library.getMissingIndices(bookToPut);

                    if (missingIndices.isEmpty()) {
                        System.out.println("No book with this title is missing!");
                    } else if (missingIndices.size() == 1) {
                        library.returnBook(missingIndices.get(0));
                        System.out.println("You have successfully returned the book.");
                    } else {

                        System.out.println("Choose a book to return:");
                        for (Integer index : missingIndices) {
                            System.out.print(index + 1);
                            System.out.print(" : ");
                            System.out.println(library.get(index));
                        }

                        int choice = in.nextInt();

                        if ((choice > missingIndices.size()) || (choice < 1)) {
                            System.out.println("Invalid choice");
                            break;
                        }

                        library.returnBook(missingIndices.get(choice - 1));
                        System.out.println("You have successfully returned the book.");
                    }
                    break;
                case "list":
                    System.out.println("List of books that you have:");
                    for (Book book : library) {
                        if (!book.available()) {
                            System.out.println(book);
                        }
                    }
                    in.nextLine();
                    break;
                case "all":
                    System.out.println("List of all books:");
                    for (Book book : library) {
                        System.out.println(book);
                    }
                    in.nextLine();
                    break;
                case "exit":
                    break;
                default:
                    in.nextLine();
                    System.out.println("Unable to recognize the command!");
                    break;
            }
        } while (!command.equals("exit"));
    }
}