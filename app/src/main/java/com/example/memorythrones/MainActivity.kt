package com.example.memorythrones

import android.app.PendingIntent.OnFinished
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.children
private operator fun Unit.not(): Boolean {
    return false
}

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var buttonRestart: Button
    private lateinit var gridLayout: GridLayout
    private var jogo = Jogo()
    private var cartinhas = mutableListOf<ImageView>()

   

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        buttonRestart = findViewById(R.id.buttonRestart)
        gridLayout = findViewById(R.id.gridLayout)

        preencherGrid()


        object : CountDownTimer(7000, 70) {
            override fun onTick(millisUntilFinished: Long) {
                progressBar.progress += 1
            }
            override fun onFinish() {
                virarCartas()
                buttonRestart.setOnClickListener({reiniciar()})
            }
        }.start()
    }

    override fun onResume() {
        super.onResume()
        reiniciar()
    }


    fun preencherGrid(){
            for (carta in jogo.cartasImagem){
                var casa = ImageView(this)
                this.gridLayout.addView(casa)
                casa.setImageResource(carta)
                casa.layoutParams.height = 190
                casa.layoutParams.width = 190
                casa.tag = carta
            }
        }

        fun virarCartas(){
            for (casa in gridLayout.children){
                var casaTrono = casa as ImageView
                casaTrono.setImageResource(R.drawable.capa)
                casaTrono.setOnClickListener({virar(casaTrono)})
            }
        }


    fun virar(casaTrono: ImageView) {
        casaTrono.setImageResource(casaTrono.tag as Int)
        casaTrono.setOnClickListener(null) // Desativa o clique na carta

        this.cartinhas.add(casaTrono)

        if (cartinhas.size == 2) {
            if (!jogo.verificarEmparelhamento(cartinhas)) {
                val handler = android.os.Handler(Looper.getMainLooper())
                handler.postDelayed({
                    desvirarCartas(cartinhas)
                }, 1000)
            }
            this.cartinhas.clear()
            this.jogo.fimDoJogo()
        }
    }


        fun desvirarCartas(trono: MutableList<ImageView>) {
            val handler = android.os.Handler(Looper.getMainLooper())
            handler.postDelayed({
                for (casaTrono in trono) {
                    casaTrono.setImageResource(R.drawable.capa)
                    casaTrono.setOnClickListener { virar(casaTrono) }
                }
            }, 1)
        }

        fun reiniciar(){
            this.jogo.iniciarJogo()
            this.progressBar.progress = 0
            this.gridLayout.removeAllViews()

            preencherGrid()
            this.buttonRestart.setOnClickListener({})

            object : CountDownTimer(6000, 60) {
                override fun onTick(millisUntilFinished: Long) {
                    progressBar.progress += 1
                }
                override fun onFinish() {
                    virarCartas()
                    buttonRestart.setOnClickListener({reiniciar()})
                }
            }.start()
        }
    }






