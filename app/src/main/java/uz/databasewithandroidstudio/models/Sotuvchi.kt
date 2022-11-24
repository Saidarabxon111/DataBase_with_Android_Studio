package uz.databasewithandroidstudio.models

class Sotuvchi {
    var id: Int = 0
    var name: String = ""
    var number: String = ""


    constructor(id: Int, name: String, number: String) {
        this.id = id
        this.name = name
        this.number = number
    }

    constructor(name: String, number: String) {
        this.name = name
        this.number = number
    }

    override fun toString(): String {
        return "Sotuvchi(id=$id, name='$name', number='$number')"
    }


}