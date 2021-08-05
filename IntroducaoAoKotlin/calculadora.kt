// Exercício aula Introdução ao Kotlin
// 
//  Calculadora (beeem básica)
// 

fun main() {
   
   val operador1 = 4f      // operador 01
   val operador2 = 2f      // operador 02 
   var operacao :String   // operação + - / *
    
   operacao  = "+" 
   println(calcula(operador1, operador2, operacao))
   
   operacao  = "-"
   println(calcula(operador1, operador2, operacao))
   
   operacao  = "*"
   println(calcula(operador1, operador2, operacao))
   
   operacao  = "/"
   println(calcula(operador1, operador2, operacao))
}

fun calcula(op1:Float, op2:Float, oper:String):String{

   var resultado :Float 
    
   when (oper) {
       
       "+" -> {
           resultado = op1.plus(op2)           
       }
       "-" -> {
           resultado = op1.minus(op2)  
       }
       "*" -> {
           resultado = op1.times(op2)  
       }
       "/" -> {
           resultado = op1.div(op2)  
       }
       else -> {
           return "Operação inválida! Valores válidos(+, -, * e /)"
       }
   } 
   
   return "$op1 $oper $op2 = $resultado"
}