package com.example.videogameshopapplication

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.videogameshopapplication.Data.VideoGame
import java.util.*

fun formatVideoGames(videogames: List<VideoGame>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        videogames.forEach {
            append("<br>")
            append(" ${it.id}")
            append("\t${it.name}")
            append("\t${it.publisher}")
            append("\t${it.platform}")
            append("\t${it.price} $<br><br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}