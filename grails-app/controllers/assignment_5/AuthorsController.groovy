package assignment_5

import com.fasterxml.jackson.databind.ObjectMapper
import grails.gorm.transactions.Transactional
import grails.rest.*
import grails.converters.*

@Transactional
class AuthorsController extends RestfulController {

    static responseFormats = ['json']

    AuthorsController() {
        super(Author)
    }

    /**
     * gets all authors
     *
     * @return all Authors in DB
     */
    def index() {
        render Author.findAll() as JSON
    }

    /**
     * adds author to DB based on request body information
     * if not already added
     *
     * @return message if author was added or not
     */
    def save() {
        def mapper = new ObjectMapper()
        def author = mapper.readValue(request.reader.text, Author.class)

        if (Author.find(author) == null) {
            author.save(failOnError: true)
            render author as JSON
        } else {
            render "author already added"
        }
    }

    /**
     * removes author from DB
     *
     * @return message saying author deleted successfully
     */
    def delete() {
        def temp = Author.get(params.id)

        temp.delete(failOnError: true)

        render "author deleted successfully"
    }

    /**
     * gets author information based on id parameter
     *
     * @return author information
     */
    def getBooks() {
        def author = Author.get(params.id)

        render author.books as JSON
    }

    /**
     * removes specified book from specified author
     *
     * @return author with book deleted
     */
    def deleteBook() {
        def author = Author.get(params.authorID)
        def book = Book.get(params.bookID)

        author.removeFromBooks(book)

        render author as JSON
    }

    /**
     * creates book from request body, saves book if not exists,
     * and adds book to author
     *
     * @return author with newly added book
     */
    def addBook() {
        def mapper = new ObjectMapper()

        def temp = mapper.readValue(request.reader.text, Book.class)

        def book = Book.find(temp)

        if (book == null)
            book.save(failOnError: true)

        def author = Author.get(params.id)

        author.addToBooks(book).save(failOnError: true)

        render author as JSON
    }

    /**
     * adds book based on bookID parameter, if in the DB
     *
     * @return author with new Book added
     */
    def addBookByID() {
        def author = Author.get(params.authorID)
        def book = Book.get(params.bookID)

        author.addToBooks(book).save()
        render author as JSON
    }
}
