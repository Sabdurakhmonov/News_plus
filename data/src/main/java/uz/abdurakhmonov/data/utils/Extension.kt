package uz.abdurakhmonov.data.utils

fun Long.toHours(): Int {
    return (this / (1000 * 60 * 60)).toInt()
}
