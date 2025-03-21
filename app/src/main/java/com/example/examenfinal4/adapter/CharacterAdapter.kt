package com.example.examenfinal4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examenfinal4.R

import com.example.examenfinal4.api.HarryPotterCharacter
import com.example.examenfinal4.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharacterAdapter (private val caracters: List<HarryPotterCharacter>, private val listener: OnClickCharacterListener): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private lateinit var context: Context
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        // Inicializamos el binding (item)
        val binding = ItemCharacterBinding.bind(view)
        // Llamamos a onClickListener
        fun setListener(harryPotterCharacter: HarryPotterCharacter){
            binding.root.setOnClickListener {
                listener.onClick(harryPotterCharacter)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.ViewHolder {
        // Inflamos la vista del Intem del Recycler
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.ViewHolder, position: Int) {

        val character = caracters.get(position)

        with(holder){
            setListener(character)
            binding.nombre.text = character.name
            binding.especie.text = character.species
            binding.gender.text = character.gender
            binding.house.text = character.house
            binding.wizzard.text = character.wizard.toString()
            val imageUrl = character.image
            if (!imageUrl.isNullOrEmpty()) {
                Picasso.get().load(imageUrl).into(binding.imagen)
            } else {
                // Imagen por defecto si la URL está vacía o es null
                binding.imagen.setImageResource(R.drawable.ic_launcher_background)
            }
            Thread {
            }.start()
        }


    }


    override fun getItemCount(): Int = caracters.size




}