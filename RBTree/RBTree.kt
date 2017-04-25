/**
 *
 * by Cherepanov Aleksei (PI-171)
 *
 * mrneumann888@gmail.com
 *
 **/

package RBTree
import Interfaces.Tree

class RedBlackTree<K:Comparable<K>,V>:Tree<K,V>, Iterable<RBNode<K, V>>{
    /*private*/var root: RBNode<K,V>? = null

    override fun insert(key:K,value:V){
        val newNode = RBNode(key, value)
        var y:RBNode<K,V>?=null
        var x:RBNode<K,V>?=this.root
        while (x!=null){
            y=x

            when {
                newNode.key < x.key -> x= x.left
                newNode.key > x.key -> x=x.right
                newNode.key == x.key -> {
                    x.value = newNode.value
                    return
                }
            }
        }
        newNode.parent=y
        if(y==null) {
            this.root=newNode
        }
        else if(newNode.key<y.key){
            y.left=newNode
        }
        else{
            y.right=newNode
        }

        newNode.color=true
        newNode.right=null
        newNode.left=null
        this.stabilization(newNode)
    }

    fun stabilization(newNode: RBNode<K, V>?){
        var z = newNode
        var y:RBNode<K,V>?
        while(z!!.parent?.color==true){
            if (z.parent== z.parent?.parent?.left){
                y=z.parent!!.parent!!.right
                if(y?.color ==true){
                    z.parent!!.color=false
                    y.color=false
                    z.parent!!.parent!!.color=true
                    z=z.parent!!.parent
                }else if(z== z.parent!!.right){
                    z=z.parent
                    leftRotate(z!!)
                }else if(z==z.parent!!.left) {
                    z.parent!!.color = false
                    z.parent!!.parent!!.color = true
                    rightRotate(z.parent!!.parent!!)
                }
            }else{
                y= z.parent?.parent?.left
                if(y?.color==true){
                    z.parent!!.color=false
                    y.color=false
                    z.parent!!.parent!!.color=true
                    z=z.parent!!.parent
                }else if(z== z.parent!!.left){
                    z=z.parent
                    rightRotate(z!!)
                }else if(z== z.parent!!.right) {
                    z.parent!!.color = false
                    z.parent?.parent?.color = true
                    leftRotate(z.parent!!.parent!!)
                }
            }
        }
        this.root!!.color=false
    }

    override fun delete(key: K) {

    val node = searchNode(key, root) ?: return
    val min = searchMin(node.right)

    when {
        ((node.right != null) && (node.left != null))
        -> {
            val nextKey = min!!.key
            val nextValue = min.value
            delete(min.key)
            node.key = nextKey
            node.value = nextValue
        }
        ((node == root) && (node.right == null && node.left == null))
        -> {
            root = null
            return
        }
        (node.color == true && node.right == null && node.left == null)
        -> {
            if (node.key < node.parent!!.key) {
                node.parent!!.left = null
            } else {
                node.parent!!.right = null
            }
            return
        }
        (node.color == false && ((node.left != null) && (node.left!!.color == true)))
        -> {
            node.key = node.left!!.key
            node.value = node.left!!.value
            node.left = null
            return
        }
        (node.color == false && (node.right != null) && (node.right!!.color == true))
        -> {
            node.key = node.right!!.key
            node.value = node.right!!.value
            node.right = null
            return
        }
        else
        -> {
            deleteCase1(node)
        }
    }

    if (node.key == key) {
        if (node.key < node.parent!!.key) {
            node.parent!!.left = null
        } else {
            node.parent!!.right = null
        }
    }
    return
}

    private fun deleteCase1(node: RBNode<K,V>) {
        if (node == root) {
            node.color = false
            return
        }

        val brother = node.brother()

        if (brother!!.color) {
            node.parent!!.recoloring()
            brother.recoloring()
            if (node == node.parent!!.left) {
                leftRotate(node.parent!!)
            } else {
                rightRotate(node.parent!!)
            }

            deleteCase1(node)
            return
        }

        deleteCase2(node)

    }


    private fun deleteCase2(node: RBNode<K,V>) {
        val brother = node.brother()

        if (((brother!!.left == null) || !brother.left!!.color)
                && ((brother.right == null) || !brother.right!!.color))
        {
            node.color = false
            brother.recoloring()
            if (node.parent!!.color == true) {
                node.parent!!.recoloring()
                return
            }
            deleteCase1(node.parent!!)
            return
        }

        if (node == node.parent!!.left) {
            deleteCase3(node)
        } else {
            deleteCase4(node)
        }
    }

    private fun deleteCase3(node: RBNode<K,V>) {
        val brother = node.brother()

        if ((brother!!.right == null) || brother.right!!.color == false) {
            brother.recoloring()
            brother.left!!.recoloring()
            rightRotate(brother)
            deleteCase1(node)
            return
        }
        deleteCase3_2(node)
    }

    private fun deleteCase4(node: RBNode<K,V>) {
        val brother = node.brother()

        if ((brother!!.left == null) || brother.left!!.color == false) {
            brother.recoloring()
            brother.right!!.recoloring()
            leftRotate(brother)
            deleteCase1(node)
            return
        }
        deleteCase4_2(node)
    }

    private fun deleteCase3_2(node: RBNode<K,V>) {
        val brother = node.brother()

        if ((brother!!.right != null) && brother.right!!.color == true) {
            brother.color = node.parent!!.color
            node.color = false
            node.parent!!.color = false
            brother.right!!.color = false
            leftRotate(node.parent!!)
            return
        }
    }

    private fun deleteCase4_2(node: RBNode<K,V>) {
        val brother = node.brother()

        if ((brother!!.left != null) && brother.left!!.color == true) {
            brother.color = node.parent!!.color
            node.color = false
            node.parent!!.color = false
            brother.left!!.color = false
            rightRotate(node.parent!!)
            return
        }
    }

    private fun leftRotate(x: RBNode<K,V>?){
        val y:RBNode<K,V>?= x?.right
        x?.right= y?.left
        y?.left?.parent=x
        y?.parent =x?.parent
        if(x?.parent==null){
            this.root=y
        }
        if(x == x?.parent?.left){
            x?.parent?.left=y
        }
        if(x== x?.parent?.right){
            x?.parent?.right=y
        }
        y?.left =x
        x?.parent=y
    }

    private fun rightRotate(x: RBNode<K,V>?){
        val y:RBNode<K,V>?= x!!.left
        x.left= y!!.right
        y.right?.parent=x
        y.parent=x.parent
        if(x.parent==null){
            this.root=y
        }
        if(x==x.parent?.right){
            x.parent?.right=y
        }
        if(x==x.parent?.left){
            x.parent!!.left=y
        }
        y.right=x
        x.parent=y
    }

    override fun search(key: K)=searchNode(key)?.value
    /*private*/fun searchNode(key: K, node: RBNode<K,V>?=root): RBNode<K,V>? {
        if(node==null) return null
        if(key == node.key)return node
        if(key < node.key) return searchNode(key, node.left)
        else return searchNode(key, node.right)
    }

    private fun searchMax(node: RBNode<K, V>?=root): RBNode<K, V>? {
        var max = node
        while (max?.right != null) {
            max = max.right
        }
        return max
    }

    private fun searchMin(node: RBNode<K, V>?=root): RBNode<K, V>? {
        var min = node
        while (min?.left != null) {
            min = min.left
        }
        return min
    }

    override fun iterator(): Iterator<RBNode<K, V>> {
        return (object : Iterator<RBNode<K, V>> {
            var node = searchMax()
            var next = searchMax()
            val last = searchMin()

            override fun hasNext(): Boolean {
                return (node != null) && (node!!.key >= last!!.key)
            }

            override fun next(): RBNode<K, V> {
                next = node
                node = nextSmaller(node)
                return next!!
            }
        })
    }

    private fun nextSmaller(node: RBNode<K, V>?): RBNode<K, V>?{
        var smaller = node ?: return null
        if ((smaller.left != null)) {
            return searchMax(smaller.left!!)
        }
        else if ((smaller == smaller.parent?.left)) {
            while (smaller == smaller.parent?.left) {
                smaller = smaller.parent!!
            }
        }
        return smaller.parent
    }

}