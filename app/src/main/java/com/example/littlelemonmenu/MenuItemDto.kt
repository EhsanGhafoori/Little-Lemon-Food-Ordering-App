package com.example.littlelemonmenu

data class MenuItemDto(
    val id: Int,
    val title: String,
    val price: Double,
    val category: String,
    val description: String? = null,
    val image: String? = null,
) {
    fun toEntity(): MenuItemEntity =
        MenuItemEntity(
            id = id,
            title = title,
            description = description.orEmpty(),
            price = price,
            category = category,
            image = image.orEmpty(),
        )
}
