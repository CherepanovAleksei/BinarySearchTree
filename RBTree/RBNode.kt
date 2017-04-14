/**
 *
 * by Cherepanov Aleksei (PI-171)
 *
 * mrneumann888@gmail.com
 *
 **/

package RBTree

class RBNode<K: Comparable<K>, V>(var key:K,var value:V,var color:Boolean=false){
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
}