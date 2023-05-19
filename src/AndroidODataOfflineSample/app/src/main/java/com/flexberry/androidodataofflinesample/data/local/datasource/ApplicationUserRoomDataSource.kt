package com.flexberry.androidodataofflinesample.data.local.datasource

import android.util.Log
import androidx.sqlite.db.SimpleSQLiteQuery
import com.flexberry.androidodataofflinesample.data.local.entities.ApplicationUserEntity
import com.flexberry.androidodataofflinesample.data.query.QuerySettings

class ApplicationUserRoomDataSource(private val db: LocalDatabase) : RoomDataSource<ApplicationUserEntity>() {
    private fun ApplicationUserDao() = db.getApplicationUserDao()

    override fun createObjects(listObjects: List<ApplicationUserEntity>): List<Long> {
        return ApplicationUserDao().insertObjects(listObjects)
    }

    override fun readObjects(querySettings: QuerySettings?): List<ApplicationUserEntity> {
        var queryParamsValue = querySettings?.getRoomDataSourceValue()
        Log.v("queryParamsValue", queryParamsValue.toString())

        var finalQuery = StringBuilder()
        finalQuery.append("SELECT * FROM ApplicationUser")

        queryParamsValue?.forEach{
            if (!it.isNullOrEmpty()) {
                finalQuery.append(it)
            }
        }

        Log.v("finalQuery", finalQuery.toString())

        val simpleSQLiteQuery = SimpleSQLiteQuery(finalQuery.toString());

        return ApplicationUserDao().getObjects(simpleSQLiteQuery)
    }

    override fun updateObjects(listObjects: List<ApplicationUserEntity>): Int {
        return ApplicationUserDao().updateObjects(listObjects)
    }

    override fun deleteObjects(listObjects: List<ApplicationUserEntity>): Int {
        return ApplicationUserDao().deleteObjects(listObjects)
    }
}