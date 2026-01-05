package datastructures

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LinkedListTest {

    @ParameterizedTest
    @MethodSource("providesAddLinkedList")
    fun `add success`(sizeList : Int, expected: List<Int>) {
      val actual = buildList(sizeList)
      validate(actual, expected)
    }

    @ParameterizedTest
    @MethodSource("providesRemoveLinkedList")
    fun `remove success`(actual: LinkedList<Int>, expected: List<Int>, target: Int) {
        actual.remove(target)
        validate(actual, expected)
    }

    @ParameterizedTest
    @MethodSource("providesRemoveAllLinkedList")
    fun `removeAll success`(actual: LinkedList<Int>, expected: List<Int>, target: Int) {
        actual.removeAll(target)
        validate(actual, expected)
    }

    private fun validate(actual: LinkedList<Int>, expected: List<Int>) {
        var index = 0
        for(value in actual.asSequence()) {
            value shouldBe expected[index++]
        }
        actual.last?.value shouldBe
                if(expected.isEmpty()) null else expected.last()
        actual.root?.value shouldBe
                if(expected.isEmpty()) null else expected.first()
    }

    companion object {

        fun buildList(limit: Int) : LinkedList<Int> {
            val linkedList = LinkedList<Int>()
            for(i in 1..limit) {
                linkedList.add(i)
            }
            return linkedList
        }

        @JvmStatic
        fun providesAddLinkedList() = listOf(
            Arguments.of(3, listOf(1,2,3)),
            Arguments.of(1, listOf(1)),
        )

        @JvmStatic
        fun providesRemoveLinkedList() = listOf(
            Arguments.of(buildList(3), listOf(1,3), 2),
            Arguments.of(buildList(3), listOf(2,3), 1),
            Arguments.of(buildList(3), listOf(1,2), 3),
            Arguments.of(buildList(1), listOf<Int>(), 1),
            Arguments.of(buildList(2), listOf<Int>(1,2), 4),
        )

        @JvmStatic
        fun providesRemoveAllLinkedList(): List<Arguments> {
            val removeOneList = buildList(3)

            val removeFromStartList = LinkedList<Int>()
            removeFromStartList.add(1)
            removeFromStartList.add(1)
            removeFromStartList.add(2)
            removeFromStartList.add(3)

            val removeFromMiddleList = LinkedList<Int>()
            removeFromMiddleList.add(1)
            removeFromMiddleList.add(2)
            removeFromMiddleList.add(2)
            removeFromMiddleList.add(2)
            removeFromMiddleList.add(3)

            val removeFromEndList = LinkedList<Int>()
            removeFromEndList.add(1)
            removeFromEndList.add(2)
            removeFromEndList.add(3)
            removeFromEndList.add(3)
            removeFromEndList.add(3)

            val removeSameList = LinkedList<Int>()
            removeSameList.add(1)
            removeSameList.add(1)
            removeSameList.add(1)

            val removeSingleList = buildList(1)

            val removeNotFoundList = buildList(3)

            return listOf(
                Arguments.of(removeOneList, listOf(1,3), 2),
                Arguments.of(removeFromStartList, listOf(2,3), 1),
                Arguments.of(removeFromMiddleList, listOf(1,3), 2),
                Arguments.of(removeFromEndList, listOf(1,2), 3),
                Arguments.of(removeSameList, listOf<Int>(), 1),
                Arguments.of(removeSingleList, listOf<Int>(), 1),
                Arguments.of(removeNotFoundList, listOf(1,2,3), 4),
            )
        }

    }
}

