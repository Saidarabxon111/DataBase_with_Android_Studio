package uz.databasewithandroidstudio.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.databasewithandroidstudio.R
import uz.databasewithandroidstudio.databinding.FragmentHomeBinding


class HomeFragment : androidx.fragment.app.Fragment() {

private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)

        binding.btnSotuvchi.setOnClickListener {

            findNavController().navigate(R.id.sotuvFragment)
        }
        binding.btnXaridor.setOnClickListener {
            findNavController().navigate(R.id.xarid_fragment)
        }

        binding.btnBuyurtma.setOnClickListener {
            findNavController().navigate(R.id.buyurtmaFragment)
        }

        return binding.root
    }


}