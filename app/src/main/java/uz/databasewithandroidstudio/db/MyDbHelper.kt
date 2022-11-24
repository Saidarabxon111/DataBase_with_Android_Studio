package uz.databasewithandroidstudio.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.databasewithandroidstudio.models.Buyurtma
import uz.databasewithandroidstudio.models.Sotuvchi
import uz.databasewithandroidstudio.models.Xaridor

class MyDbHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    MyDbInterface {

    companion object {

        const val DB_NAME = "market_db"
        const val DB_VERSION = 1

        const val SOTUVCHI_TABLE = "sotuvchi_table"
        const val SOTUVCHI_ID = "id"
        const val SOTUVCHI_NAME = "name"
        const val SOTUVCHI_NUMBER = "number"

        const val XARIDOR_TABLE = "xaridor_table"
        const val XARIDOR_ID = "id"
        const val XARIDOR_NAME = "name"
        const val XARIDOR_NUMBER = "number"
        const val XARIDOR_ADRESS = "adress"


        const val BUYURTMA_TABLE = "orders"
        const val BUYURTMA_ID = "id"
        const val BUYURTMA_NAME = "name"
        const val BUYURTMA_PRICE = "price"
        const val BUYURTMA_SOTUVCHI_ID = "sotuvchi_id"
        const val BUYURTMA_XARIDOR_ID = "xaridor_id"

    }

    override fun onCreate(p0: SQLiteDatabase?) {

        val querySotuvchi =
            "create table $SOTUVCHI_TABLE ($SOTUVCHI_ID integer not null primary key autoincrement, $SOTUVCHI_NAME text not null, $SOTUVCHI_NUMBER text not null)"
        val querXaridor =
            "create table $XARIDOR_TABLE ($XARIDOR_ID integer not null primary key autoincrement, $XARIDOR_NAME text not null, $XARIDOR_NUMBER text not null,$XARIDOR_ADRESS text not null )"
        val queryBuyurtma =
            "create table $BUYURTMA_TABLE ($BUYURTMA_ID integer not null primary key autoincrement, $BUYURTMA_NAME text not null, $BUYURTMA_PRICE integer not null, $BUYURTMA_SOTUVCHI_ID integer not null,$BUYURTMA_XARIDOR_ID integer not null,foreign key ($BUYURTMA_SOTUVCHI_ID)REFERENCES $SOTUVCHI_TABLE($SOTUVCHI_ID),FOREIGN KEY ($BUYURTMA_XARIDOR_ID)REFERENCES $XARIDOR_TABLE($XARIDOR_ID))"



        p0?.execSQL(querySotuvchi)
        p0?.execSQL(querXaridor)
        p0?.execSQL(queryBuyurtma)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun addSalesman(sotuvchi: Sotuvchi) {
        val database = readableDatabase
        val contentValues = ContentValues()
        contentValues.put(SOTUVCHI_NAME, sotuvchi.name)
        contentValues.put(SOTUVCHI_NUMBER, sotuvchi.number)
        database.insert(SOTUVCHI_TABLE, null, contentValues)
        database.close()
    }

    override fun addCustomer(xaridor: Xaridor) {

        val database = readableDatabase
        val contentValues = ContentValues()
        contentValues.put(XARIDOR_NAME, xaridor.name)
        contentValues.put(XARIDOR_NUMBER, xaridor.number)
        contentValues.put(XARIDOR_ADRESS, xaridor.address)
        database.insert(XARIDOR_TABLE, null, contentValues)
        database.close()
    }

    override fun addOrder(buyurtma: Buyurtma) {
        val database = readableDatabase
        val contentValues = ContentValues()
        contentValues.put(BUYURTMA_NAME, buyurtma.name)
        contentValues.put(BUYURTMA_PRICE, buyurtma.price)
        contentValues.put(BUYURTMA_SOTUVCHI_ID, buyurtma.sotuvchi?.id)
        contentValues.put(BUYURTMA_XARIDOR_ID, buyurtma.xaridor?.id)
        database.insert(BUYURTMA_TABLE, null, contentValues)
        database.close()
    }

    override fun getAllSalesman(): List<Sotuvchi> {
        val list = ArrayList<Sotuvchi>()
        val query = "select * from $SOTUVCHI_TABLE  "
        var database = readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Sotuvchi(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getCustomer(): List<Xaridor> {
        val list = ArrayList<Xaridor>()
        val query = "select * from $XARIDOR_TABLE"
        val database = readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Xaridor(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getAllOrders(): List<Buyurtma> {
        val list = ArrayList<Buyurtma>()
        val database = readableDatabase
        val query = "select * from $BUYURTMA_TABLE"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Buyurtma(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        getSalesmenById(cursor.getInt(3)),
                        getCustomerById(cursor.getInt(4))

                    )
                )
            } while (cursor.moveToNext())
        }


        return list
    }

    override fun getSalesmenById(id: Int): Sotuvchi {
val database = readableDatabase
        val cursor = database.query(SOTUVCHI_TABLE,
            arrayOf(
            SOTUVCHI_ID,
            SOTUVCHI_NAME,
            SOTUVCHI_NUMBER
        ),
            "$SOTUVCHI_ID = ?",
            arrayOf(id.toString()),
            null,null,null
        )
        cursor.moveToFirst()
        val sotuvchi = Sotuvchi(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
        )
        return sotuvchi
    }

    override fun getCustomerById(id: Int): Xaridor {
        val database = readableDatabase
        val cursor = database.query(XARIDOR_TABLE,
            arrayOf(
                XARIDOR_ID,
                XARIDOR_NAME,
                XARIDOR_NUMBER,
                XARIDOR_ADRESS
            ),
            "$XARIDOR_ID = ?",
            arrayOf(id.toString()),
            null,null,null
        )
        cursor.moveToFirst()
        val xaridor = Xaridor(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
        return xaridor
    }
}