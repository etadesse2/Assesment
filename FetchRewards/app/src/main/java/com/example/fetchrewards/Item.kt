package com.example.fetchrewards

data class Item(
    val id: Int,
    val listId: Int,
    val name: String?  // Assuming the API might return null for name
)
