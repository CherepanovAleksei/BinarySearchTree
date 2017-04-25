/**
 *
 * by Cherepanov Aleksei (PI-171)
 *
 * mrneumann888@gmail.com
 *
 **/

package RBTree

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