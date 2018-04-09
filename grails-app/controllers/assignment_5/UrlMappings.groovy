package assignment_5

class UrlMappings {

    static mappings = {

        get "/authors/$id/books"(controller: 'authors', action: 'getBooks')
        delete "/authors/$authorID/books/$bookID"(controller: 'authors', action: 'deleteBook')
        post "/authors/$id/books"(controller: 'authors', action: 'addBook')
        post "/authors/$authorID/books/$bookID"(controller: 'authors', action: 'addBookByID')

        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
