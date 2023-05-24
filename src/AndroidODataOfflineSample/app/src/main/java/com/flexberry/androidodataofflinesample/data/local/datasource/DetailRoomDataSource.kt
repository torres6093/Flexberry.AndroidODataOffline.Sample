package com.flexberry.androidodataofflinesample.data.local.datasource

import com.flexberry.androidodataofflinesample.data.local.datasource.room.RoomDataSource
import com.flexberry.androidodataofflinesample.data.local.datasource.room.RoomDataSourceTypeManager
import com.flexberry.androidodataofflinesample.data.local.entities.DetailEntity
import javax.inject.Inject

class DetailRoomDataSource @Inject constructor(
    typeManager: RoomDataSourceTypeManager
) : RoomDataSource<DetailEntity>(DetailEntity::class, typeManager) {

}