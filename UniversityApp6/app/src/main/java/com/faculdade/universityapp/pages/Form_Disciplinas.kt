package com.faculdade.universityapp.pages

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.faculdade.universityapp.R
import com.faculdade.universityapp.bd.Disciplina
import com.faculdade.universityapp.bd.DisciplinaBDHelper
import kotlinx.android.synthetic.main.activity_disciplinas.*

class Form_Disciplinas : Activity() {

    lateinit var DisciplinasBDHelper : DisciplinaBDHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disciplinas)
        DisciplinasBDHelper = DisciplinaBDHelper(this)

        try {
            var id_disc = intent.getStringExtra("disc_id").toInt()
            var sigla_disc = intent.getStringExtra("sigla")
            var nome_disc = intent.getStringExtra("nome")
            var semestre_disc = intent.getStringExtra("semestre")
            var nota_disc = intent.getStringExtra("nota")

            Sigla.setText(sigla_disc)
            Nome_Disc.setText(nome_disc)
            SemAno.setText(semestre_disc)
            Nota.setText(nota_disc)



            titulo.text = "Editar Disciplina"
            btn_formAddDisc.text = "Alterar"

            btn_formAddDisc.setOnClickListener {

                val edit_sigla = Sigla.text.toString()
                val edit_nome = Nome_Disc.text.toString()
                val edit_semestre = SemAno.text.toString().toInt()
                val edit_nota = Nota.text.toString().toDouble()

                var disc_edit = Disciplina(id_disc, edit_sigla, edit_nome, edit_semestre, edit_nota)
                var editarDisc = DisciplinasBDHelper.editarDisciplina(disc_edit)
                if (editarDisc){

                    Toast.makeText(this, "Disciplina alterada com sucesso!", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, Disciplinas::class.java)
                    startActivity(i)
                    btn_formAddDisc.text = "Adicionar"


                }else{
                    Toast.makeText(this, "Não foi possível alterar a disciplina", Toast.LENGTH_SHORT).show()
                }

            }



        }catch (e : Exception){
            btn_formAddDisc.setOnClickListener{
                var sigla = Sigla.text.toString()
                var nome = Nome_Disc.text.toString()
                var semestre = SemAno.text.toString().toInt()
                var nota = Nota.text.toString().toDouble()

                var disciplina = Disciplina(0, sigla, nome, semestre, nota)

                addDisciplina(disciplina)
            }
        }

    }

    fun addDisciplina(disciplinas : Disciplina){
        val insert = DisciplinasBDHelper.inserirDisciplina(disciplinas)
        if (insert) {
            Toast.makeText(this, "Disciplina Inserida com sucesso", Toast.LENGTH_SHORT).show()
            val i = Intent(this, Disciplinas::class.java)
            startActivity(i)
        }
        else {
            Toast.makeText(this, "Não foi possível inserir a disciplina", Toast.LENGTH_SHORT).show()
        }
    }

}
