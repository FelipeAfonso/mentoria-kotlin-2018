package Estudante1

/*
    ADICIONAR E TESTAR:
    ADICIONAR OFERECIMENTO USANDO SEMESTRE E SIGLA DA DISCIPLINA
    ALUNO SERÁ RELACIONADO A DISCIPLINA A PARTIR DO OFERECIMENTO
    CONCEITO: A RELAÇÃO ENTRE ALUNO E DISCIPLINA É MEDIADA PELO OFERECIMENTO
 */

fun main(args: Array<String>) {

    var d1 = DisciplinaCursada("Linguagem de Programação I", 1, "LP1")
    var d2 = DisciplinaCursada("Linguagem de Programação II", 2, "LP2")
    var d2_1 = DisciplinaCursada("Linguagem de Programação II", 3, "LP2")
    var d3 = DisciplinaCursada("Linguagem de Programação III", 2, "LP3")
    var e1 = Estudante1("Vinicius", "19-03-1999", 2017)
    e1.disc.add(d1)
    e1.disc.add(d2)
    e1.disc.add(d2_1)
    e1.disc.add(d3)
    e1.disc[1].nota = 9.0
    e1.disc[2].nota = 3.0
    e1.disc[3].nota = 8.0
    e1.disc[4].nota = 6.0
    e1.atualizaStatus()


    e1.exibeBoletim()
    /*
    var turma : MutableList<Estudante1> = mutableListOf(alunoA, alunoB)

    for(i in 0 until turma.size){
        turma[i].exibeEstudante()
    }
    */

    // Deletar Estudantes
    //turma.removeAt(2)


}