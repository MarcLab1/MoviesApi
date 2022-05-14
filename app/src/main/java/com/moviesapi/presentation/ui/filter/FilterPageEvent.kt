package com.moviesapi.presentation.ui.filter

sealed class FilterPageEvent{
    object OnFilterChangedEvent : FilterPageEvent()
    data class UpdateCurrentScrollIndex(val newPosition : Int) : FilterPageEvent()
}
