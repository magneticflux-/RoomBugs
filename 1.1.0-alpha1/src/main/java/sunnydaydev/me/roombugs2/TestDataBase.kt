package sunnydaydev.me.roombugs2

import android.arch.persistence.room.*
import android.content.Context

@Entity
data class TestItem(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
        val nullable: Boolean?
)

@Dao
interface TestDao {

    @Insert
    fun insert(testItem: TestItem)

}

@Database(
        version = 1,
        entities = [TestItem::class]
)
abstract class TestDataBase: RoomDatabase() {

    abstract val dao: TestDao

    companion object {

        fun create(ctx: Context): TestDataBase {
            return Room.databaseBuilder(ctx, TestDataBase::class.java, "test.db")
                    .build()
        }

    }

}