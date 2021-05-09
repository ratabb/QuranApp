package items.entity

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.Fts4

@Immutable
@Entity(tableName = "ayahFts")
@Fts4(contentEntity = AyahEntity::class)
data class AyahFts(val transIndo: String)
