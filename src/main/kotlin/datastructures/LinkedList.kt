package datastructures

class LinkedList<E: Comparable<E>> {

    fun add(value: E) {

    }

    fun remove(value: E) : Boolean {
        return true
    }

    fun removeAll(value: E) : Boolean {
        return true
    }

    fun reverse() {
    }

    fun removeDuplicates() : Boolean{
        return true
    }

    fun removeDuplicatesWithHashSet() : Boolean {
        return true
    }

    fun removeKthFromEnd(k: Int) : E? {
        return null
    }

    fun isCircular() : Boolean {
        return true
    }

    fun isEmpty() : Boolean {
        return true
    }

    fun startCircularNode() : LinkedListNode<E>? {
        return null
    }

    fun sort() {

    }

    fun printList() : String {
        return ""
    }

    fun asSequence() = sequence {
        var node = head
        while(node != null) {
            yield(node.value)
            node = node.next
        }
    }

}

