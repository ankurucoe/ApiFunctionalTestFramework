package Helpers;

import Constants.EndPoints;
import Models.Book;
import Utils.ConfigManager;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import java.lang.reflect.Type;
import java.util.List;
public class BookHelper {

    private static final String baseUrl = ConfigManager.getInstance().getProp("baseUrl");

    public BookHelper(){
        RestAssured.baseURI = baseUrl;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public List<Book> getAllBooks(){
        Response resp = RestAssured.given().contentType(ContentType.JSON)
                .get(EndPoints.booksEndPoint).andReturn();
        Type type = new TypeReference<List<Book>>(){}.getType();
        Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_OK, "Ok");
        List<Book> bookList = resp.as(type);
        return bookList;
    }

    public Response createBook(int id, String title, String desc, int pageCount, String excerpt, String publishDate){
        Book book = new Book();
        book.setID(id);
        book.setTitle(title);
        book.setDescription(desc);
        book.setPageCount(pageCount);
        book.setExcerpt(excerpt);
        book.setPublishDate(publishDate);

        Response resp = RestAssured.given().contentType(ContentType.JSON).body(book)
                .post(EndPoints.createBookRecordEndPoint).andReturn();

        Assert.assertEquals(resp.getStatusCode(), HttpStatus.SC_OK, "Post Request Books");
        return resp;
    }
}
