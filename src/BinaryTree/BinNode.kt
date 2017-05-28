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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as BinNode<*, *>

        if (key != other.key) return false
        if (value != other.value) return false
        if (parent != other.parent) return false
        if (left != other.left) return false
        if (right != other.right) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (parent?.hashCode() ?: 0)
        result = 31 * result + (left?.hashCode() ?: 0)
        result = 31 * result + (right?.hashCode() ?: 0)
        return result
    }

}