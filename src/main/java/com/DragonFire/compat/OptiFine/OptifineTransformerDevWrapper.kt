package com.DragonFire.compat.OptiFine

import net.minecraft.launchwrapper.IClassTransformer
import java.io.File
import java.net.URLClassLoader
import java.util.zip.ZipFile

/**
 * Replacement for OptiFine's class transformer. Implements the pre-1.8.x-H5 way of operation.
 *
 * This class is only used in development to debug cross-mod issues with Optifine, and
 * is not part of the release!
 */
class OptifineTransformerDevWrapper : IClassTransformer {
    fun <T> tryDefault(default: T, work: ()->T) = try { work() } catch (e: Throwable) { default }

    val ofZip = (this.javaClass.classLoader as? URLClassLoader)?.urLs?.find {
        val zipFile = tryDefault(null) { ZipFile(File(it.toURI())) }
        zipFile?.getEntry("optifine/OptiFineClassTransformer.class") != null
    }?.let { ZipFile(File(it.toURI())) }

    /**
     * Load replacement classes from the OptiFine Jar.
     */
    override fun transform(name: String?, transformedName: String?, classData: ByteArray?) =
            ofZip?.getEntry(name?.replace(".", "/") + ".class")?.let { ofZip.getInputStream(it).readBytes() } ?: classData
}