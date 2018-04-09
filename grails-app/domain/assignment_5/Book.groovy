package assignment_5

import org.grails.datastore.gorm.GormEntity

class Book implements GormEntity<Book> {

    String id
    String title
    String genre
    int year_published
    int pages


    static mapping = {
        table: 'books'
    }
    static constraints = {
        title blank: false
    }

    static belongsTo = Author

    static hasMany = [authors: Author]
}
