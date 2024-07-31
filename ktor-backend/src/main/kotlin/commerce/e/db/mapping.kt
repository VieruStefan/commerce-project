package commerce.e.db

import commerce.e.model.listing.Listing
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ListingTable: LongIdTable("listing") {
    val title = varchar("title", 255)
    val description = varchar("description", 500)
    val price = integer("price")
    val image_url = varchar("image_url", 255)
    val pub_date = datetime("pub_date")
    val updated_date = datetime("updated_date")
}

class ListingDAO(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ListingDAO>(ListingTable)

    var title by ListingTable.title
    var description by ListingTable.description
    var price by ListingTable.price
    var imageUrl by ListingTable.image_url
    var pubDate by ListingTable.pub_date
    var updatedDate by ListingTable.updated_date
}

fun daoToModel(dao: ListingDAO) = Listing(
    dao.title,
    dao.description,
    dao.price,
    dao.imageUrl,
//    dao.pubDate,
//    dao.updatedDate
)