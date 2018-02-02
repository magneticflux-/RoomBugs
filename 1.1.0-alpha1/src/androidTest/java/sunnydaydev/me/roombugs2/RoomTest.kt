package sunnydaydev.me.roombugs2

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by sunny on 02.02.2018.
 * mail: mail@sunnydaydev.me
 */
@RunWith(AndroidJUnit4::class)
class RoomTest {

    private lateinit var db: TestDataBase

    @Before
    fun prepareDb() {

        val ctx = InstrumentationRegistry.getTargetContext()
        db = TestDataBase.create(ctx)

    }

    @Test
    fun insert_nullable_with_null() {

        val nullableWithNull = TestItem(nullable = null)

        db.dao.insert(nullableWithNull)

    }

}
