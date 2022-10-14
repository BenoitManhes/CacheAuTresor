package com.benoitmanhes.domain.interfaces

interface ModelEncodable<out M : Model> {
    fun toAppModel(): M
}
