import java.util.Scanner
import java.util.Locale

fun main() {

    val leitor = Scanner(System.`in`)
    val input: String = leitor.nextLine().trim()
    val valores = input.split(" ")
    val H: Double = valores[0].toDouble()
    val P:  Double = valores[1].toDouble()
    val media: Double = H / P
    println(String.format(Locale.US,"%.2f", media))
    leitor.close()
}