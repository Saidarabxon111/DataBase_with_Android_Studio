package uz.databasewithandroidstudio.adapders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.databasewithandroidstudio.databinding.ItemSotuvchiRvBinding
import uz.databasewithandroidstudio.models.Buyurtma

class BuyurtmaAdapter(private val list: ArrayList<Buyurtma>, val context: Context?) :
    RecyclerView.Adapter<BuyurtmaAdapter.VH>() {
    inner class VH(private val itemrvBinding: ItemSotuvchiRvBinding) :
        RecyclerView.ViewHolder(itemrvBinding.root) {
        fun onBind(buyurtma: Buyurtma) {
            itemrvBinding.tvName.text = buyurtma.name
            itemrvBinding.tvNumber.text = buyurtma.price.toString()
            itemrvBinding.tvAddress.visibility  =View.VISIBLE
            itemrvBinding.tvAddress.text = buyurtma.sotuvchi?.name

            itemrvBinding.tvXaridor.visibility  =View.VISIBLE
            itemrvBinding.tvXaridor.text = buyurtma.xaridor?.name

//            Glide.with(context).load(gridView.image).into(binding.image)
//            binding.image.setImageResource(gridView.image)
//            binding.txtName.text = gridView.text
//            binding.linearLayout.setBackgroundColor(gridView.backround)
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
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
