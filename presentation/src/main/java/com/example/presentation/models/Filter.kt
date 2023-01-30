package com.example.presentation.models

data class Filter(var id: Int, var isSelected: Boolean = false, val label: String, val type: String)
