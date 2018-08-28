package com.faculdade.universityapp.pages

import android.os.Bundle
import android.app.Activity

import android.widget.Toast
import com.faculdade.universityapp.R
import com.faculdade.universityapp.R.layout.activity_disciplinas
import com.faculdade.universityapp.bd.Disciplina
import com.faculdade.universityapp.bd.DisciplinaBDHelper

class Disciplinas : Activity() {

    lateinit var DisciplinasDBHelper : DisciplinaBDHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disciplinas)
        DisciplinasDBHelper = DisciplinaBDHelper(this)

        }

    //Inserir disciplinas iniciais
    fun disciplinasIniciais(){

        //var d1 = Disciplina(0,"LP1","Linguagem de Programação I", 2,8.0)

        var result = DisciplinasDBHelper.inserirPrimeirasDisciplinas()

        if (result){
            Toast.makeText(this, "Disciplinas iniciais inseridas com sucesso", Toast.LENGTH_SHORT).show()
            //val disciplinasCursadas = DisciplinasDBHelper.lerTodasDisciplinas()

            //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1 ,disciplinasCursadas)
            //listDisciplinas.adapter = adapter
        }else{
            Toast.makeText(this, "Erro ao inserir disciplinas", Toast.LENGTH_SHORT).show()
        }
    }

    fun pesquisaDisciplina(id : Int){
        var discFound = DisciplinasDBHelper.lerDisciplina(id)

        discFound.forEach{
            Toast.makeText(this, "Sigla: ${it.sigla} || Nome: ${it.nome} || Semestre: ${it.semAno} || Nota: ${it.nota}", Toast.LENGTH_LONG).show()
        }
    }
    }



