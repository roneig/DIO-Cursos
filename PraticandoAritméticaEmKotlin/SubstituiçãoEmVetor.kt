/*
   Substituição em Vetor

Faça um programa que leia um vetor X[10]. Substitua a seguir, todos os valores
nulos e negativos do vetor X por 1. Em seguida mostre o vetor X.

- Entrada
A entrada contém 10 valores inteiros, podendo ser positivos ou negativos.

- Saída

Para cada posição do vetor, escreva "X[i] = x", onde i é a posição do vetor
e x é o valor armazenado naquela posição.

*/

fun main(args: Array<String>) {
    //val list = IntArray(10) {readline()!!.toInt()}
    val list = intArrayOf(-78945, -260, 6, -5, -294, 950, -50000, -30645, 0, -583)   // valores informados

    for( x in 0 until list.size ) {
        if (list[x] <= 0) list[x] = 1
    }
    for( i in list.indices ) { println("X[$i] = ${list[i]} ") }

}

