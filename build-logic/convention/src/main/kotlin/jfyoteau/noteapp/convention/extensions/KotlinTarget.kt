package jfyoteau.noteapp.convention.extensions

import jfyoteau.noteapp.convention.EnvParams
import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinMultiplatformPlugin

private enum class HostType {
    MAC_OS,
    LINUX,
}

private fun KotlinTarget.getHostType(): HostType? =
    when (platformType) {
        KotlinPlatformType.androidJvm,
        KotlinPlatformType.jvm,
        KotlinPlatformType.js,
        KotlinPlatformType.wasm -> HostType.LINUX

        KotlinPlatformType.native ->
            when {
                name.startsWith("ios") -> HostType.MAC_OS
                name.startsWith("watchos") -> HostType.MAC_OS
                name.startsWith("linux") -> HostType.LINUX
                else -> error("Unsupported native target: $this")
            }

        KotlinPlatformType.common -> null
    }

private fun KotlinTarget.isCompilationAllowed(): Boolean {
    if ((name == KotlinMultiplatformPlugin.METADATA_TARGET_NAME) || !EnvParams.splitTargets) {
        return true
    }

    val os = OperatingSystem.current()

    return when (getHostType()) {
        HostType.MAC_OS -> os.isMacOsX
        HostType.LINUX -> os.isWindows || os.isLinux
        null -> true
    }
}

fun KotlinTarget.disableCompilationsIfNeeded() {
    if (!isCompilationAllowed()) {
        disableCompilations()
    }
}

private fun KotlinTarget.disableCompilations() {
    compilations.configureEach {
        compileTaskProvider.configure {
            enabled = false
        }
    }
}
