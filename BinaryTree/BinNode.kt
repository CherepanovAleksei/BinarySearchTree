/**
 *
 * by Cherepanov Aleksei (PI-171)
 *
 * mrneumann888@gmail.com
 *
 **/

package BinaryTree

class BinNode<K: Comparable<K>, V>(var key:K,var value:V){
    var left: BinNode<K,V>? = null
    var right: BinNode<K,V>? = null
    var parent: BinNode<K,V>? = null

    fun height(): Int {
        var thisNode = this
        var count = 0
        while (thisNode.parent != null) {
            thisNode = thisNode.parent!!
            count++
        }
        return count
    }
}