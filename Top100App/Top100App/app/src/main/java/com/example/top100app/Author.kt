package com.example.top10app

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable


@Root(name = "author", strict = false)
class Author : Serializable {

    @field:Element(name = "name")
    var name: String? = null

    @field:Element(name = "uri")
    var uri: String? = null

    override fun toString(): String {
        return "Author{\n name :  $name\nuri : $uri\n}"
    }
}