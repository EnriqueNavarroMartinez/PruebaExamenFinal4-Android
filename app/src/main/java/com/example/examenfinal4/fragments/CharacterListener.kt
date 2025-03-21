package com.example.examenfinal4.fragments

import com.example.examenfinal4.adapter.OnClickCharacterListener
import com.example.examenfinal4.api.HarryPotterCharacter

interface CharacterListener : OnClickCharacterListener {
    fun onCharacterSeleccionado(harryPotterCharacter: HarryPotterCharacter)
}

