import Helpers.BookHelper;
import Models.Book;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class BooksTest {
    private BookHelper bookHelper;

    @BeforeClass
    public void setUp(){
        bookHelper = new BookHelper();
    }

    @Test
    public void validateBookTest(){

        List<Book> bookList= bookHelper.getAllBooks();
        Assert.assertNotNull(bookList, "Found Null Object");

    }

    @Test
    public void validatePostRequest(){
        String id = bookHelper.createBook(420, "Shaktimaan", "Indian Super Man", 50, null, "0001-01-01T00:00:00")
                .jsonPath().getString("ID");
        Assert.assertEquals(id, "420");

    }


}
