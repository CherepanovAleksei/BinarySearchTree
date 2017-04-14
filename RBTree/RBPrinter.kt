package RBTree

/**
 * Created by Алексей on 12.04.2017.
 */
class logic<K: Comparable<K>, V> {
    fun display(node: RBNode<K, V>) {
        for (i in 1..node.height()) print("    |")

        if(node.color) {
            println(27.toChar() + "[31m${node.key}" + '(' + node.value + ')' + 27.toChar() + "[0m")
        }else {
            println(27.toChar() + "[30m${node.key}" + '(' + node.value + ')' + 27.toChar() + "[0m")
        }
    }
}