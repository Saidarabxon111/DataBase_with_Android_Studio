package uz.databasewithandroidstudio.adapders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.databasewithandroidstudio.databinding.ItemSotuvchiRvBinding
import uz.databasewithandroidstudio.models.Sotuvchi
import uz.databasewithandroidstudio.models.Xaridor

class SotuvchiRvAdabter<T>(private val list: List<T>, val context: Context?) :
    RecyclerView.Adapter<SotuvchiRvAdabter<T>.VH>() {
    inner class VH(var itemRvBinding: ItemSotuvchiRvBinding) :
        RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBindSotuvchi(sotuvchi: Sotuvchi, position: Int) {
            itemRvBinding.tvName.text = sotuvchi.name
            itemRvBinding.tvNumber.text = sotuvchi.number

        }

        fun onBindXaridor(xaridor: Xaridor, position: Int) {
            itemRvBinding.tvName.text = xaridor.name
            itemRvBinding.tvNumber.text = xaridor.number
            itemRvBinding.tvAddress.visibility = View.VISIBLE
            itemRvBinding.tvAddress.text = xaridor.address

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemSotuvchiRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
try {
val sotuvchi:Sotuvchi = list[position] as Sotuvchi
    holder.onBindSotuvchi(sotuvchi,position)
}catch (e:Exception){

}

        try {
            val xaridor:Xaridor = list[position] as Xaridor
            holder.onBindXaridor(xaridor,position)
        }catch (e:Exception){

        }
    }

    override fun getItemCount(): Int = list.size
}
