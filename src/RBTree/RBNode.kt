/**
 *
 * by Cherepanov Aleksei (PI-171)
 *
 * mrneumann888@gmail.com
 *
 **/

package RBTree

class RBNode<K: Comparable<K>, V>(var key:K,var value:V,var color: Boolean = true){
    var left: RBNode<K,V>? = null
    var right: RBNode<K,V>? = null
    var parent: RBNode<K,V>? = null

    fun height(): Int {
        var thisNode = this
        var count = 0
        while (thisNode.parent != null) {
            thisNode = thisNode.parent!!
            count++
        }
        return count
    }

    fun isLeaf(): Boolean = (this.left == null) && (this.right == null)

    fun brother(): RBNode<K, V>? {
        if (this == this.parent?.left) return this.parent!!.right
        return this.parent?.left
    }

    fun recoloring() {
        this.color = this.color == false
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as RBNode<*, *>

        if (key != other.key) return false
        if (value != other.value) return false
        if (parent != other.parent) return false
        if (color == other.color) return false
        if (left != other.left) return false
        if (right != other.right) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (parent?.hashCode() ?: 0)
        result = 31 * result + color.hashCode()
        result = 31 * result + (left?.hashCode() ?: 0)
        result = 31 * result + (right?.hashCode() ?: 0)
        return result
    }
}