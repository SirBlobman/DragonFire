package com.DragonFire.compat.OptiFine

import net.minecraft.launchwrapper.IClassTransformer
import net.minecraft.launchwrapper.ITweaker
import net.minecraft.launchwrapper.LaunchClassLoader
import java.io.File
import java.net.URLClassLoader
import java.util.zip.ZipFile

class OptifineTweakerDevWrapper : ITweaker {
    override fun acceptOptions(p0: MutableList<String>?, p1: File?, p2: File?, p3: String?) { }
    override fun getLaunchArguments(): Array<out String>? = Array<String>(0) {""}
    override fun getLaunchTarget() = "net.minecraft.client.main.Main"
    override fun injectIntoClassLoader(classLoader: LaunchClassLoader) {
        classLoader.registerTransformer("com.DragonFire.compat.OptiFine.OptifineTransformerDevWrapper")
    }
}