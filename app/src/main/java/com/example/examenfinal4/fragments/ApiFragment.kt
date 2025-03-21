package com.example.examenfinal4.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenfinal4.R
import com.example.examenfinal4.adapter.CharacterAdapter
import com.example.examenfinal4.adapter.OnClickCharacterListener
import com.example.examenfinal4.api.HarryPotterCharacter
import com.example.examenfinal4.api.RetrofitClient
import com.example.examenfinal4.databinding.DialogPersonalBinding
import com.example.examenfinal4.databinding.FragmentApiBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApiFragment : Fragment(), OnClickCharacterListener {

    //Declaramos la variablesa utilizar
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var binding: FragmentApiBinding
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.api.getAllCharacters()

            // Verifica si la respuesta fue exitosa
            if (response.isSuccessful && response.body() != null) {
                val characters = response.body()!!



                // Log para mostrar cada personaje recibido
                characters.forEach { character ->
                    Log.d("CHARACTER_LIST", "Nombre: ${character.name}, Estado: ${character.gender}, Especie: ${character.species}")
                }




                withContext(Dispatchers.Main) {
                    characterAdapter = CharacterAdapter(characters, this@ApiFragment)
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

// Inflamos el binding del layout del diálogo
        val dialogBinding = DialogPersonalBinding.inflate(layoutInflater)

        // Asignamos datos con binding
        with(dialogBinding) {
            Picasso.get()
                .load(harryPotterCharacter.image)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView1)

            tvnombre.text = "Nombre: ${harryPotterCharacter.name}"
            tvespecie.text = "Especie: ${harryPotterCharacter.species}"
            tvgenero.text = "Tipo: ${harryPotterCharacter.gender}"
            tvhouse.text = "Género: ${harryPotterCharacter.house}"
            tvwizzard.text = "mago: ${harryPotterCharacter.wizard}"
        }

        // Mostrar el diálogo con la vista de binding
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Detalles del Personaje")
            .setView(dialogBinding.root)  // Aquí se usa binding.root
            .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
            .show()


    }

}