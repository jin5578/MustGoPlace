package com.example.mustgoplace.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class Place(
    val id: Long = 0,
    val content: String,
    val visitedAt: Date,
    val writtenAt: Date
) : Parcelable