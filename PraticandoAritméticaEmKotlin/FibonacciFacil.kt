/*
   Fibonacci Fácil

A seguinte sequência de números 0 1 1 2 3 5 8 13 21... é conhecida como série
de Fibonacci. Nessa sequência, cada número, depois dos 2 primeiros, é igual à
soma dos 2 anteriores. Escreva um algoritmo que leia um inteiro N (N < 46) e
mostre os N primeiros números dessa série.

- Entrada

O arquivo de entrada contém um valor inteiro N (0 < N < 46).

- Saída

Os valores devem ser mostrados na mesma linha, separados por um espaço em
branco. Não deve haver espaço após o último valor.

*/

fun main(args: Array<String>) {
    //val n = readline()!!.toInt()
    val n = 8       //0, 1, 1, 2, 3, 5, 8, 13,  tamanho da série
    var t1 = 0
    var t2 = 1
    var sum: Int = 0

    for ( i in 1..(n-1)) {
        print("$t1 ")
        when (i){
            1 -> t1 = 1
            2 -> t1 = 1
            else -> {
                sum = t1 + t2
                t2 = t1
                t1 = sum
            }
        }
    }
    print("$t1")
    println()
}