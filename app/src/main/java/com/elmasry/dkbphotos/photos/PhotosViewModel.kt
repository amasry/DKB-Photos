package com.elmasry.dkbphotos.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elmasry.dkbphotos.R
import com.elmasry.dkbphotos.data.PhotosRepository
import com.elmasry.dkbphotos.model.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class PhotosViewModel(
    photosRepository: PhotosRepository,
) : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> = _photos
    private val _viewActions = SingleLiveEvent<ViewAction>()
    val viewActions: LiveData<ViewAction> = _viewActions
    private var disposable: Disposable? = null

    init {
        disposable = photosRepository
            .getPhotos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _photos.value = it
                },
                onError = {
                    _viewActions.value = ViewAction.ShowError(R.string.generic_error)
                }
            )
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    sealed class ViewAction {
        data class ShowError(val errorResId: Int) : ViewAction()
    }
}
