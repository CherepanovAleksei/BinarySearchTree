/**
 * Created by Алексей on 28.02.2017.
 */
import java.io.File
import java.io.FileNotFoundException

interface Tree {
    var root: Node?
    fun insert(key:Int): Node?
    fun delete(key:Int)
    fun search(node: Node?, key: Int): Node?
    fun display(node: Node?, n:Int)
}

class Node(key:Int){
    var left: Node? = null
    var right: Node? = null
    var parent: Node? = null
    var key = key
    var color:Boolean=false //black
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
class RedBlackTree:Tree{

    val nil=Node(0)
    override var root: Node? = nil

    override fun insert(key:Int):Node?{
        var newNode=Node(key)
        var y:Node?=this.nil
        var x:Node?=this.root
        while (x!=this.nil){
            y=x
            if(newNode.key < x!!.key) x= x.left else x=x.right
        }
        newNode.parent=y
        if(y==this.nil) {

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
        newNode.right=nil
        newNode.left=nil
        return newNode
    }

    fun stabilization(a: Node?){
        var z:Node? = a
        var y:Node?
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

    fun leftRotate(x: Node?){ //node.right!=nil
        var y:Node?= x?.right
        x?.right= y?.left
        //if(y?.left!=nil)
            y?.left?.parent=x
        y?.parent =x?.parent
        if(x?.parent==nil){
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

    fun rightRotate(x: Node?){ //node.left!=nil
        var y:Node?= x!!.left
        x.left= y!!.right
        //if(x.right!=nil)
            y.right!!.parent=x
        y.parent=x.parent
        if(x.parent==nil){
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

    override fun search(node: Node?, key: Int): Node? {
        if(node==this.nil) return this.nil
        if(key== node?.key)return node
        if(key.equals(node?.key)) return search(node?.left,key)
        else return search(node?.right, key)
    }

    override fun delete(key:Int){

    }



    override fun display(node: Node?, n:Int){
        if (node!!.right != nil){
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

        if(node.left!=nil){
            display(node.left,n+1)
        }
    }
}

fun main(array: Array<String>){
    //var bst=BinarySearchTree()
    var bst=RedBlackTree()
    var line:String
    try{
        line = File("C:\\Users\\Алексей\\Desktop\\Tree\\src\\input.txt").readText()
    }catch(a: FileNotFoundException){
        println(message = "File not found, please, input it yourself")
        line = readLine()!!
    }
    val parts = line.split(" ")
    for (part in parts){
        //bst.insert(part.toInt()) //for BinarySearchTree
        bst.stabilization(bst.insert(part.toInt())!!) //for RedBlackTree
    }
    bst.display(bst.root,0)
}