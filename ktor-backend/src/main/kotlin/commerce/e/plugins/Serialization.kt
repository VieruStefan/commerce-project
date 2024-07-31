package commerce.e.plugins

import com.fasterxml.jackson.databind.*
import commerce.e.model.listing.Listing
import commerce.e.model.listing.ListingRepository
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization(repository: ListingRepository) {
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
        route("/listings"){
            get{
                val listings = repository.allListings()
                call.respond(HttpStatusCode.OK, listings)
            }

            post {
                val listing = call.receive<Listing>()
                repository.createListing(listing)
                call.respond(HttpStatusCode.NoContent)
            }

            delete("/{id}") {
                val title = call.parameters["title"]

                if (title == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }

                if (repository.deleteListing(title)) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }

                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
