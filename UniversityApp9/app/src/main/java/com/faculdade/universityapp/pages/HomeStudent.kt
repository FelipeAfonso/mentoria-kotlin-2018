package com.faculdade.universityapp.pages

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.BaseTransientBottomBar.LENGTH_INDEFINITE
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.faculdade.universityapp.R
import com.faculdade.universityapp.bd.EstudanteBDHelper
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.android.synthetic.main.activity_home_student.*


class HomeStudent : AppCompatActivity() {
    private val TAG = "HomeStudent"
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var latitudeText : TextView
    private lateinit var longitudeText : TextView

    private var locationManager : LocationManager? = null

    // Verifica se a localização está ativada ---- Funcionando
    private val isLocationEnabled: Boolean
        get() {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        }

    // Chama a requisição de ativação da localização caso não esteja ativa ------- Funcionando
    private fun checkLocation(): Boolean {
        if (!isLocationEnabled) {
            showAlert()
        }
        return isLocationEnabled
    }

    // Abre o diálogo para ativar a localização ----------- Funcionando
    private fun showAlert() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Ativar localização")
                .setMessage("Sua localização está desativada.\nPor favor, ative a localização para " + "usar esse aplicativo")
                .setPositiveButton("Ativar") { paramDialogInterface, paramInt ->
                    val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(myIntent)
                }
                .setNegativeButton("Cancelar") { paramDialogInterface, paramInt -> }
        dialog.show()
    }



    lateinit var EstudanteBDHelper : EstudanteBDHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_student)

        latitudeText = findViewById(R.id.latitude_tv)
        longitudeText = findViewById(R.id.longitude_tv)

        fusedLocationClient = FusedLocationProviderClient(this) // Instanciou o Location Client

        checkLocation()

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

    override fun onStart() {
        super.onStart()
        if (!verificarPermissao()) {
            Toast.makeText(this,"Não tem permissão ${verificarPermissao()}", Toast.LENGTH_SHORT).show()
            requisitarPermissaoLocal()
        } else {
            obterLocalizacao()
        }
    }

    // Obtem os valores de latitude e longitude e atribui as TextViews
    @SuppressLint("MissingPermission")
    private fun obterLocalizacao(){
        fusedLocationClient.lastLocation.addOnCompleteListener(this){
            if (it.isSuccessful && it.result != null){
                latitudeText.text = it.result.latitude.toString()
                longitudeText.text = it.result.longitude.toString()
            }else{
                Toast.makeText(this, "Erro! ${it.exception}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Verifica as permissões
    private fun verificarPermissao() = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requisitarPermissaoLocal(){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_PERMISSIONS_REQUEST_CODE)
        Toast.makeText(this, "Requisitou Permissao", Toast.LENGTH_SHORT).show()
    }




}
