package com.example.shoplistproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoplistproject.data.ShopListRepositoryImpl
import com.example.shoplistproject.domain.DeleteShopItemUseCase
import com.example.shoplistproject.domain.EditShopItemUseCase
import com.example.shoplistproject.domain.GetShopListUseCase
import com.example.shoplistproject.domain.ShopItem
import com.example.shoplistproject.domain.ShopListRepository

class MainViewModel : ViewModel(){

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()
    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }
}