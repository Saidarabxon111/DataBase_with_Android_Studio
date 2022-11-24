package uz.databasewithandroidstudio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import uz.databasewithandroidstudio.adapders.SotuvchiRvAdabter
import uz.databasewithandroidstudio.databinding.FragmentXaridFragmentBinding
import uz.databasewithandroidstudio.databinding.ItemDialogBinding
import uz.databasewithandroidstudio.databinding.ItemDialogXaridorBinding
import uz.databasewithandroidstudio.db.MyDbHelper
import uz.databasewithandroidstudio.models.Xaridor


class xarid_fragment : Fragment() {

    lateinit var binding: FragmentXaridFragmentBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list: ArrayList<Xaridor>
    private lateinit var sotuvchiRvAdabter: SotuvchiRvAdabter<Xaridor>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentXaridFragmentBinding.inflate(layoutInflater)
        myDbHelper = MyDbHelper(context)
        list = ArrayList()
        list.addAll(myDbHelper.getCustomer())
        sotuvchiRvAdabter = SotuvchiRvAdabter(list, binding.root.context)
        binding.rv.adapter = sotuvchiRvAdabter


        binding.btnAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(binding.root.context).create()
            val itemDialogBinding = ItemDialogXaridorBinding.inflate(layoutInflater)
//            itemDialogBinding.edtAddress.visibility = View.VISIBLE
            dialog.setView(itemDialogBinding.root)
            dialog.show()


            itemDialogBinding.btnSave.setOnClickListener {
            val xaridor = Xaridor(
                itemDialogBinding.edtName.text.toString(),
                itemDialogBinding.edtNumber.text.toString(),
                itemDialogBinding.edtAddress.text.toString()
            )
                myDbHelper.addCustomer(xaridor)
                list.add(xaridor)
                sotuvchiRvAdabter.notifyItemInserted(list.size-1)


                Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }

        }


        return binding.root
    }


}