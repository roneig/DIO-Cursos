fun main() {
    val inteiros: MutableList <Int> = mutableListOf <Int> (-5, 0, -3, -4, 12)
    //inteiros.add(5, 5)

    val pares: MutableList<Int> = ArrayList()
    val impares: MutableList<Int> = ArrayList()
    val positivos: MutableList<Int> = ArrayList()
    val negativos: MutableList<Int> = ArrayList()

    for (i in inteiros){
        if ((i % 2) == 0) {pares.add(i)}
        if ((i % 2) != 0) {impares.add(i)}
        if  (i > 0)       {positivos.add(i)}
        if  (i < 0)       {negativos.add(i)}
    }
    println("${pares.size} valor(es) par(es)")
    println("${impares.size} valor(es) impar(es)")
    println("${positivos.size} valor(es) positivo(s)")
    println("${negativos.size} valor(es) negativo(s)")
}
