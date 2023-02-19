package com.example.forstart.pages.cartpage.data.repository

import com.example.forstart.pages.cartpage.data.model.Prudact

class CartRepository {
    fun fillList():List<Prudact>{
        var prudactList = mutableListOf<Prudact>()

//        for(i in 1..10){
//            prudactList.add(Prudact(
//                id = i,
//                name = "Item $i",
//                price = i*1.5,
//                quntity = 0
//            ))
//        }
        return  prudactList
    }
}