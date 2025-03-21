package com.example.examenfinal4.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenfinal4.R
import com.example.examenfinal4.adapter.CharacterAdapter
import com.example.examenfinal4.adapter.OnClickCharacterListener
import com.example.examenfinal4.api.HarryPotterCharacter
import com.example.examenfinal4.api.RetrofitClient
import com.example.examenfinal4.databinding.DialogPersonalBinding
import com.example.examenfinal4.databinding.FragmentApiBinding
import com.example.examenfinal4.databinding.FragmentRoomBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RoomFragment : Fragment(), OnClickCharacterListener {

    //Declaramos la variablesa utilizar
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var binding: FragmentRoomBinding
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.api.getAllCharacters()

            if (response.isSuccessful && response.body() != null) {
                val characters = response.body()!! // List<Results>

                // Filtra los gatos cuyo origin sea "United States"
                val characterGriffindor = characters.filter { it.house == "Gryffindor" }


                withContext(Dispatchers.Main) {
                    characterAdapter = CharacterAdapter(characterGriffindor, this@RoomFragment)
                    linearLayoutManager = LinearLayoutManager(requireContext())
                    binding.recycler.apply {
                        layoutManager = linearLayoutManager
                        adapter = characterAdapter
                    }
                }
            } else {
                Log.e("gatos_LIST", "Error al obtener gatos")
            }
        }

    }

    // Para realizar onClick
    override fun onClick(harryPotterCharacter: HarryPotterCharacter) {

        // Cargar la imagen con Picasso
        Picasso.get()
            .load(harryPotterCharacter.image)
            .placeholder(R.drawable.ic_launcher_background) // Opcional: imagen mientras carga
            .error(R.drawable.harry)             // Opcional: imagen si hay error
            .into(binding.imagensita)

        binding.tvnombre.text = "Nombre: ${harryPotterCharacter.name}"
        binding.tvspecie.text = "Especie: ${harryPotterCharacter.species}"
        binding.tvgender.text = "Tipo: ${harryPotterCharacter.gender}"
        binding.tvhouse.text = "Género: ${harryPotterCharacter.house}"
        binding.tvwizzard.text = "mago: ${harryPotterCharacter.wizard}"




    }

}