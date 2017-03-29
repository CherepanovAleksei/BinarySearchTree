/**
 * Created by Алексей
 */
import java.io.File
import java.io.FileNotFoundException


//import javax.print.attribute.IntegerSyntax

interface Tree<K: Comparable <K>, V> {
    var root: Node<K, V>?
    fun insert(newNode: Node<K, V>)
    fun delete(key:Int)
    fun leftRotate(x: Node<K,V>?)
    fun rightRotate(x: Node<K,V>?)
    fun search(node: Node<K,V>?/*=root*/, key: K): Node<K,V>?
    fun searchMin(node: Node<K, V>?=root): Node<K, V>?
    fun searchMax(node: Node<K, V>?=root): Node<K, V>?
    fun display(node: Node<K,V>?, n:Int)
}

class Node<K: Comparable<K>, V>(var key:K,var value:V,var color:Boolean=false){
    var left: Node<K,V>? = null
    var right: Node<K,V>? = null
    var parent: Node<K,V>? = null
    fun height(): Int {
        var current = this
        var count = 0
        while (current.parent != null) {
            current = current.parent!!
            count++
        }
        return count
    }

    fun isLeaf(): Boolean = (this.left == null) && (this.right == null)

    fun brother(): Node<K, V>? {
        if (this == this.parent?.left) return this.parent!!.right
        return this.parent?.left
    }
}

class logic<K: Comparable<K>, V> {
    fun print(node: Node<K, V?>) {
        if (node == null) return

        for (i in 1..node.height())
            print(" |")
        when {
            node.color -> println(27.toChar() + "[31m${node.key }" + node.value + 27.toChar() + "[0m")
            else -> println(27.toChar() + "[30m${node.key}" + node.value + 27.toChar() + "[0m")
        }
    }
}

/*
class BinarySearchTree: Tree{
    override var root: Node? = null

    override fun insert(n: Int) {
        var newNode=Node(n)
        if (this.root == null){
            this.root = newNode
            return
        }
        var buff = root
        var parent:Node?
        while (true){
            parent=buff

            if(newNode.key.compareTo(parent!!.key) > 0){
                buff=parent.right
                if (buff == null){
                    parent.right=newNode
                    return
                }
            }else if(newNode.key.compareTo(buff!!.key) < 0){
                buff=parent.left
                if (buff == null){
                    parent.left=newNode
                    return
                }
            }else if(newNode.key.equals(buff.key)){
                return
            }
        }
    }

    override fun display(node: Node?, n:Int) {
        if (node!!.left != null){
            display(node.left,n+1)
        }
        var i:Int=0
        while (i<n){
            print("   ")
            i++
        }
        print(node.key)
        println()

        if(node.right!=null){
            display(node.right,n+1)
        }
    }

    override fun delete(key: Int) {

    }
}
*/
class RedBlackTree<K:Comparable<K>,V>:Tree<K,V>, Iterable<Node<K, V>>{
    override var root: Node<K,V>? = null
    override fun iterator(): Iterator<Node<K, V>> {
        return (object : Iterator<Node<K, V>> {
            var node = searchMax()
            var next = searchMax()
            val last = searchMin()

            override fun hasNext(): Boolean {
                return (node != null) && (node!!.key >= last!!.key)
            }

            override fun next(): Node<K, V> {
                next = node
                node = nextSmaller(node)
                return next!!
            }
        })
    }

    fun nextSmaller(node: Node<K, V>?): Node<K, V>?{
        var smaller = node
        if(node==null) return null
//nil?? var smaller = node ?: return null
        when {
            (smaller?.left != null) -> {
                return searchMax(smaller.left)
            }
            (smaller == smaller?.parent?.left) -> {
                while (smaller == smaller?.parent?.left) {
                    smaller = smaller?.parent!!
                }
            }
        }
        return smaller?.parent
    }

    override fun insert(newNode: Node<K, V>){
        var y:Node<K,V>?=null
        var x:Node<K,V>?=this.root
        while (x!=null){
            y=x
            if(newNode.key < x!!.key) x= x.left else x=x.right
        }
        newNode.parent=y
        if(y==null) {

            this.root=newNode
            //this.root!!.parent=nil
        }
        else if(newNode.key<y!!.key){
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

    fun stabilization(newNode: Node<K, V>?){
        var z = newNode
        var y:Node<K,V>?
        while(z!!.parent?.color==true){
            if (z.parent== z.parent?.parent?.left){
                y=z.parent!!.parent!!.right
                if(y?.color ==true){
                    z.parent!!.color=false
                    y?.color=false
                    z.parent!!.parent!!.color=true
                    z=z.parent!!.parent
                }else if(z== z.parent!!.right){
                    z=z.parent
                    leftRotate(z)
                }else if(z==z.parent!!.left) {
                    z.parent!!.color = false
                    z.parent!!.parent!!.color = true
                    rightRotate(z.parent!!.parent)
                }
            }else{
                y= z.parent?.parent?.left
                if(y?.color==true){
                    z.parent!!.color=false
                    y?.color=false
                    z.parent!!.parent!!.color=true
                    z=z.parent!!.parent
                }else if(z== z.parent!!.left){
                    z=z.parent
                    rightRotate(z)
                }else if(z== z.parent!!.right) {
                    z.parent!!.color = false
                    z.parent?.parent?.color = true
                    leftRotate(z.parent!!.parent)
                }
            }
        }
        this.root!!.color=false
    }

    override fun leftRotate(x: Node<K,V>?){ //node.right!=nil
        var y:Node<K,V>?= x?.right
        x?.right= y?.left
        //if(y?.left!=nil)
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

    override fun rightRotate(x: Node<K,V>?){ //node.left!=nil
        var y:Node<K,V>?= x!!.left
        x.left= y!!.right
        //if(x.right!=nil)
        y.right?.parent=x
        y.parent=x.parent
        if(x.parent==null){
            this.root=y
        }
        if(x==x.parent!!.right){
            x.parent!!.right=y
        }
        if(x==x.parent?.left){
            x.parent!!.left=y
        }
        y.right=x
        x.parent=y
    }

    override fun search(node: Node<K,V>?, key: K): Node<K,V>? {
        if(node==null) return null
        if(key== node?.key)return node
        if(key.equals(node?.key)) return search(node?.left,key)
        else return search(node?.right, key)
    }

    override fun searchMax(node: Node<K, V>?): Node<K, V>? {
        var max = node
        while (max?.left != null) {
            max = max?.left
        }
        return max
    }

    override fun searchMin(node: Node<K, V>?): Node<K, V>? {
        var min = node
        while (min?.left != null) {
            min = min?.left
        }
        return min
    }
    override fun delete(key:Int){

    }



    override fun display(node: Node<K,V>?, n:Int){
        if (node!!.right != null){
            display(node.right,n+1)
        }
        var i:Int=0
        while (i<n){
            print("    |")
            i++
        }
        if (node.color) {
            print(27.toChar()+"[31m${node.key}"+27.toChar()+"[0m")
        } else {
            print(27.toChar()+"[30m${node.key}"+27.toChar()+"[0m")
        }
        println()

        if(node.left!=null){
            display(node.left,n+1)
        }
    }
}

fun main(array: Array<String>) {
    //var bst=BinarySearchTree()
    var bst = RedBlackTree<Int, String?>()
/*    var flagInput: Boolean=true
    try{
        line = File("C:\\Users\\Алексей\\Desktop\\Tree\\src\\input.txt").readText()
        val parts = line.split(' ')
    }catch(a: FileNotFoundException){
        println(message = "File not found, please, input it yourself")
        flagInput=false

    }*/
    //var input = readLine()
    var flagKV: Boolean = false
    var key: Int = 0
    var value: String?
    while (true) {
        val input = readLine()
        when (input) {
            "-f" -> {
                print("Key to found: ")
                if (bst.search(bst.root, readLine()!!.toInt()) != null) println("Key found")
                else println("Key not found")
            }
            "-p" -> {
                //bst.display(bst.root,0)
                for (i in bst) {
                    logic<Int, String>().print(i)
                }
            }
            "-d" -> {
                println("Key to delete: ")
                bst.delete(readLine()!!.toInt())
            }
            "-q" -> return
            else -> {
                if (flagKV) {
                    value = input
                    val newNode = Node(key, value)
                    bst.insert(newNode)
                    flagKV = false
                } else {
                    key = input?.toInt()!!
                    flagKV = true
                }

            }
        }
    }
}
