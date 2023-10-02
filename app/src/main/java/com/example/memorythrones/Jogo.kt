package com.example.memorythrones

import android.widget.ImageView

class Jogo {
    val cartasImagem = arrayOf(
        R.drawable.arryn,
        R.drawable.baratheon,
        R.drawable.lannister,
        R.drawable.mormont,
        R.drawable.stark,
        R.drawable.targaryen,
        R.drawable.tarth,
        R.drawable.tyrell,
        R.drawable.arryn,
        R.drawable.baratheon,
        R.drawable.lannister,
        R.drawable.mormont,
        R.drawable.stark,
        R.drawable.targaryen,
        R.drawable.tarth,
        R.drawable.tyrell

    )

    private var cartasEmparelhadas = 0


    init {
        iniciarJogo()
    }

    fun iniciarJogo() {
        this.cartasImagem.shuffle()
        this.cartasEmparelhadas = 0
    }

    fun verificarEmparelhamento(cartasViradas: MutableList<ImageView>) {
        val carta1 = cartasViradas[0].tag as Int
        val carta2 = cartasViradas[1].tag as Int

        if (carta1 == carta2) {
            this.cartasEmparelhadas += 2
        }
    }

    fun fimDoJogo(): Boolean{
        if (cartasEmparelhadas == 16){
            return true
        }
        return false
    }

}