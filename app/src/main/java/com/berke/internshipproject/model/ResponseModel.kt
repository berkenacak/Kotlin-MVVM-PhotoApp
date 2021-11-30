package com.berke.internshipproject.model

import java.io.Serializable

data class ResponseModel(
    val hits: List<ImageModel>,
    val total: Int,
    val totalHits: Int
): Serializable

data class ImageModel(
    val comments: Int? = null,
    val downloads: Int? = null,
    val favorites: Int? = null,
    val fullHDURL: String? = null,
    val id: Int,
    val imageHeight: Int? = null,
    val imageSize: Int? = null,
    val imageURL: String? = null,
    val imageWidth: Int? = null,
    val largeImageURL: String? = null,
    val likes: Int? = null,
    val pageURL: String? = null,
    val previewHeight: Int? = null,
    val previewURL: String? = null,
    val previewWidth: Int? = null,
    val tags: String? = null,
    val type: String? = null,
    val user: String? = null,
    val userImageURL: String? = null,
    val user_id: Int? = null,
    val views: Int? = null,
    val webformatHeight: Int? = null,
    val webformatURL: String? = null,
    var searchWord: String? = null,
    val webformatWidth: Int? = null
): Serializable