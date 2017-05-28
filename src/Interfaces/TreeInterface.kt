/**
 *
 * by Cherepanov Aleksei (PI-171)
 *
 * mrneumann888@gmail.com
 *
 **/

package Interfaces
interface Tree<K: Comparable <K>, V> {
    fun insert(key:K,value:V)
    fun delete(key:K)
    fun search(key: K): V?
}