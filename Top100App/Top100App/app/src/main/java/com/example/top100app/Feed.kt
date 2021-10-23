package com.example.top10app

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "feed", strict = false)
class Feed {

    @field:Element(name = "title")
    var title: String? = null

    @field:Element(name = "updated")
    var updated: String? = null

    @field:Element(name = "icon")
    var icon: String? = null

    @field:ElementList(inline = true, name = "entry")
    var entrys: List<Entry>? = null

    @field:Element(required = false, name = "author")
    var author: Author? = null

}