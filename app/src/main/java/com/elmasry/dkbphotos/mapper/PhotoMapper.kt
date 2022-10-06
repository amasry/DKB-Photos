package com.elmasry.dkbphotos.mapper

import com.elmasry.dkbphotos.data.ApiPhoto
import com.elmasry.dkbphotos.model.Photo

class PhotoMapper : Mapper<ApiPhoto, Photo> {

    override fun map(input: ApiPhoto): Photo {
        return Photo(
            id = input.id,
            title = input.title,
            url = input.url,
            thumbnailUrl = input.thumbnailUrl,
        )
    }
}
