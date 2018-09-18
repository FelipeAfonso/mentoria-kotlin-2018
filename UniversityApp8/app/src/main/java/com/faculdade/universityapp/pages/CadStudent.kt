package com.faculdade.universityapp.pages

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.faculdade.universityapp.R
import kotlinx.android.synthetic.main.activity_home_student.view.*
import kotlinx.android.synthetic.main.estudante.*
import kotlinx.android.synthetic.main.estudante.view.*
import com.faculdade.universityapp.bd.*


class CadStudent : AppCompatActivity(){

    lateinit var EstudanteBDHelper : EstudanteBDHelper
    val REQUEST_CODE = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.estudante)

        EstudanteBDHelper = EstudanteBDHelper(this)

        btn_TakePic.setOnClickListener {
            val CameraRequest = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (CameraRequest.resolveActivity(packageManager) != null) {
                startActivityForResult(CameraRequest, REQUEST_CODE)
            }
        }

        var validado = 0
        var nome = nomeCS.text
        var nasc = nascimentoCS.text
        var anoIngresso = ingressoCS.text


        if (nome.isBlank()){
            validado++
        }else {
            validado--
        }
        if (nasc.isBlank()){
            validado++
        }else{
            validado--
        }

        if (anoIngresso.isBlank()){
            validado++
        }else{
            validado--
        }

            cadNewStudent.setOnClickListener {
                if (validado == 3){

                    var stud_nome = nome.toString()
                    var stud_nasc = nasc.toString()
                    var stud_ingresso = anoIngresso.toString().toInt()

                    try {
                        EstudanteBDHelper.inserirEstudante(Estudante(0,stud_nome,stud_nasc,stud_ingresso))

                        val i = Intent(applicationContext, HomeStudent::class.java)

                        startActivity(i)
                    }catch (e : Exception){
                        Toast.makeText(this,"Não foi possível cadastrar o estudante",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"Preencha todos os campos",Toast.LENGTH_SHORT).show()
                }
            }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode.equals(REQUEST_CODE)){
            if (resultCode == Activity.RESULT_OK && data != null){
                Foto.setImageBitmap(data.extras.get("data") as Bitmap)
            } else {
                Toast.makeText(this,"Não foi possível salvar a imagem",Toast.LENGTH_SHORT).show()
            }
        }
}
}
