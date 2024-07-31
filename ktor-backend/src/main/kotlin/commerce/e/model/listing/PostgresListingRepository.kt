package commerce.e.model.listing

import commerce.e.db.ListingDAO
import commerce.e.db.ListingTable
import commerce.e.db.daoToModel
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class PostgresListingRepository : ListingRepository {
    override suspend fun allListings(): List<Listing> = newSuspendedTransaction(Dispatchers.IO, statement = {
        ListingDAO.all().map(::daoToModel)
    })

    override suspend fun createListing(listing: Listing): Unit = newSuspendedTransaction(Dispatchers.IO, statement = {
        ListingDAO.new {
            title = listing.title
            description = listing.description
            price = listing.price
            imageUrl = listing.image_url
//            pubDate = listing.pub_date
//            updatedDate = listing.updated_date
        }
    })

    override suspend fun deleteListing(title: String): Boolean = newSuspendedTransaction(Dispatchers.IO, statement = {
        val rowsDeleted = ListingTable.deleteWhere {
            ListingTable.title eq title
        }
        rowsDeleted == 1
    })
}