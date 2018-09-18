package com.faculdade.universityapp.pages

import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.faculdade.universityapp.R
import com.faculdade.universityapp.bd.Disciplina
import com.faculdade.universityapp.bd.DisciplinaBDHelper
import kotlinx.android.synthetic.main.disciplinas.*
import kotlinx.android.synthetic.main.list_disciplinas_view.*

class Disciplinas : Activity() {

    lateinit var DisciplinasDBHelper: DisciplinaBDHelper
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disciplinas)
        DisciplinasDBHelper = DisciplinaBDHelper(this)

        btn_AddDisc.setOnClickListener {
            val i = Intent(this, Form_Disciplinas::class.java)
            startActivity(i)
        }

        //disciplinasIniciais()
        //pesquisaDisciplina(2)
        todasDisc()
        //apagaDisc()


    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    //Inserir disciplinas iniciais
    fun disciplinasIniciais() {

        var result = DisciplinasDBHelper.inserirPrimeirasDisciplinas()

        if (result) {
            Toast.makeText(this, "Disciplinas iniciais inseridas com sucesso", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Erro ao inserir disciplinas", Toast.LENGTH_SHORT).show()
        }
    }

    fun pesquisaDisciplina(id: Int) {
        var discFound = DisciplinasDBHelper.lerDisciplina(id)

        discFound.forEach {
            Toast.makeText(this, "Sigla: ${it.sigla} || Nome: ${it.nome} || Semestre: ${it.semAno} || Nota: ${it.nota}", Toast.LENGTH_LONG).show()
        }
    }


    fun todasDisc() {

        var disciplinas = DisciplinasDBHelper.lerTodasDisciplinas()
        listView = findViewById<ListView>(R.id.listView)
        val adapter = DisciplinasAdapter(this, disciplinas)
        listView.adapter = adapter

    }

    /*
    fun btn_deleteClick(v: View){
        btn_delete.setOnClickListener{
            Toast.makeText(this, "Clicou!", Toast.LENGTH_SHORT).show()

            var removerDisc = DisciplinasDBHelper.apagarDisciplina(id)
            if (removerDisc) {
                Toast.makeText(this, "Disciplina ${id} deletada com sucesso!", Toast.LENGTH_SHORT).show()
                todasDisc()
            } else {
                Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
            }

        }
    }
    */

    inner class DisciplinasAdapter(private val context: Context, private val dados: ArrayList<Disciplina>) : BaseAdapter() {

        private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return dados.size
        }


        override fun getItemId(p0: Int): Long {
            return dados[p0].id.toLong()
        }

        override fun getItem(p0: Int): Any {
            return dados[p0]
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val rowView = inflater.inflate(R.layout.list_disciplinas_view, p2, false)

            val siglaTextView = rowView.findViewById(R.id.disc_list_sigla) as TextView
            val nomeTextView = rowView.findViewById(R.id.disc_list_nome) as TextView
            val idDiscTextView = rowView.findViewById(R.id.disc_list_id) as TextView
            val semestreTextView = rowView.findViewById(R.id.disc_list_semestre) as TextView
            val notaTextView = rowView.findViewById(R.id.disc_list_nota) as TextView
            val btn_delete = rowView.findViewById(R.id.btn_delete) as ImageButton
            val btn_edit = rowView.findViewById(R.id.btn_edit) as ImageButton


            val disciplinas = getItem(p0) as Disciplina
            siglaTextView.text = disciplinas.sigla
            nomeTextView.text = disciplinas.nome
            semestreTextView.text = disciplinas.semAno.toString()
            notaTextView.text = disciplinas.nota.toString()
            idDiscTextView.text = disciplinas.id.toString()


            val id = idDiscTextView.text.toString()
            val sigla = siglaTextView.text.toString()
            val nome = nomeTextView.text.toString()
            val semestre = semestreTextView.text.toString()
            val nota = notaTextView.text.toString()

            btn_delete.setOnClickListener {
                var removerDisc = DisciplinasDBHelper.apagarDisciplina(id)
                if (removerDisc) {
                    Toast.makeText(context, "Disciplina ${id} deletada com sucesso!", Toast.LENGTH_SHORT).show()
                    todasDisc()
                } else {
                    Toast.makeText(context, "Erro", Toast.LENGTH_SHORT).show()
                }
            }

            btn_edit.setOnClickListener{

                val i = Intent(context,Form_Disciplinas::class.java)
                i.putExtra("disc_id", id)
                i.putExtra("sigla", sigla)
                i.putExtra("nome", nome)
                i.putExtra("semestre", semestre)
                i.putExtra("nota", nota)
                startActivity(i)



            }

            return rowView
        }
    }
}




