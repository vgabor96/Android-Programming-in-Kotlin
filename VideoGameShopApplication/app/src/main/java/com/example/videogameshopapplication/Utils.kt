package com.example.videogameshopapplication

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.videogameshopapplication.data.VideoGame


fun formatVideoGames(videoGames: List<VideoGame>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append(resources.getString(R.string.title))
        videoGames.forEach {
            append("<br>")
            append("Name: ${it.name}<br>")
            append("Publisher: ${it.publisher} ")
            append("\tPlatForm: ${it.platform} <br>")
            append("\tPrice: ${it.price} g<br><br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
