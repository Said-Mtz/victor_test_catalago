package com.example.examenintercam.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.examenintercam.core.model.BeerRequestModel
import com.example.examenintercam.core.model.BeerRequestModelItem
import com.example.examenintercam.data.Repository
import com.example.examenintercam.room.AppDataBase
import com.example.examenintercam.room.user.UserEntity
import com.example.examenintercam.ui.StatusRequest
import com.example.examenintercam.ui.fragments.favorites.FavoritesModel

class MainViewModel : ViewModel() {

    private val repository = Repository()


    private val userList =
        listOf(UserEntity(id = 1, email = "victorjr.mtz@hotmail.com", password = "1234"))

    val statusLogin: LiveData<Pair<StatusRequest, Boolean?>> get() = _statusLogin
    private val _statusLogin = MutableLiveData<Pair<StatusRequest, Boolean?>>()

    val statusSaveFavorite: LiveData<Pair<StatusRequest, Boolean?>> get() = _statusSaveFavorite
    private val _statusSaveFavorite = MutableLiveData<Pair<StatusRequest, Boolean?>>()

    val beerData: LiveData<Pair<StatusRequest, Pair<BeerRequestModel, List<FavoritesModel>>?>> get() = _beerData
    private val _beerData =
        MutableLiveData<Pair<StatusRequest, Pair<BeerRequestModel, List<FavoritesModel>>?>>(
            Pair(StatusRequest.NONE, Pair(BeerRequestModel(), listOf()))
        )

    val favoriteBeerData: LiveData<Pair<StatusRequest, List<FavoritesModel>?>> get() = _favoriteBeerData
    private val _favoriteBeerData = MutableLiveData<Pair<StatusRequest, List<FavoritesModel>?>>()

    private lateinit var beerSelected: BeerRequestModelItem

    fun setSelectedModel(modelSelected: BeerRequestModelItem) {
        beerSelected = modelSelected
    }

    fun getSelectedModel() = beerSelected

    fun requestBeerData() {
        _beerData.value = Pair(
            StatusRequest.LOADING, _beerData.value?.second ?: Pair(BeerRequestModel(), listOf())
        )

        val oldList = _beerData.value?.second
        repository.executeBeerData(oldList) {
            _beerData.postValue(it)
        }

    }

    fun initDataBase(context: Context) {
        Repository.dataBase = Room.databaseBuilder(
            context,
            AppDataBase::class.java, AppDataBase.DATABASE_NAME
        ).build()
    }

    fun requestUser(user: String, password: String) {
        saveUser(userList) {
            _statusLogin.postValue(Pair(StatusRequest.LOADING, null))
            repository.executeGetUser(user, password) {
                _statusLogin.postValue(it)
            }
        }

    }

    fun saveFavorite(beerItem: BeerRequestModelItem) {
        _statusSaveFavorite.value = Pair(StatusRequest.LOADING, null)
        repository.executeSetFavorite(beerItem) { result ->
            _statusSaveFavorite.postValue(result)
        }
    }

    fun saveFavorite(
        beerItem: FavoritesModel,
        rate: Double = 0.0,
        callback: (List<FavoritesModel>) -> Unit
    ) {
        _statusSaveFavorite.value = Pair(StatusRequest.LOADING, null)
        repository.executeSetFavorite(beerItem, rate, callback = callback, observer = {
            _statusSaveFavorite.postValue(it)
        })
    }

    fun getAllFavorite() = _statusSaveFavorite.value ?: listOf<FavoritesModel>()

    fun getFavorite(callback: (List<FavoritesModel>) -> Unit = {}) {
        _favoriteBeerData.value = Pair(StatusRequest.LOADING, null)
        repository.executeGetFavorite(callback = {
            callback(it)
            _favoriteBeerData.postValue(Pair(StatusRequest.SUCCESS,it))
        })
    }

    fun resetStatusLogin() {
        _statusLogin.postValue(Pair(StatusRequest.NONE, null))
    }

    private fun saveUser(listUser: List<UserEntity>, execute: () -> Unit) {
        repository.executeSaveUser(listUser) {
            execute()
        }
    }
}