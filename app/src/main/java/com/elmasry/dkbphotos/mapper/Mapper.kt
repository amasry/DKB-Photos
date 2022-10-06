package com.elmasry.dkbphotos.mapper

interface Mapper<INPUT, OUTPUT> {
    fun map(input: INPUT): OUTPUT
}
