package com.example.top10app

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "entry", strict = false)
class Entry constructor(
    @field:Element(name = "id")
    @param:Element(name = "id")
    private val id: String? = null,

    @field:Element(name = "title")
    @param:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "summary")
    @param:Element(name = "summary")
    var summary: String? = null
) : Serializable