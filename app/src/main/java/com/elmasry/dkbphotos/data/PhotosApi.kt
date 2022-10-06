package com.elmasry.dkbphotos.data

import io.reactivex.Single
import retrofit2.http.GET

interface PhotosApi {

    @GET("/photos")
    fun getPhotos(): Single<List<ApiPhoto>>
}
