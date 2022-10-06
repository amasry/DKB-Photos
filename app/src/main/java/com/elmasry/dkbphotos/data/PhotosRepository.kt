package com.elmasry.dkbphotos.data

import com.elmasry.dkbphotos.mapper.Mapper
import com.elmasry.dkbphotos.model.Photo
import io.reactivex.Single

interface PhotosRepository {
    fun getPhotos(): Single<List<Photo>>
}

class PhotosRepositoryImpl(
    private val photosApi: PhotosApi,
    private val photoMapper: Mapper<ApiPhoto, Photo>,
) : PhotosRepository {

    override fun getPhotos(): Single<List<Photo>> {
        return photosApi.getPhotos().map {
            it.map { apiPhoto ->
                photoMapper.map(apiPhoto)
            }
        }
    }
}
