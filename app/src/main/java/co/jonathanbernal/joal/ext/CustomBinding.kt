package co.jonathanbernal.joal.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadimage")
fun bindingImage(imageView: ImageView, path: String?) {
    if (path != null) {
        imageView.loadMovieImage(path)
    }
}