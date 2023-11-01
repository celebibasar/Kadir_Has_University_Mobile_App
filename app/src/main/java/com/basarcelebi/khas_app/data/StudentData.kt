package com.basarcelebi.khas_app.data

import com.basarcelebi.khas_app.model.Student

object StudentData {
    val defaultData = StudentDatas()[0]

    fun StudentDatas():List<Student>
    {
        return listOf(
            Student(
                id = 1,
                name = "Başar",
                surname = "Çelebi",
                semester = 5,
                gno = 3.67
            ),
            Student(
                id = 2,
                name = "Başar",
                surname = "Çelebi",
                semester = 5,
                gno = 3.67
            )

        )

    }
}