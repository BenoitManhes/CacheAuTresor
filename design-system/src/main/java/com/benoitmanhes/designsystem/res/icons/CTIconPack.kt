package com.benoitmanhes.designsystem.res.icons

import androidx.compose.ui.graphics.vector.ImageVector
import com.benoitmanhes.designsystem.res.icons.iconpack.Add
import com.benoitmanhes.designsystem.res.icons.iconpack.Book
import com.benoitmanhes.designsystem.res.icons.iconpack.BookFilled
import com.benoitmanhes.designsystem.res.icons.iconpack.Box
import com.benoitmanhes.designsystem.res.icons.iconpack.BoxBig
import com.benoitmanhes.designsystem.res.icons.iconpack.BoxMedium
import com.benoitmanhes.designsystem.res.icons.iconpack.BoxMicro
import com.benoitmanhes.designsystem.res.icons.iconpack.BoxSmall
import com.benoitmanhes.designsystem.res.icons.iconpack.Chevron
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
import com.benoitmanhes.designsystem.res.icons.iconpack.Coop
import com.benoitmanhes.designsystem.res.icons.iconpack.Crown
import com.benoitmanhes.designsystem.res.icons.iconpack.Disconnect
import com.benoitmanhes.designsystem.res.icons.iconpack.DoneStamp
import com.benoitmanhes.designsystem.res.icons.iconpack.Ensign
import com.benoitmanhes.designsystem.res.icons.iconpack.Etoile2
import com.benoitmanhes.designsystem.res.icons.iconpack.Etoile4
import com.benoitmanhes.designsystem.res.icons.iconpack.Flag
import com.benoitmanhes.designsystem.res.icons.iconpack.Globe
import com.benoitmanhes.designsystem.res.icons.iconpack.Key
import com.benoitmanhes.designsystem.res.icons.iconpack.Mystery
import com.benoitmanhes.designsystem.res.icons.iconpack.Parchment
import com.benoitmanhes.designsystem.res.icons.iconpack.Piste
import com.benoitmanhes.designsystem.res.icons.iconpack.Rank
import com.benoitmanhes.designsystem.res.icons.iconpack.AddBox
import com.benoitmanhes.designsystem.res.icons.iconpack.Alert
import com.benoitmanhes.designsystem.res.icons.iconpack.Create
import com.benoitmanhes.designsystem.res.icons.iconpack.CreateFilled
import com.benoitmanhes.designsystem.res.icons.iconpack.GlobeDD
import com.benoitmanhes.designsystem.res.icons.iconpack.GlobeDM
import com.benoitmanhes.designsystem.res.icons.iconpack.GlobeDMS
import com.benoitmanhes.designsystem.res.icons.iconpack.Minus
import com.benoitmanhes.designsystem.res.icons.iconpack.PersonRemove
import com.benoitmanhes.designsystem.res.icons.iconpack.RemoveRoad
import com.benoitmanhes.designsystem.res.icons.resicons.Edit
import com.benoitmanhes.designsystem.utils.IconSpec

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
            AddBox,
            Alert,
            Book,
            BookFilled,
            Box,
            BoxBig,
            BoxMedium,
            BoxMicro,
            BoxSmall,
            Chevron,
            Compass,
            Coop,
            Create,
            CreateFilled,
            Crown,
            Difficulty,
            Disconnect,
            DoneStamp,
            Ensign,
            Etoile2,
            Etoile4,
            Explore,
            ExploreFilled,
            EyeClose,
            EyeOpen,
            Favorite,
            FavoriteFilled,
            Flag,
            Globe,
            GlobeDD,
            GlobeDM,
            GlobeDMS,
            Home,
            HomeFilled,
            Key,
            Layer,
            Location,
            Logo,
            Minus,
            Mountain,
            Mystery,
            Newspaper,
            Parchment,
            PersonRemove,
            Piste,
            Position,
            PositionCurrent,
            Profile,
            ProfileFilled,
            Rank,
            RemoveRoad,
            Search,
        )
        return _CTIcons!!
    }

val CTIconPack.ResIcons: List<IconSpec>
    get() = listOf(
        Edit,
    )
