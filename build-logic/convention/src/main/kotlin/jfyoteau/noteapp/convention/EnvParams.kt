package jfyoteau.noteapp.convention

object EnvParams {
    val splitTargets: Boolean get() = System.getProperty("split_targets") != null
}
