package com.basarcelebi.khas_app.data

import com.basarcelebi.khas_app.model.Calendar
import com.basarcelebi.khas_app.model.Student

object SubjectData {

    val defaultData = SubjectDatas()[0]
    fun SubjectDatas():List<Calendar>
    {
        return listOf(
            Calendar(
                subjectName = "Computer Programming II",
                time = "14:00 - 16:00"
            ),
            Calendar(
            subjectName = "Data Structures & Algorithms",
            time = "16:00 - 18:00"
            )
        )
    }
}