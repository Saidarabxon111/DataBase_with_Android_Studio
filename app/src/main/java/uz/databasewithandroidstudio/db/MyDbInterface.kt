package uz.databasewithandroidstudio.db

import uz.databasewithandroidstudio.models.Buyurtma
import uz.databasewithandroidstudio.models.Sotuvchi
import uz.databasewithandroidstudio.models.Xaridor

interface MyDbInterface {

    fun addSalesman(sotuvchi: Sotuvchi)
    fun addCustomer(xaridor: Xaridor)
    fun addOrder(buyurtma: Buyurtma)


    fun getAllSalesman(): List<Sotuvchi>
    fun getCustomer(): List<Xaridor>
    fun getAllOrders(): List<Buyurtma>

    fun getSalesmenById(id:Int):Sotuvchi
    fun getCustomerById(id:Int):Xaridor

}