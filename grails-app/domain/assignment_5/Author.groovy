package assignment_5

import org.grails.datastore.gorm.GormEntity

class Author implements GormEntity<Author> {

    String id
    String first_name
    String last_name

    static mapping = {
        table: 'authors'
    }

    static constraints = {
        last_name blank: false
    }

    static hasMany = [books: Book]

}
