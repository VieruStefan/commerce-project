package commerce.e.model.listing

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Serializable
data class Listing (
    val title: String,
    val description: String,
    val price: Int,
    val image_url: String,
//    val pub_date: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
//    val updated_date: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)
)