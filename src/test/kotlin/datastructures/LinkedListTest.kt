package datastructures

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LinkedListTest {

    @ParameterizedTest(name = "{index} => {2}")
    @MethodSource("providesAddLinkedList")
    fun `add success`(sizeList: Int, expected: List<Int>, displayName: String) {
        val actual = buildList(sizeList)
        validate(actual, expected)
    }

    @ParameterizedTest(name = "{index} => {4}")
    @MethodSource("providesRemoveLinkedList")
    fun `remove success`(
        actual: LinkedList<Int>,
        expected: List<Int>,
        target: Int,
        wasFound: Boolean,
        displayName: String
    ) {
        val result = actual.remove(target)
        result shouldBe wasFound
        validate(actual, expected)
    }

    @ParameterizedTest(name = "{index} => {4}")
    @MethodSource("providesRemoveAllLinkedList")
    fun `removeAll success`(
        actual: LinkedList<Int>,
        expected: List<Int>,
        target: Int,
        wasFound: Boolean,
        displayName: String
    ) {
        val result = actual.removeAll(target)
        result shouldBe wasFound
        validate(actual, expected)
    }

    @ParameterizedTest(name = "{index} => {2}")
    @MethodSource("providesReverseLinkedList")
    fun `reverse success`(actual: LinkedList<Int>, expected: List<Int>, displayName: String) {
        actual.reverse()
        validate(actual, expected)
    }

    @ParameterizedTest(name = "{index} => {4}")
    @MethodSource("providesRemoveKthFromEnd")
    fun `removeKthFromEnd success`(
        actual: LinkedList<Int>,
        k: Int,
        expectedValue: Int?,
        expectedList: List<Int>,
        displayName: String
    ) {
        val result = actual.removeKthFromEnd(k)
        result shouldBe expectedValue
        validate(actual, expectedList)
    }

    @ParameterizedTest(name = "{index} => {3}")
    @MethodSource("providesRemoveDupsLinkedList")
    fun `remove duplicates success`(
        actual: LinkedList<Int>,
        expected: List<Int>,
        hasDuplicates: Boolean,
        displayName: String
    ) {
        val result = actual.removeDuplicates()
        result shouldBe hasDuplicates
        validate(actual, expected)
    }

    @ParameterizedTest(name = "{index} => {3}")
    @MethodSource("providesRemoveDupsLinkedList")
    fun `remove duplicates hashset success`(
        actual: LinkedList<Int>,
        expected: List<Int>,
        hasDuplicates: Boolean,
        displayName: String
    ) {
        val result = actual.removeDuplicatesWithHashSet()
        result shouldBe hasDuplicates
        validate(actual, expected)
    }

    private fun validate(actual: LinkedList<Int>, expected: List<Int>) {
        var index = 0
        for (value in actual.asSequence()) {
            value shouldBe expected[index++]
        }
        actual.last?.value shouldBe
                if (expected.isEmpty()) null else expected.last()
        actual.root?.value shouldBe
                if (expected.isEmpty()) null else expected.first()
    }

    companion object {

        fun buildList(limit: Int): LinkedList<Int> {
            val linkedList = LinkedList<Int>()
            for (i in 1..limit) {
                linkedList.add(i)
            }
            return linkedList
        }

        @JvmStatic
        fun providesReverseLinkedList(): List<Arguments> {
            return listOf(
                Arguments.of(buildList(3), listOf(3, 2, 1), "reverse list with 3 elements [1,2,3]"),
                Arguments.of(buildList(1), listOf(1), "reverse single element list [1]"),
                Arguments.of(buildList(5), listOf(5, 4, 3, 2, 1), "reverse list with 5 elements [1,2,3,4,5]"),
                Arguments.of(buildList(2), listOf(2, 1), "reverse list with 2 elements [1,2]"),
            )
        }

        @JvmStatic
        fun providesAddLinkedList() = listOf(
            Arguments.of(3, listOf(1, 2, 3), "add 3 elements to empty list"),
            Arguments.of(1, listOf(1), "add single element to empty list"),
        )

        @JvmStatic
        fun providesRemoveLinkedList(): List<Arguments> {
            return listOf(
                Arguments.of(buildList(3), listOf(1, 3), 2, true, "remove middle element from list [1,2,3]"),
                Arguments.of(buildList(3), listOf(2, 3), 1, true, "remove first element from list [1,2,3]"),
                Arguments.of(buildList(3), listOf(1, 2), 3, true, "remove last element from list [1,2,3]"),
                Arguments.of(buildList(1), listOf<Int>(), 1, true, "remove single element from list [1]"),
                Arguments.of(buildList(2), listOf<Int>(1, 2), 4, false, "remove element not found in list [1,2]"),
            )
        }

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
                Arguments.of(removeOneList, listOf(1, 3), 2, true, "removeAll one occurrence in list [1,2,3]"),
                Arguments.of(removeFromStartList, listOf(2, 3), 1, true, "removeAll duplicates from start [1,1,2,3]"),
                Arguments.of(
                    removeFromMiddleList,
                    listOf(1, 3),
                    2,
                    true,
                    "removeAll multiple duplicates from middle [1,2,2,2,3]"
                ),
                Arguments.of(
                    removeFromEndList,
                    listOf(1, 2),
                    3,
                    true,
                    "removeAll multiple duplicates from end [1,2,3,3,3]"
                ),
                Arguments.of(removeSameList, listOf<Int>(), 1, true, "removeAll all same values [1,1,1]"),
                Arguments.of(removeSingleList, listOf<Int>(), 1, true, "removeAll single element list [1]"),
                Arguments.of(
                    removeNotFoundList,
                    listOf(1, 2, 3),
                    4,
                    false,
                    "removeAll value not found [1,2,3] remove 4"
                ),
            )
        }

        @JvmStatic
        fun providesRemoveDupsLinkedList(): List<Arguments> {
            val duplicatesAtEndList = buildList(3)
            duplicatesAtEndList.add(3)
            duplicatesAtEndList.add(2)
            val multipleDuplicatesAtEndList = buildList(3)
            multipleDuplicatesAtEndList.add(2)
            multipleDuplicatesAtEndList.add(3)
            multipleDuplicatesAtEndList.add(3)
            multipleDuplicatesAtEndList.add(3)
            val allDuplicatesAtStartList = LinkedList<Int>()
            allDuplicatesAtStartList.add(1)
            allDuplicatesAtStartList.add(1)
            allDuplicatesAtStartList.add(1)
            allDuplicatesAtStartList.add(1)
            allDuplicatesAtStartList.add(2)
            allDuplicatesAtStartList.add(3)
            val scatteredDuplicatesList = LinkedList<Int>()
            scatteredDuplicatesList.add(1)
            scatteredDuplicatesList.add(2)
            scatteredDuplicatesList.add(2)
            scatteredDuplicatesList.add(1)
            scatteredDuplicatesList.add(2)
            scatteredDuplicatesList.add(3)

            val expected = listOf(1, 2, 3)
            return listOf(
                Arguments.of(duplicatesAtEndList, expected, true, "remove duplicates at end of list [1,2,3,3,2]"),
                Arguments.of(
                    multipleDuplicatesAtEndList,
                    expected,
                    true,
                    "remove multiple duplicate occurrences at end [1,2,3,2,3,3,3]"
                ),
                Arguments.of(
                    allDuplicatesAtStartList,
                    expected,
                    true,
                    "remove all duplicate occurrences at start [1,1,1,1,2,3]"
                ),
                Arguments.of(
                    scatteredDuplicatesList,
                    expected,
                    true,
                    "remove scattered duplicate values [1,2,2,1,2,3]"
                ),
                Arguments.of(buildList(3), expected, false, "no duplicates found in list [1,2,3]"),
            )
        }

        @JvmStatic
        fun providesRemoveKthFromEnd(): List<Arguments> {
            return listOf(
                Arguments.of(buildList(3), 1, 3, listOf(1, 2), "remove last element (k=1) from list [1,2,3]"),
                Arguments.of(buildList(3), 2, 2, listOf(1, 3), "remove middle element (k=2) from list [1,2,3]"),
                Arguments.of(buildList(3), 3, 1, listOf(2, 3), "remove first element (k=3) from list [1,2,3]"),
                Arguments.of(buildList(1), 1, 1, listOf<Int>(), "remove single element (k=1) from list [1]"),
                Arguments.of(buildList(2), 1, 2, listOf(1), "remove last element (k=1) from list [1,2]"),
                Arguments.of(buildList(2), 2, 1, listOf(2), "remove first element (k=2) from list [1,2]"),
                Arguments.of(buildList(3), 5, null, listOf(1, 2, 3), "k larger than list size (k=5) list [1,2,3]"),
                Arguments.of(buildList(3), 0, null, listOf(1, 2, 3), "k is invalid (k=0) list [1,2,3]"),
                Arguments.of(
                    buildList(5),
                    4,
                    2,
                    listOf(1, 3, 4, 5),
                    "remove 4th element from end (k=4) from list [1,2,3,4,5]"
                ),
            )
        }
    }
}
