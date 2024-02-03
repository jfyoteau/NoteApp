package jfyoteau.noteapp.core.ui

object ColorUtils {
    fun blendARGB(
       color1: Long,
       color2: Long,
       ratio: Float
    ): Long {
        val inverseRatio = 1 - ratio
        val a: Float = (color1 ushr 24) * inverseRatio + (color2 ushr 24) * ratio
        val r: Float = (color1 shr 16 and 0xFF) * inverseRatio + (color2 shr 16 and 0xFF) * ratio
        val g: Float = (color1 shr 8 and 0xFF) * inverseRatio + (color2 shr 8 and 0xFF) * ratio
        val b: Float = (color1 and 0xFF) * inverseRatio + (color2 and 0xFF) * ratio
        return (a.toInt() shl 24 or (r.toInt() shl 16) or (g.toInt() shl 8) or b.toInt()).toLong()
    }
}
