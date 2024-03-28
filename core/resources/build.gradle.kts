plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.jetbrainsCompose)
}

android {
    namespace = "jfyoteau.noteapp.core.resources"
}

// fix for [https://github.com/JetBrains/compose-multiplatform/issues/4327]
// After executing the "generateComposeResClass" task, we replace the internal stuff by public.
tasks.named("generateComposeResClass") {
    doLast {
        val dirName = buildString {
            val group = project.group.toString()
                .lowercase()
                .replace('.', '/')
                .replace('-', '_')
            append(group)
            if (group.isNotEmpty()) {
                append("/")
            }
            append(project.name.lowercase())
        }
        val dir = project.layout.buildDirectory
            .dir("generated/compose/resourceGenerator/kotlin/$dirName/generated/resources")
            .get()
            .asFile
        File(dir, "Res.kt").also {
            if (!it.exists()) {
                return@also
            }
            val content = it.readText()
            val updatedContent = content.replace("internal object Res {", "object Res {")
            it.writeText(updatedContent)
        }
        listOf("Drawable0.kt", "String0.kt").forEach { filename ->
            File(dir, filename).also { file ->
                if (!file.exists()) {
                    return@also
                }
                val content = file.readText()
                val updatedContent = content.replace("internal val Res", "val Res")
                file.writeText(updatedContent)
            }
        }
    }
}
