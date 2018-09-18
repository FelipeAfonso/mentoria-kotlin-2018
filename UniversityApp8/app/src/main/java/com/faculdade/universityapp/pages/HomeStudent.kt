package com.faculdade.universityapp.pages

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.faculdade.universityapp.R
import com.faculdade.universityapp.bd.EstudanteBDHelper
import kotlinx.android.synthetic.main.activity_home_student.*


class HomeStudent : AppCompatActivity() {

    lateinit var EstudanteBDHelper : EstudanteBDHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_student)
        EstudanteBDHelper = EstudanteBDHelper(this)

        var student = EstudanteBDHelper.lerEstudante(1)
        student.forEach {
            nomeStudent.text = it.nome
            nascimentoStudent.text = it.nascimento
            ingressoStudent.text = it.ingresso.toString()
            var cursado = (2018-it.ingresso)/2
            cursadoStudent.text = cursado.toString()
        }

        cadDisciplina_button.setOnClickListener {
        val i = Intent(applicationContext, Disciplinas::class.java)
        startActivity(i)
        }
        }
}
