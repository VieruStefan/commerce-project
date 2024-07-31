package commerce.e.model.listing

interface ListingRepository {
    suspend fun allListings(): List<Listing>
    suspend fun createListing(listing: Listing)
    suspend fun deleteListing(title: String): Boolean
}