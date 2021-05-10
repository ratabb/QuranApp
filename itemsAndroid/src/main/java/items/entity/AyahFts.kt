package items.entity

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.Fts4

@Immutable
@Entity(tableName = "ayahFts")
@Fts4(contentEntity = AyahEntity::class)
data class AyahFts(val transIndo: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as AyahFts
        if (transIndo != other.transIndo) return false
        return true
    }

    override fun hashCode(): Int = transIndo.hashCode()
    override fun toString() = "AyahFts(transIndo='$transIndo')"
}
