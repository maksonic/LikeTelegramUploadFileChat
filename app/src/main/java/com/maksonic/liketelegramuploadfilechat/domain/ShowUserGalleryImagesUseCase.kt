package com.maksonic.liketelegramuploadfilechat.domain

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentUris
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @Author: maksonic on 25.12.2021
 */
interface ShowUserGalleryImagesUseCase {
    suspend fun queryImagesFromGallery(): List<MediaStoreImage>

    class Base(private val application: Application) : ShowUserGalleryImagesUseCase {

        override suspend fun queryImagesFromGallery(): List<MediaStoreImage> {
            val images = mutableListOf<MediaStoreImage>()
            withContext(Dispatchers.IO) {
                val projection = arrayOf(
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATE_ADDED,
                )

                val selection = "${MediaStore.Images.Media.DATE_ADDED} >= ?"
                val selectionArgs = arrayOf(
                    dateToTimestamp(day = 22, month = 10, year = 2008).toString()
                )
                val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
                application.contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    selection,
                    selectionArgs,
                    sortOrder
                )?.use { cursor ->

                    val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                    val dateModifiedColumn =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
                    val displayNameColumn =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

                    while (cursor.moveToNext()) {
                        val id = cursor.getLong(idColumn)
                        val dateModified =
                            Date(TimeUnit.SECONDS.toMillis(cursor.getLong(dateModifiedColumn)))
                        val displayName = cursor.getString(displayNameColumn)
                        val contentUri = ContentUris.withAppendedId(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)

                        val image = MediaStoreImage(id, displayName, dateModified, contentUri)
                        images += image
                    }
                }
            }
            return images
        }

        @Suppress("SameParameterValue")
        @SuppressLint("SimpleDateFormat")
        private fun dateToTimestamp(day: Int, month: Int, year: Int): Long =
            SimpleDateFormat("dd.MM.yyyy").let { formatter ->
                TimeUnit.MICROSECONDS.toSeconds(formatter
                    .parse("$day.$month.$year")?.time ?: 0)
            }
    }
}