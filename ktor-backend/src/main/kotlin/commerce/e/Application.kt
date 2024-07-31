package commerce.e

import commerce.e.model.listing.ListingRepository
import commerce.e.model.listing.MockListingRepository
import commerce.e.model.listing.PostgresListingRepository
import commerce.e.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 7200, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val listingRepository = PostgresListingRepository()

    configureSerialization(listingRepository)
    configureDatabases()
    configureRouting()
}
