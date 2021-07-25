package co.jonathanbernal.joal.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.google.firebase.storage.FirebaseStorage


fun ImageView.loadMovieImage(path: String) {
    val url = FirebaseStorage.getInstance().getReferenceFromUrl(path)
    Glide
        .with(this.context)
        .load(url)
        .signature(ObjectKey(System.currentTimeMillis()))
        .into(this)
}