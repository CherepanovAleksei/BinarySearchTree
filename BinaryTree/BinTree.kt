/**
 *
 * by Cherepanov Aleksei (PI-171)
 *
 * mrneumann888@gmail.com
 *
 **/

package BinaryTree
import Interfaces.Tree

class BinarySearchTree<K:Comparable<K>,V>: Tree<K, V>, Iterable<BinNode<K, V>>{
    private var root: BinNode<K, V>? = null

    override fun insert(key:K,value:V) {
        val newNode = BinNode(key, value)

        if (this.root == null){
            this.root = newNode
            return
        }
        var buff = root
        var parent:BinNode<K,V>?
        while (true){
            parent=buff

            if(newNode.key > parent!!.key){
                buff=parent.right
                if (buff == null){
                    parent.right=newNode
                    newNode.parent=parent
                    return
                }
            }else if(newNode.key < buff!!.key){
                buff=parent.left
                if (buff == null){
                    parent.left=newNode
                    newNode.parent=parent
                    return
                }
            }else if(newNode.key == buff.key){
                buff.value=newNode.value
                return
            }
        }
    }

    override fun delete(key:K){
        val delNode = searchNode(key) ?: return
        if (delNode.left == null){
            transplant(delNode,delNode.right)
        }
        else if (delNode.right == null) {
            transplant(delNode,delNode.left)
        }
        else {
            val y=searchMin(delNode.right)
            if (y?.parent != delNode){
                transplant(y,y?.right)
                y?.right=delNode.right
                y?.right?.parent=y
            }
                transplant(delNode,y)
                y?.left=delNode.left
                y?.left?.parent=y

        }
    }
    private fun transplant(u: BinNode<K,V>?,v: BinNode<K,V>?){
        if (u?.parent ==null) this.root=v
        else if(u == u.parent!!.left) u.parent!!.left=v
        else u.parent?.right=v
        if (v!=null) v.parent= u?.parent
    }

    override fun search(key: K)=searchNode(key)?.value
    private fun searchNode(key: K, node: BinNode<K,V>?=root): BinNode<K,V>? {
        if(node==null) return null
        if(key == node.key)return node
        if(key < node.key) return searchNode(key, node.left)
        else return searchNode(key, node.right)
    }

    private fun searchMax(node: BinNode<K, V>?=root): BinNode<K, V>? {
        var max = node
        while (max?.right != null) {
            max = max.right
        }
        return max
    }

    private fun searchMin(node: BinNode<K, V>?=root): BinNode<K, V>? {
        var min = node
        while (min?.left != null) {
            min = min.left
        }
        return min
    }

    override fun iterator(): Iterator<BinNode<K, V>> {
        return (object : Iterator<BinNode<K, V>> {
            var node = searchMax()
            var next = searchMax()
            val last = searchMin()

            override fun hasNext(): Boolean {
                return node != null && node!!.key >= last!!.key
            }

            override fun next(): BinNode<K, V> {
                next = node
                node = nextSmaller(node)
                return next!!
            }
        })
    }
    private fun nextSmaller(node: BinNode<K, V>?): BinNode<K, V>?{
        var smaller = node ?: return null
        if ((smaller.left != null)) {

            return searchMax(smaller.left)
        }
        else if ((smaller == smaller.parent?.left)) {
            while (smaller == smaller?.parent?.left) {
                smaller = smaller?.parent!!
            }
        }
        return smaller.parent
    }
}
