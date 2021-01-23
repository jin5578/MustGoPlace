package com.example.mustgoplace.util

import androidx.lifecycle.LiveData


fun<T: Any> LiveData<T>.requireValue() = requireNotNull(value)