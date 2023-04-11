package com.example.clothingsuggester.data

import com.example.clothingsuggester.R
import com.example.clothingsuggester.domain.Cloth

object DataManager {
    private val clothesList: List<Cloth> = listOf(
        Cloth(1, R.drawable.black_jacket_colorful_shirts_blue_pants, 10.0, 22.0),
        Cloth(2, R.drawable.black_jacket_grey_pants_black_shoes, 20.0, 25.0),
        Cloth(3, R.drawable.black_shirt_camel_shorts_black_shoes, 27.0, 40.0),
        Cloth(4, R.drawable.blue_jacket_grey_shirt_blue_pants, 5.0, 15.0),
        Cloth(5, R.drawable.blue_jens_orange_chemise_camel_shoes, 30.0, 40.0),
        Cloth(6, R.drawable.camel_pants_white_shirt_grey_chemise_brown_shoes, 18.0, 25.0),
        Cloth(7, R.drawable.camel_shirt_grey_shoes_green_shorts, 27.0, 40.0),
        Cloth(8, R.drawable.chemise_blue_jens_white_shoes, 25.0, 40.0),
        Cloth(9, R.drawable.flower_shirt_grey_jens_black_shoes, 27.0, 40.0),
        Cloth(10, R.drawable.green_chemise_sandal_brown_short, 30.0, 45.0),
        Cloth(11, R.drawable.grey_jacket_black_jens_brown_shoes, 15.0, 25.0),
        Cloth(12, R.drawable.half_chemise_blue_jens_brown_shoes, 20.0, 30.0),
        Cloth(13, R.drawable.red_chemise_white_shirt_camel_pants, 15.0, 25.0),
        Cloth(14, R.drawable.red_jacket_blue_pants_brown_shoes, 5.0, 20.0),
        Cloth(15, R.drawable.white_black_chemise_blue_jens_blue_shoes, 30.0, 40.0),
        Cloth(16, R.drawable.white_jacket_white_cap_blue_jens, 10.0, 20.0),
        Cloth(17, R.drawable.yellow_chemise_black_cap_black_pants, 15.0, 25.0),
        Cloth(18, R.drawable.yellow_white_chemise_blue_pants_grey_shoes, 20.0, 30.0),
        )
    val clothes = clothesList
}
