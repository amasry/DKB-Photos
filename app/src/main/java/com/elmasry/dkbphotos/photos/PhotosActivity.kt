package com.elmasry.dkbphotos.photos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.elmasry.dkbphotos.databinding.ActivityPhotosBinding
import com.elmasry.dkbphotos.photos.PhotosViewModel.ViewAction.ShowError
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosActivity : AppCompatActivity() {

    private val viewModel: PhotosViewModel by viewModel()
    private val adapter = PhotosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.viewActions.observe(this) {
            when (it) {
                is ShowError -> Toast.makeText(this, getString(it.errorResId), Toast.LENGTH_LONG).show()
            }
        }
        viewModel.photos.observe(this) {
            adapter.submitList(it)
        }
    }
}
