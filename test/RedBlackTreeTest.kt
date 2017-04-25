/**
 *
 * by Cherepanov Aleksei (PI-171)
 *
 * mrneumann888@gmail.com
 *
 **/

package RBTree

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class RedBlackTreeTest {
    val RBTree = RedBlackTree<Int, Int>()
    var maxBlackHeight = -1

    fun checkStructure(node: RBNode<Int, Int>? = RBTree.root, blackHeight: Int = 1): Boolean {
        if (node == null) return true

        for(i in RBTree){
            if(i.parent!=null){
                if(i==i.parent!!.left) assertEquals(i, i.parent!!.left)
                else assertEquals(i, i.parent!!.right)
            }
        }

        if(RBTree.root!!.color) return false

        if (node.isLeaf()) {
            if (maxBlackHeight == -1) maxBlackHeight = blackHeight
            if (maxBlackHeight != blackHeight) return false
            return true
        }

        if (node.color) {
            if (node.left?.color == true || node.right?.color == true) return false
            else return checkStructure(node.left, blackHeight + 1) && checkStructure(node.right, blackHeight + 1)
        }
        else {
            val res1: Boolean
            val res2: Boolean

            if (node.left?.color == false){
                res1 = checkStructure(node.left, blackHeight + 1)
            }
            else {
                res1 = checkStructure(node.left, blackHeight)
            }

            if (node.right?.color == false) {
                res2 = checkStructure(node.right, blackHeight + 1)
            }
            else{
                res2 = checkStructure(node.right, blackHeight)
            }

            return res1 && res2
        }
    }

    @Test
    fun insertNormal() {
        val arr=Array(1000001,{ 0 })
        for (i in 1..1000000) {
            val key = Random().nextInt(999999) + 1
            RBTree.insert(key, key)
            arr[key]++
        }

        for (i in 1..1000000) {
            if(arr[i]>0) assertNotNull(RBTree.search(i))
        }
    }

    @Test
    fun insertSaveStructure() {
        for (i in 1..1000) {
            val key = Random().nextInt(999) + 1
            RBTree.insert(key, key)
            maxBlackHeight = -1
            assertTrue(checkStructure())
        }
    }

    @Test
    fun rootColorAfterInsert() {
        for (i in 1..1000000) {
            val key=Random().nextInt(999999)+1
            RBTree.insert(key, key)
            assertEquals(RBTree.root!!.color, false)
        }
    }

    @Test
    fun findExistentKey() {
        val arr=Array(1000001,{ 0 })
        for (i in 1..1000000) {
            val key = Random().nextInt(999999) + 1
            RBTree.insert(key, key)
            arr[key]++
        }

        for (i in 1..1000000) {
            if(arr[i]>0) assertEquals(RBTree.search(i), RBNode(i, i).value)
        }
    }

    @Test
    fun findNonexistentKey() {
        val arr=Array(1000001,{ 0 })
        for (i in 1..1000000) {
            val key = Random().nextInt(999999) + 1
            RBTree.insert(key, key)
            arr[key]++
        }

        for (i in 1..1000000) {
            if(arr[i]==0) assertNull(RBTree.search(i))
        }
    }

    @Test
    fun deleteRootOneNode() {
        RBTree.insert(1, 1)
        RBTree.delete(1)
        assertNull(RBTree.root)
    }

    @Test
    fun deleteRootFullTree() {
        var key:Int=0
        for (i in 1..1000000) {
            key = Random().nextInt(999999) + 1
            RBTree.insert(key, key)
        }
        var node=RBTree.searchNode(key)
        while(node!!.parent !=null) node = node.parent
        RBTree.delete(node.key)
        assertNotNull(RBTree.root)
    }

    @Test
    fun deleteNormal() {
        var key:Int
        val arr=Array(10001,{ 0 })
        for (i in 1..10000) {
            key=Random().nextInt(9999)+1
            RBTree.insert(key, key)
            arr[key]++
            assertTrue(RBTree.searchNode(key)!=null)
        }

        for (i in 1..5000){
            key=Random().nextInt(9999)+1
            RBTree.delete(key)
            arr[key]=0
        }

        for (i in 1..10000) if (arr[i]>0) assertTrue(RBTree.searchNode(i) in RBTree)
        else assertFalse(RBTree.searchNode(i) in RBTree)
    }

    @Test
    fun deleteNonexistentKey() {
        var key:Int
        val arr=Array(10001,{ 0 })
        for (i in 1..5000) {
            key=Random().nextInt(4999)+1
            RBTree.insert(key, key)
            arr[key]++
        }

        for (i in 5001..10000){
            key=Random().nextInt(4999)+1
            RBTree.insert(key, key)
            arr[key]++
    }

        RBTree.delete(5000)

        for (i in 1..10000) {
            if (arr[i]>0) assertTrue(RBTree.searchNode(i) in RBTree)
            else assertFalse(RBTree.searchNode(i) in RBTree)


        }
    }

    @Test
    fun deleteSaveStructure() {
        var key:Int
        val arr=Array(1001,{ 0 })
        for (i in 1..500) {
            key=Random().nextInt(499)+1
            RBTree.insert(key, key)
            arr[key]++
        }

        for (i in 1..1000) {
            RBTree.delete(i)
            maxBlackHeight = -1
            assertTrue(checkStructure())
        }
    }

    @Test
    fun rootColorAfterDelete() {
        var key:Int
        val arr=Array(1000001,{ 0 })
        for (i in 1..1000000) {
            key=Random().nextInt(999999)+1
            RBTree.insert(key, key)
            arr[key]++
        }

        for (i in 1..500000) {
            key=Random().nextInt(999999)+1
            RBTree.delete(key)
            assertEquals(RBTree.root?.color, false)
        }
    }

    @Test
    fun iterateEmptyTree() {
        for (i in RBTree)
            assertEquals(0, 1)
    }

    @Test
    fun iterateNormalTree() {
        for (i in 1..1000000) RBTree.insert(i, i)
        var a: Int = 1000001
        for (i in RBTree) {
            a--
            assertEquals(Pair(i.key,i.value), Pair(a, a))
        }
    }
}