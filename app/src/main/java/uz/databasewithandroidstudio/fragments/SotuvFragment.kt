package uz.databasewithandroidstudio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import uz.databasewithandroidstudio.adapders.SotuvchiRvAdabter
import uz.databasewithandroidstudio.databinding.FragmentSotuvBinding
import uz.databasewithandroidstudio.databinding.ItemDialogBinding
import uz.databasewithandroidstudio.db.MyDbHelper
import uz.databasewithandroidstudio.models.Sotuvchi


class SotuvFragment : Fragment() {

    private lateinit var binding: FragmentSotuvBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var sotuvchiRvAdabter: SotuvchiRvAdabter<Sotuvchi>
    private lateinit var list:ArrayList<Sotuvchi>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSotuvBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)
        list  = ArrayList()
       list.addAll(myDbHelper.getAllSalesman())
        sotuvchiRvAdabter = SotuvchiRvAdabter(list, context)
        binding.rv.adapter = sotuvchiRvAdabter

        binding.btnAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(binding.root.context).create()
            val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
            dialog.setView(itemDialogBinding.root)


            dialog.show()

            itemDialogBinding.btnSave.setOnClickListener {
                val sotuvchi = Sotuvchi(
                    itemDialogBinding.edtName.text.toString(),
                    itemDialogBinding.edtNumber.text.toString()
                )
                myDbHelper.addSalesman(sotuvchi)
                list.add(sotuvchi)
                sotuvchiRvAdabter.notifyItemInserted(list.size-1)
                Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
        }
        return binding.root
    }


}