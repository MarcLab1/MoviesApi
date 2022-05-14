package com.moviesapi.presentation.ui.search

sealed class SearchPageEvent
{
    data class UpdateQuery(val newQuery : String) : SearchPageEvent()
    object MoreSearchEvent : SearchPageEvent()
    object NewSearchEvent : SearchPageEvent()
    data class UpdateCurrentScrollIndex(val newPosition : Int) : SearchPageEvent()
}
