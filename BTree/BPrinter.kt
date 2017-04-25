/**
 *
 * by Cherepanov Aleksei (PI-171)
 *
 * mrneumann888@gmail.com
 *
 **/

package BTree

class logic {
    fun <K : Comparable<K>>display(tree:BTree<K>){
        var size:Int=0
        var level:Int=1
        for (i in tree)
        {
            print(" "+i.keys+" ")
            size+=i.keys.size+1
            level--
            if (level==0) {
                println()
                level = size
                size=0
            }
        }
    }
}