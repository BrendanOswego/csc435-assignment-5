package assignment_5

import com.fasterxml.jackson.databind.ObjectMapper
import grails.gorm.transactions.Transactional
import grails.rest.*
import grails.converters.*

@Transactional
class BooksController extends RestfulController {

    static responseFormats = ['json']

    BooksController() {
        super(Book)
    }

    /**
     * GET request for /books
     * @return all Books in DB
     */
    def index() {
        render Book.findAll() as JSON
    }

    /**
     * POST request for /books
     * @return book saved to DB
     */
    def save() {
        def mapper = new ObjectMapper()

        def book = mapper.readValue(request.reader.text, Book.class)

        book.save(failOnError: true)

        render book as JSON
    }
    
    /**
     * updates book given body of request
     *
     * @return newly updated book
     */
    def updateBook() {
        def mapper = new ObjectMapper()
        def book = Book.get(params.id)

        def temp = mapper.readValue(request.reader.text, Book.class)

        if (temp.title != null)
            book.title = temp.title

        if (temp.genre != null)
            book.genre = temp.title

        if (temp.year_published != null)
            book.year_published = temp.year_published
        
        if (temp.pages != null)
            book.pages = temp.pages
        
        book.save(failOnError: true)

        render book as JSON
    }
}
