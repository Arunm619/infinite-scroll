package io.arunbuilds.infinite_scroll.data

import io.arunbuilds.infinite_scroll.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepository @Inject constructor(
    private val unsplashApi: UnsplashApi
)