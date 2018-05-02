package Teste3

fun main(args: Array<String>){
    // Testar e demonstrar o uso das estruturas de repetição (formas de usar, proibições etc
    var x: Any = 14
    if(x == 14 && x is Int){
        println("Satisfaz ambas")
    }
    when(x){
        //14  && is Int && 0..15 -> println("Satisfaz as 3")
        14 , is Int, 0..15 -> println("Satisfaz uma das 3")
        14 -> println("eh 14")
        0..15 -> println("Entre 0 e 15")
        !is Int -> println("X nao eh é Int")
        is Int -> println("X é Inteiro")
        is String -> println("X é String")
        is Long -> println("X é Long")
        is Char -> println("X é Char")
        else -> println("X é desconhecido")
    }
    /*
    for (i in 1..10){
        if (i == 4) x = "Ola" else println("4\n")
        if (i == 6) x = 432432423 else println("6\n")
        if (i == 8) x = "v" else println("8\n")
    }
    */

    /*
    for (i in 6 downTo 0 step 2) {
        println(i)
    }
    */


}