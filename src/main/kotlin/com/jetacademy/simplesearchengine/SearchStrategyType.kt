package com.jetacademy.simplesearchengine

enum class SearchStrategyType {
    ALL {
        override fun search(dataSet: DataSet, queryKeyWords: List<String>): List<FileData> {
            val indexesSets = buildSetsOfIndexes(queryKeyWords, dataSet.invertedIndex)
            return indexesSets.reduce { acc, set -> acc.intersect(set) }.map { dataSet.dataList[it]}
        }
    },
    ANY {
        override fun search(dataSet: DataSet, queryKeyWords: List<String>): List<FileData> {
            val indexesSets = buildSetsOfIndexes(queryKeyWords, dataSet.invertedIndex)
            return indexesSets.reduce { acc, set -> acc.union(set) }.map { dataSet.dataList[it]}
        }
    },
    NONE {
        override fun search(dataSet: DataSet, queryKeyWords: List<String>): List<FileData> {
            val indexesSets = buildSetsOfIndexes(queryKeyWords, dataSet.invertedIndex)
            return dataSet.dataList.indices.toSet().subtract(indexesSets.reduce { acc, set -> acc.union(set) }).map { dataSet.dataList[it] }
        }


    };

    fun buildSetsOfIndexes(
            queryKeyWords: List<String>,
            inverseIndex: Map<String, Set<Int>>
    ): List<Set<Int>> {
        val normalizedKeyWords = queryKeyWords.map { it.toLowerCase() }
        return normalizedKeyWords.map { normalizedKeyWord ->
            if (inverseIndex.containsKey(normalizedKeyWord))
                inverseIndex[normalizedKeyWord]!!
            else
                setOf<Int>()
        }
    }

    abstract fun search(
            dataSet: DataSet,
            queryKeyWords: List<String>
    ): List<FileData>
}
