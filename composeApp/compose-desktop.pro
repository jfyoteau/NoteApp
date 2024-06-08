# sl4j
-dontwarn javax.jms.**
-dontwarn javax.mail.**
-dontwarn com.sun.jdmk.comm.**

# kotlinx.coroutines
-keep class kotlinx.coroutines.internal.MainDispatcherFactory { *; }
-keep class kotlinx.coroutines.swing.SwingDispatcherFactory { *; }

# Decompose
-keep class com.arkivanov.decompose.extensions.compose.mainthread.SwingMainThreadChecker

# sqlite
-keep class org.sqlite.** { *; }
