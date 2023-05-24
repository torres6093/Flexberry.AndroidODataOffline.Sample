package com.flexberry.androidodataofflinesample.data.local.datasource.room

import com.flexberry.androidodataofflinesample.data.local.datasource.LocalDatabase
import com.flexberry.androidodataofflinesample.data.local.entities.DetailEntity
import com.flexberry.androidodataofflinesample.data.local.entities.MasterEntity
import javax.inject.Inject

class RoomDataSourceTypeManager @Inject constructor(
    db: LocalDatabase
) {
    private val roomTypeMap = listOf(
        RoomDataSourceTypeInfo(
            kotlinClass = MasterEntity::class,
            dao = db.getMasterDao(),
            tableName = MasterEntity.tableName,
            details = listOf("details")
        ),
        RoomDataSourceTypeInfo(
            kotlinClass = DetailEntity::class,
            dao = db.getDetailDao(),
            tableName = DetailEntity.tableName
        ),
    )

    fun getInfoByTypeName(typeName: String?): RoomDataSourceTypeInfo<*>? {
        return roomTypeMap.firstOrNull { x -> x.typeName == typeName }
    }
}