package seminar2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library extends ArrayList<Book> {
    Book getBook(int index) {
        Book book = get(index);
        set(index, new Book(book.title(), book.description(), book.authors(), book.publicationDate(), false));
        return get(index);
    }

    void returnBook(int index) {
        Book book = get(index);
        set(index, new Book(book.title(), book.description(), book.authors(), book.publicationDate(), true));
    }

    List<Integer> getIndices(String title) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (get(i).title().equals(title) && get(i).available()) {
                result.add(i);
            }
        }
        return result;
    }

    List<Integer> getMissingIndices(String title) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (get(i).title().equals(title) && !get(i).available()) {
                result.add(i);
            }
        }
        return result;
    }

    boolean isAvailable(String title) {
        return !getIndices(title).isEmpty();
    }
}
