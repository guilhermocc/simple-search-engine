package com.jetacademy.simplesearchengine

data class DataSet (
        val dataList: List<FileData>,
        val invertedIndex: Map<String, Set<Int>>
)