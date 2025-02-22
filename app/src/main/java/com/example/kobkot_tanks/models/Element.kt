package com.example.kobkot_tanks.models

import com.example.kobkot_tanks.enums.Material

data class Element(
    val viewId: Int,
    val material: Material,
    val coordinate: Coordinate
)
