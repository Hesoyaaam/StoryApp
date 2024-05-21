package com.example.storyapp

import com.example.storyapp.data.remote.response.ListStoryItem

object DataDummy {
    fun generateDummyQuoteResponse(): List<ListStoryItem> {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (i in 0..100) {
            val quote = ListStoryItem(
                id = "story-NFHo_CEUrsWMnulO",
                name = "poil",
                photoUrl = "ini ga pake loc",
                createdAt = "2024-05-17T16:09:26",
                lat = -7.7926351,
                lon = 110.4035111
            )
            items.add(quote)
        }
        return items
    }
}