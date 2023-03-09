package com.benoitmanhes.designsystem.res.icons

import androidx.compose.ui.graphics.vector.ImageVector
import com.benoitmanhes.designsystem.res.icons.iconpack.Add
import com.benoitmanhes.designsystem.res.icons.iconpack.Box
import com.benoitmanhes.designsystem.res.icons.iconpack.Compass
import com.benoitmanhes.designsystem.res.icons.iconpack.PositionCurrent
import com.benoitmanhes.designsystem.res.icons.iconpack.Difficulty
import com.benoitmanhes.designsystem.res.icons.iconpack.Explore
import com.benoitmanhes.designsystem.res.icons.iconpack.ExploreFilled
import com.benoitmanhes.designsystem.res.icons.iconpack.EyeClose
import com.benoitmanhes.designsystem.res.icons.iconpack.EyeOpen
import com.benoitmanhes.designsystem.res.icons.iconpack.Favorite
import com.benoitmanhes.designsystem.res.icons.iconpack.FavoriteFilled
import com.benoitmanhes.designsystem.res.icons.iconpack.Home
import com.benoitmanhes.designsystem.res.icons.iconpack.HomeFilled
import com.benoitmanhes.designsystem.res.icons.iconpack.Layer
import com.benoitmanhes.designsystem.res.icons.iconpack.Location
import com.benoitmanhes.designsystem.res.icons.iconpack.Position
import com.benoitmanhes.designsystem.res.icons.iconpack.Logo
import com.benoitmanhes.designsystem.res.icons.iconpack.Mountain
import com.benoitmanhes.designsystem.res.icons.iconpack.Newspaper
import com.benoitmanhes.designsystem.res.icons.iconpack.Profile
import com.benoitmanhes.designsystem.res.icons.iconpack.ProfileFilled
import com.benoitmanhes.designsystem.res.icons.iconpack.Search

object CTIconPack

@Suppress("ObjectPropertyName")
private var _CTIcons: List<ImageVector>? = null

val CTIconPack.CTIcons: List<ImageVector>
    get() {
        if (_CTIcons != null) {
            return _CTIcons!!
        }
        _CTIcons = listOf(
            Add,
            Box,
            Compass,
            Difficulty,
            Explore,
            ExploreFilled,
            EyeClose,
            EyeOpen,
            Favorite,
            FavoriteFilled,
            Home,
            HomeFilled,
            Layer,
            Location,
            Logo,
            Mountain,
            Newspaper,
            Position,
            PositionCurrent,
            Profile,
            ProfileFilled,
            Search,
        )
        return _CTIcons!!
    }
