package com.elmasry.dkbphotos.photos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elmasry.dkbphotos.R
import com.elmasry.dkbphotos.data.PhotosRepository
import com.elmasry.dkbphotos.model.Photo
import com.elmasry.dkbphotos.photos.PhotosViewModel.ViewAction.ShowError
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class PhotosViewModelTest {

    private val photosRepository: PhotosRepository = mockk()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        RxAndroidPlugins.setMainThreadSchedulerHandler { io.reactivex.schedulers.Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { io.reactivex.schedulers.Schedulers.trampoline() }
    }

    @Test
    fun `emits photos to livedata`() {
        val expected = listOf(Photo(1, "title", "url", "thumbnail url"))
        every { photosRepository.getPhotos() } returns Single.just(expected)

        val viewModel = PhotosViewModel(photosRepository)

        assertThat(viewModel.photos.value).isEqualTo(expected)
    }

    @Test
    fun `emits error`() {
        val expected = ShowError(R.string.generic_error)
        every { photosRepository.getPhotos() } returns Single.error(Exception())

        val viewModel = PhotosViewModel(photosRepository)

        assertThat(viewModel.viewActions.value).isEqualTo(expected)
    }
}
