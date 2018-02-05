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

    @Insert
    fun insert(testItemTag: TestItemTag)

    @Transaction
    @Query("SELECT * FROM TestItem WHERE id = :id")
    fun query(id: Long): List<TestItemAndTags>
}

@Database(
        version = 1,
        entities = [TestItem::class, TestItemTag::class]
)
abstract class TestDataBase : RoomDatabase() {

    abstract val dao: TestDao

    companion object {

        fun create(ctx: Context): TestDataBase {
            return Room.databaseBuilder(ctx, TestDataBase::class.java, "test.db")
                    .build()
        }

    }

}

@Entity
data class TestItemTag(@PrimaryKey val id: Long,
                       val data: String)

data class TestItemAndTags @JvmOverloads constructor(@field:Embedded val testItem: TestItem,
                                                     @field:Relation(entity = TestItemTag::class, parentColumn = "id", entityColumn = "id")
                                                     var tags: List<TestItemTag> = listOf())