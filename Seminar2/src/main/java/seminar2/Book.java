package seminar2;

import java.util.List;

public record Book(String title, String description, List<String> authors, int publicationDate, boolean available) {
}
