package com.example.materialsettingsscreen.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

val Typography = Typography(
    // Великий заголовок (назва екрана)
    headlineSmall = TextStyle(
        fontSize = 22.sp,              // Трохи більше за попередній
        fontWeight = FontWeight.Bold,  // Жирний шрифт
        color = TextPrimary
    ),

    // Секційні заголовки (наприклад, "Notification")
    titleMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextPrimary
    ),

    // Назви налаштувань (основний текст)
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextPrimary
    ),

    // Опис під налаштуваннями (вторинний текст)
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = TextSecondary
    ),

    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = TextSecondary
    ),

    // Текст кнопок або малий акцентний текст
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = TextPrimary
    ),

    // Маленький текст для допоміжних елементів
    labelSmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextSecondary
    )
)
