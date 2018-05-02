package Teste1

 class Cliente (var nome:String, val cpf: Long, var idade:Int, var email:String)

 class Pessoal(val cpf1: Long){
    constructor(cpf1: Long, nome1: String, email1: String) : this(cpf1)

    var idade1 : Int = 0
        set(value){
           field = value-10
        }


 }

class Pessoa2 (var nome3:String)

/*
class Person (val nome:String){
    constructor(nome:String, pai: Person, mae:Person) : this.nome {
        pai.children.add(this)
        mae.children.add(this)
    }
    */
