
/**
 * Created by mrneumann888@gmail.com
 */


import BTree.BTree
import BinaryTree.BinarySearchTree
import RBTree.RedBlackTree

import org.junit.jupiter.api.Test
import java.util.*

internal class EqualTreeTest {
    @Test
    fun BinTreeRandom() {
        val BinTree = BinarySearchTree<Int, Int>()

        var number:Int = 100
        while (number < 10000001) {
            var start = System.currentTimeMillis()
            for (i in 1..number) {
                val key = Random().nextInt(number-1) + 1
                BinTree.insert(key, key)
            }
            var finish = System.currentTimeMillis()
            var timeConsumedMillis:Float = (finish - start).toFloat()/1000
            println("InsertBinTreeRandom(${number}):"+timeConsumedMillis)

            start = System.currentTimeMillis()
            for (i in 1..number) {
                val key = Random().nextInt(number-1) + 1
                BinTree.search(key)
            }
            finish = System.currentTimeMillis()
            timeConsumedMillis = (finish - start).toFloat()/1000
            println("SearchBinTreeRandom(${number}):"+timeConsumedMillis)
            number *= 10
            println()
        }
    }

    @Test
    fun BinTreeQueue() {
        val BinTree = BinarySearchTree<Int, Int>()

        var number:Int = 100
        while (number < 10000001) {
            var start = System.currentTimeMillis()
            for (key in 1..number) BinTree.insert(key, key)
            var finish = System.currentTimeMillis()
            var timeConsumedMillis:Float = (finish - start).toFloat()/1000
            println("InsertBinTreeQueue(${number}):"+timeConsumedMillis)

            start = System.currentTimeMillis()
            for (key in 1..number) {
                BinTree.search(key)
            }
            finish = System.currentTimeMillis()
            timeConsumedMillis = (finish - start).toFloat()/1000
            println("SearchBinTreeQueue(${number}):"+timeConsumedMillis)
            number *= 10
            println()
        }
    }


    @Test
    fun RBTreeRandom() {
        val RBTree = RedBlackTree<Int, Int>()

        var number:Int = 100
        while (number < 10000001) {
            var start = System.currentTimeMillis()
            for (i in 1..number) {
                val key = Random().nextInt(number-1) + 1
                RBTree.insert(key, key)
            }
            var finish = System.currentTimeMillis()
            var timeConsumedMillis:Float = (finish - start).toFloat()/1000
            println("InsertRBTreeRandom(${number}):"+timeConsumedMillis)

            start = System.currentTimeMillis()
            for (i in 1..number) {
                val key = Random().nextInt(number-1) + 1
                RBTree.search(key)
            }
            finish = System.currentTimeMillis()
            timeConsumedMillis = (finish - start).toFloat()/1000
            println("SearchRBTreeRandom(${number}):"+timeConsumedMillis)
            number *= 10
            println()
        }
    }

    @Test
    fun RBTreeQueue() {
        val RBTree = RedBlackTree<Int, Int>()

        var number:Int = 100
        while (number < 10000001) {
            var start = System.currentTimeMillis()
            for (key in 1..number) RBTree.insert(key, key)
            var finish = System.currentTimeMillis()
            var timeConsumedMillis:Float = (finish - start).toFloat()/1000
            println("InsertRBTreeQueue(${number}):"+timeConsumedMillis)

            start = System.currentTimeMillis()
            for (key in 1..number) {
                RBTree.search(key)
            }
            finish = System.currentTimeMillis()
            timeConsumedMillis = (finish - start).toFloat()/1000
            println("SearchRBTreeQueue(${number}):"+timeConsumedMillis)
            number *= 10
            println()
        }
    }


    @Test
    fun BTreeRandom() {
        val BTree = BTree<Int>(100)

        var number:Int = 100
        while (number < 10000001) {
            var start = System.currentTimeMillis()
            for (i in 1..number) {
                val key = Random().nextInt(number-1) + 1
                BTree.insert(key)
            }
            var finish = System.currentTimeMillis()
            var timeConsumedMillis:Float = (finish - start).toFloat()/1000
            println("InsertBTreeRandom(${number}):"+timeConsumedMillis)

            start = System.currentTimeMillis()
            for (i in 1..number) {
                val key = Random().nextInt(number-1) + 1
                BTree.search(key)
            }
            finish = System.currentTimeMillis()
            timeConsumedMillis = (finish - start).toFloat()/1000
            println("SearchBTreeRandom(${number}):"+timeConsumedMillis)
            number *= 10
            println()
        }
    }

    @Test
    fun BTreeQueue() {
        val BTree = BTree<Int>(100)

        var number:Int = 100
        while (number < 10000001) {
            var start = System.currentTimeMillis()
            for (key in 1..number) BTree.insert(key)
            var finish = System.currentTimeMillis()
            var timeConsumedMillis:Float = (finish - start).toFloat()/1000
            println("InsertBTreeQueue(${number}):"+timeConsumedMillis)

            start = System.currentTimeMillis()
            for (key in 1..number) {
                BTree.search(key)
            }
            finish = System.currentTimeMillis()
            timeConsumedMillis = (finish - start).toFloat()/1000
            println("SearchBTreeQueue(${number}):"+timeConsumedMillis)
            number *= 10
            println()
        }
    }
}