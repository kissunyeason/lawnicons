package app.lawnchair.lawnicons.model

import kotlinx.collections.immutable.ImmutableList

data class IconRequest(
    val name: String,
    val component: String,
)

data class IconRequestModel(
    val list: ImmutableList<IconRequest>,
    val iconCount: Int,
)
