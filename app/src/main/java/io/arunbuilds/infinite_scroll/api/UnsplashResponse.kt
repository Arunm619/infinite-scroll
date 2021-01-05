package io.arunbuilds.infinite_scroll.api

import io.arunbuilds.infinite_scroll.data.UnsplashPhoto

data class UnsplashResponse constructor(
    val results: List<UnsplashPhoto>
)