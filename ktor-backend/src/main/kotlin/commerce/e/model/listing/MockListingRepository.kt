package commerce.e.model.listing

class MockListingRepository : ListingRepository {
    private val tasks = mutableListOf(
        Listing("Chitară", "Chitară in stare foarte bună", 500, "image_url_1"),
        Listing("Cameră foto", "Cameră foto profesională cu accesorii", 800, "image_url_2"),
        Listing("Raft pentru carți", "Raft pentru carți ajustabil", 200, "image_url_3"),
    )

    override suspend fun allListings(): List<Listing> = tasks.toMutableList()

    override suspend fun createListing(listing: Listing) {
        tasks.add(listing)
    }

    override suspend fun deleteListing(title: String): Boolean = tasks.removeIf { it.title == title }
}