import RBTree.RedBlackTree
import RBTree.logic
import RBTree.RBNode

/**
 * Created by mrneumann888@gmail.com
 */
fun main(array: Array<String>) {
    //var bst=BinarySearchTree()
    val bst = RedBlackTree<Int, String>()
    var flagKV: Boolean = false
    var key: Int? = 0
    var value: String? = null
    while (true) {
        val input = readLine()
        when (input) {
            "-f" -> {
                print("Key to found: ")
                if (bst.search(readLine()!!.toInt()) != null) println("Key found")
                else println("Key not found")
            }
            "-p" -> {
                //bst.display(bst.root,0)
                for (i in bst) {
                    logic<Int, String>().display(i)
                }
            }
            "-d" -> {
                println("Key to delete: ")
                bst.delete(readLine()!!.toInt())
            }
            "-q" -> return
            else -> {
                if (flagKV) {
                    if (input != null) value = input.toString()
                    val newNode = RBNode(key!!, value!!)
                    bst.insert(key, value)
                    flagKV = false
                } else {
                    key = input?.toIntOrNull()
                    if (key != null) flagKV = true
                }

            }
        }
    }
}