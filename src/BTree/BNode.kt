/**
 *
 * by Cherepanov Aleksei (PI-171)
 *
 * mrneumann888@gmail.com
 *
 **/

package BTree

class BNode<K: Comparable<K>> (var isLeaf: Boolean = true) {
    var keys = ArrayList<K>()

    var children = ArrayList<BNode<K>>()

    fun splitChildren(deg: Int, splitKey: Int) {
        val splitNode = this.children[splitKey]
        val newNode = BNode<K>(splitNode.isLeaf)

        for (i in 0..deg - 2) {
            newNode.keys.add(splitNode.keys[deg])
            splitNode.keys.removeAt(deg)
        }

        if (!splitNode.isLeaf)
            for (j in 0..deg - 1) {
                newNode.children.add(splitNode.children[deg])
                splitNode.children.removeAt(deg)
            }

        this.children.add(splitKey + 1, newNode)
        this.keys.add(splitKey, splitNode.keys[deg - 1])
        splitNode.keys.removeAt(deg - 1)
    }

    fun mergeChildren(number: Int) {
        val left = children[number]
        val right = children[number + 1]
        val key = keys[number]

        left.keys.add(key)
        left.keys.addAll(right.keys)

        if (!right.isLeaf)
            left.children.addAll(right.children)

        keys.removeAt(number)
        children.removeAt(number + 1)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as BNode<*>

        if (isLeaf != other.isLeaf) return false
        if (keys != other.keys) return false
        if (children != other.children) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isLeaf.hashCode()
        result = 31 * result + keys.hashCode()
        result = 31 * result + children.hashCode()
        return result
    }
}
