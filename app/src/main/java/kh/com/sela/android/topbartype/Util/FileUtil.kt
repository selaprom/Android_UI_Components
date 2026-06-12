package kh.com.sela.android.topbartype.Util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.google.ai.edge.litert.Environment
import okio.IOException
import java.io.File
import java.io.FileOutputStream

enum class FileType(val suffix: String) {
    IMAGE("image/"),
    VIDEO("video/"),
    AUDIO("audio/")
}

object FileUtil {

    fun getFileTypeFromUri(context: Context, uri: Uri): FileType? {
        val mimeType = context.contentResolver.getType(uri) ?: return null

        return when {
            mimeType.startsWith(FileType.IMAGE.suffix) -> FileType.IMAGE
            mimeType.startsWith(FileType.VIDEO.suffix) -> FileType.VIDEO
            mimeType.startsWith(FileType.AUDIO.suffix) -> FileType.AUDIO
            else -> null
        }
    }

    fun saveBitmapToCache(
        context: Context,
        bitmap: Bitmap?,
        fileName: String = "image_${System.currentTimeMillis()}.jpg}",
    ): File? {
        try {
            val file = File(context.cacheDir, fileName)
            FileOutputStream(file).use { outputStream ->
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
                outputStream.flush()
            }
            return if (file.exists()) file else null


        } catch (e: IOException) {
            e.printStackTrace()

        }





        return null
    }

    fun saveImageToGallery(
        context: Context,
        bitmap: Bitmap,
        fileName: String = "IMG_${System.currentTimeMillis()}.jpg"
    ): String? {
        val resolver = context.contentResolver
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(
                    MediaStore.Images.Media.RELATIVE_PATH,
                    "Pictures/MyApp"
                )
            }
        }

        val uri = resolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )

        uri?.let { imageUri ->
            resolver.openOutputStream(imageUri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            }
            return imageUri.toString()
        }
        return null
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun saveBitmapToDownloads(
        context: Context,
        bitmap: Bitmap,
        fileName: String = "IMG_Download${System.currentTimeMillis()}.jpg"
    ): String? {
        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")

            put(MediaStore.Downloads.RELATIVE_PATH, "Download/MyApp")

        }

        val resolver = context.contentResolver

        val uri = resolver.insert(
            MediaStore.Downloads.EXTERNAL_CONTENT_URI,
            values
        )

       uri?.let {
           resolver.openOutputStream(it)?.use { outputStream ->
               bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
           }
           return uri.toString()
       }



        return null
    }
}