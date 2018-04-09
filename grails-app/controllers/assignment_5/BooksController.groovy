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
}
