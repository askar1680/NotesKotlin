package com.ulunayev.askar.noteskotlin.models

import android.arch.persistence.room.*
import com.ulunayev.askar.noteskotlin.R
import java.util.*


enum class Repetition{
  NEVER, EVERY_DAY, EVERY_WEEK, EVERY_MONTH, EVERY_YEAR
}

@Entity
data class Note( @PrimaryKey(autoGenerate = true)
                 val id: Int = 0, @ColumnInfo(name = "position") var position: Int, @ColumnInfo(name = "title") var title: String,
                @ColumnInfo(name = "note") var note: String, @ColumnInfo(name = "type") val type: String, var isSelected: Boolean = false,
                 @TypeConverters(ColorConverter::class)var color: Color = Color(R.color.colorPrimary, R.color.white),
                @TypeConverters(RepetitionConverter::class)var repetition: Repetition = Repetition.NEVER){

}

data class Section(val name: String, var position: Int)

data class Color(val color: Int, val textColor: Int)

class ColorConverter{
  @TypeConverter
  fun fromColor(color: Color): String{
    return "${color.color} ${color.textColor}"
  }
  @TypeConverter
  fun toColor(colorString: String): Color{

    val colorStrings = colorString.split(" ")
    val color1 = colorStrings[0].toInt()
    val color2 = colorStrings[1].toInt()
    return Color(color1, color2)
  }
}



class RepetitionConverter{
  @TypeConverter
  fun fromRepetition(repetition: Repetition): String{
    return repetition.name
  }
  @TypeConverter
  fun toRepetition(name: String): Repetition{
    return Repetition.valueOf(name)
  }
}
