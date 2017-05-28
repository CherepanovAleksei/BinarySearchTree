/**
 *
 * by Cherepanov Aleksei (PI-171)
 *
 * mrneumann888@gmail.com
 *
 **/

package BinaryTree

class logic<K: Comparable<K>, V> {
    fun display(node: BinNode<K, V>) {
        for (i in 1..node.height()) print("    |")
        println("${node.key}" + "(${node.value})")
    }
}