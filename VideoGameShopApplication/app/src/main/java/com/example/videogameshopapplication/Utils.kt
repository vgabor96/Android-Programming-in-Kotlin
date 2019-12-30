package com.example.videogameshopapplication

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.videogameshopapplication.data.DailyIntake
import java.util.*

//We should use RecycleView, but that is out of scope of the course.
fun formatDailyIntakes(dailyIntakes: List<DailyIntake>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append(resources.getString(R.string.title))
        dailyIntakes.forEach {
            append("<br>")
            append("${Date(it.dateOfIntake)}<br>")
                append("omega 3: ${it.dailyOmega3} mg")
                append("\tomega 6: ${it.dailyOmega6} mg<br>")
                append("cholesterol: ${it.dailyCholesterol} g<br><br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}