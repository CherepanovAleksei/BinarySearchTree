/**
 *
 * by Cherepanov Aleksei (PI-171)
 *
 * mrneumann888@gmail.com
 *
 **/

package BTree

import java.util.LinkedList

class BTree<K: Comparable<K>> (val deg: Int): Iterable<BNode<K>> {
    var root: BNode<K>? = null

    fun insert(key: K) {
        if (root == null) root = BNode()

        if (root!!.keys.size == 2 * deg - 1) {
            val newNode: BNode<K> = BNode(false)
            newNode.children.add(root!!)
            newNode.splitChildren(deg, 0)
            root = newNode
            insertNonfull(key, newNode)
        } else {
            insertNonfull(key, root!!)
        }
    }

    private fun insertNonfull(key: K, node: BNode<K>) {
        var count = 0
        while (count < node.keys.size && key > node.keys[count]) count++

        if (node.isLeaf){
            node.keys.add(count, key)
        } else {
            if (node.children[count].keys.size == 2 * deg - 1) {
                node.splitChildren(deg, count)

                if (key > node.keys[count]) count++
            }
            insertNonfull(key, node.children[count])
        }
    }

    fun delete(key: K) {
        if (search(key) == null) return
        if (root == null) return
        deleteRecursive(key, root!!)
        if (root!!.keys.size == 0) root = null
    }

    private fun deleteRecursive(key: K, node: BNode<K>) {
        var count = 0
        while (count < node.keys.size && key > node.keys[count]) count++

        if (node.keys[count] == key) {
            if ((node.isLeaf)) {
                node.keys.removeAt(count)
            } else if (node.children[count].keys.size > deg - 1) {
                val prevNode = prev(key, node)
                node.keys[count] = prevNode.keys.last()
                deleteRecursive(prevNode.keys.last(), node.children[count])
            } else if (node.children[count + 1].keys.size > deg - 1) {
                val nextNode = next(key, node)
                node.keys[count] = nextNode.keys.first()
                deleteRecursive(nextNode.keys.first(), node.children[count + 1])
            } else {
                node.mergeChildren(count)
                if (node.keys.isEmpty()) root = node.children[count]
                deleteRecursive(key, node.children[count])
            }
        }
        else {
            if (node.children[count].keys.size < deg) {
                when {
                    node.children[count] != node.children.last() && node.children[count + 1].keys.size > deg - 1 -> {
                        node.children[count].keys.add(node.keys[count])
                        node.keys[count] = node.children[count + 1].keys.first()
                        node.children[count + 1].keys.removeAt(0)

                        if (!node.children[count].isLeaf) {
                            node.children[count].children.add(node.children[count + 1].children.first())
                            node.children[count + 1].children.removeAt(0)
                        }
                    }

                    node.children[count] != node.children.first() && node.children[count - 1].keys.size > deg - 1 -> {
                        node.children[count].keys.add(0, node.keys[count - 1])
                        node.keys[count - 1] = node.children[count - 1].keys.last()
                        node.children[count - 1].keys.removeAt(node.children[count - 1].keys.size - 1)

                        if (!node.children[count].isLeaf) {
                            node.children[count].children.add(0, node.children[count - 1].children.last())
                            node.children[count - 1].children.removeAt(node.children[count - 1].children.size - 1)
                        }
                    }

                    node.children[count] != node.children.last() -> {
                        node.mergeChildren(count)
                        if (node.keys.isEmpty()) root = node.children[count]

                    }
                    node.children[count] != node.children.first() -> {
                        node.mergeChildren(count - 1)
                        if (node.keys.isEmpty()) root = node.children[count - 1]
                        count--
                    }
                }
            }
            deleteRecursive(key, node.children[count])
        }
    }

    fun search(key: K): K? {
        var node: BNode<K>? = root ?: return null
        var count = 0

        while (count < node!!.keys.size - 1 && key > node.keys[count]) count++
        while (node!!.keys[count] != key) {
            if (node.isLeaf) return null
            when {
                key < node.keys[count] -> node = node.children[count]
                key > node.keys[count] -> node = node.children[count + 1]
            }

            count = 0
            while (count < node.keys.size - 1 && key > node.keys[count]) count++
        }
        return key
    }

    private fun prev(key: K, node: BNode<K>): BNode<K> {
        var current = node.children[node.keys.indexOf(key)]
        while (!current.isLeaf) current = current.children.last()
        return current
    }

    private fun next(key: K, node: BNode<K>): BNode<K> {
        var current = node.children[node.keys.indexOf(key) + 1]
        while (!current.isLeaf) current = current.children.first()
        return current
    }

    override fun iterator(): Iterator<BNode<K>> {
        return (object : Iterator<BNode<K>> {

            var NodeList = LinkedList<BNode<K>>()

            init {
                if (root != null && root!!.keys.isNotEmpty()) {
                    NodeList.add(root!!)
                }
            }

            override fun hasNext() = NodeList.isNotEmpty()

            override fun next(): BNode<K> {
                val next = NodeList.remove()
                if (!next.isLeaf) {
                    NodeList.addAll(next.children)
                }
                return next
            }
        })
    }
}