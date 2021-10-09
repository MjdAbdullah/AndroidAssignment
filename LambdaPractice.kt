import kotlin.random.Random

/*

-- Lambda Practice --
-- author : Mjd Abdullah --

 */
fun main() {
    // one function (with no parameters)
    fun DisplyFun(){
        println("Riyadh is capital of KSA . " )
    }
    // Lambda
    val Disply = {  println("Riyadh is capital of KSA . " ) }
    Disply()
    //----------------------------------------------------------------
    // Two functions that take in one or more parameter
    // Two functions that return a value and rewrite
    //----------------------------------------------------------------
    fun CheckIntFun(UserInput:String): Int {
        if(UserInput.toIntOrNull() == null){
            return 0 // return 0 if it's not number
        }
        else{
            return UserInput.toInt()
        }
    }
    // Lambda of CheckIntFun
    var CheckInt : (String) -> Int = {
        if(it.toIntOrNull() == null) 0
        else it.toInt()
    }
    println(CheckInt("hi"))
    //----------------------------------------------------------------
    fun Available(C:Char):Boolean{
        for(c in "Letter"){
            if(c == C){
                return false
            }
        }
        return true
    }//Available
    // Lambda of isAlphabetsFun
    var isAlphabets : (Char) -> Boolean = {"Letter".contains(it)}
    println(isAlphabets('L'))

    //----------------------------------------------------------------
    /*
    Make use of a data class to create a Player class with attributes name, age, height (in cm)
    Create a list of 20 players
    Use a lambda expression to sort and print the list by each attribute

    */
    var playerList = arrayListOf<Player>()
    for (i in 0..19){
        playerList.add(i,Player("Player$i", 25 ,Random.nextInt(180,195).toDouble()))
    }
    val MyLa : (ArrayList<Player>) -> Unit = {
        with(it) {
            sortBy { it.height }
            removeAll(filter { it.height <= 181.0 })
            forEach { println("${it.name} ${it.age} ${it.height}") }
        }
    }
    MyLa(playerList)
}

data class Player(var name : String, var age : Int, var height : Double) {

}
