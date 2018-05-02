package Teste2

import java.util.*

class Pessoa {
    var cpf = CharArray(15)
    var cep = CharArray(10)
    var telefone = CharArray(10)
    var nascimento = CharArray(10) //Omitido = a visibilidade de pacote?
    var num_casa: Int = 0
    var cidade: String? = null
    var estado: String? = null
    var pais: String? = null
    var nome_completo: String? = null
    var nome_mae: String? = null

    fun CalendarDemo(){
        val calendar = Calendar.getInstance()
        if (calendar.firstDayOfWeek == Calendar.MONDAY) {
            println("Hoje nao é sabado")


        } else {
            val teste = "Nao e sabado"
            println("""
                |f
                |w
                |w
                |w
                |w

|              calendario = $teste
            """.trimMargin())
        }
    }
}



