package commerce.e

import commerce.e.model.listing.Listing
import commerce.e.model.listing.MockListingRepository
import commerce.e.plugins.configureRouting
import commerce.e.plugins.configureSerialization
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ListingsTest {
    @Test
    fun getAllListings() = testApplication {
        application {
            val repository = MockListingRepository()
            configureSerialization(repository)
            configureRouting()
        }

        val response = client.get("/listings")
        assertEquals(HttpStatusCode.OK, response.status)

        val results = Json.decodeFromString<List<Listing>>(response.bodyAsText())
        val expectedListingTitles = listOf("Chitară", "Cameră foto", "Raft pentru carți")
        val actualTaskNames = results.map(Listing::title)
        assertContentEquals(expectedListingTitles, actualTaskNames)
    }

    @Test
    fun addListing() = testApplication {
        application {
            val repository = MockListingRepository()
            configureSerialization(repository)
            configureRouting()
        }

        val listing = Json.encodeToString(Listing("Mouse Gaming", "Mouse gaming performant", 1200, "image_url_5"))
        val response = client.post("/listings"){
            header(
                HttpHeaders.ContentType,
                ContentType.Application.Json
            )
            setBody(listing)
        }
        assertEquals(HttpStatusCode.NoContent, response.status)


        val listings = Json.decodeFromString<List<Listing>>(client.get("/listings").bodyAsText())
        val expectedListingTitles = listOf("Chitară", "Cameră foto", "Raft pentru carți", "Mouse Gaming")
        val actualTaskNames = listings.map(Listing::title)
        assertContentEquals(expectedListingTitles, actualTaskNames)
    }
}