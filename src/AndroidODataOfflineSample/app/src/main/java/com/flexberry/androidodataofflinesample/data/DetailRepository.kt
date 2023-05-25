package com.flexberry.androidodataofflinesample.data

import com.flexberry.androidodataofflinesample.data.di.DetailLocalDatasource
import com.flexberry.androidodataofflinesample.data.di.DetailNetworkDatasource
import com.flexberry.androidodataofflinesample.data.local.entities.DetailEntity
import com.flexberry.androidodataofflinesample.data.local.entities.MasterEntity
import com.flexberry.androidodataofflinesample.data.local.interfaces.LocalDataSource
import com.flexberry.androidodataofflinesample.data.model.Detail
import com.flexberry.androidodataofflinesample.data.model.asDataModel
import com.flexberry.androidodataofflinesample.data.network.interfaces.NetworkDataSource
import com.flexberry.androidodataofflinesample.data.network.models.NetworkDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * Репозиторий для [Detail]
 */
class DetailRepository @Inject constructor(
    @DetailNetworkDatasource private val networkDataSource: NetworkDataSource<NetworkDetail>,
    @DetailLocalDatasource private val localDataSource: LocalDataSource<DetailEntity>,
) {
    /**
     * Получение списка детейлов в режиме онлайн.
     *
     * @return [List] of [Detail].
     */
    fun getDetailsOnline(): Flow<List<Detail>> {
        return flowOf(networkDataSource.readObjects().map { it.asDataModel() })
    }

    /**
     * Сохранение детейлов в режиме онлайн.
     *
     * @param dataObjects Список объектов.
     */
    fun updateDetailsOnline(dataObjects: List<Detail>) {
        networkDataSource.updateObjects(dataObjects.map { it.asNetworkModel() })
    }

    /**
     * Получение списка детейлов в режиме оффлайн.
     *
     * @return [List] of [Detail].
     */
    fun getDetailsOffline(): Flow<List<Detail>> {
        return flowOf(localDataSource.readObjects().map { it.asDataModel() })
    }

    /**
     * Сохранение детейлов в режиме оффлайн.
     *
     * @param dataObjects Список объектов.
     */
    fun updateDetailsOffline(dataObjects: List<Detail>) {
        localDataSource.updateObjects(dataObjects.map { it.asLocalModel() })
    }

    fun insertDetailOffline() {
        var m = MasterEntity(
            name = "Master"
        )
        var d1 = DetailEntity(
            master = m,
            name = "Detail"
        )
        var d2 = DetailEntity(
            master = m,
            name = "Jepa"
        )
        localDataSource.createObjects(d1, d2)
    }
}