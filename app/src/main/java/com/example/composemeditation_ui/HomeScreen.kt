package com.example.composemeditation_ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composemeditation_ui.data.BottomNavItem
import com.example.composemeditation_ui.data.Feature
import com.example.composemeditation_ui.helper.quadBezierFromTo
import com.example.composemeditation_ui.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DeepBlue)
    ) {
        Column {
            GreetingSection()

            ChipsSection(chips = listOf("Sweet Sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeaturesSection(
                features = listOf(
                    Feature(
                        title = "Sleep meditation",
                        com.example.composemeditation_ui.R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        title = "Tips for sleeping",
                        com.example.composemeditation_ui.R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Feature(
                        title = "Night island",
                        iconID = com.example.composemeditation_ui.R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Feature(
                        title = "Calming sounds",
                        com.example.composemeditation_ui.R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    )
                )
            )


        }
        BottomMenu(
            items = listOf(
                BottomNavItem("Home", com.example.composemeditation_ui.R.drawable.ic_home),
                BottomNavItem("Meditate", com.example.composemeditation_ui.R.drawable.ic_bubble),
                BottomNavItem("Sleep", com.example.composemeditation_ui.R.drawable.ic_moon),
                BottomNavItem("Music", com.example.composemeditation_ui.R.drawable.ic_music),
                BottomNavItem("Profile", com.example.composemeditation_ui.R.drawable.ic_profile),
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )

    }


}

@Composable
fun GreetingSection(name: String = "Ahmed") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column(verticalArrangement = Arrangement.Center) {


            Text(
                text = "Good morning $name",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "we wish you have a goodDay",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1
            )

        }
        Icon(
            painter = painterResource(id = com.example.composemeditation_ui.R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )

    }

}

@Composable
fun ChipsSection(chips: List<String>) {
    val selectedChip = remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp)
                    .clickable {
                        selectedChip.value = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChip.value == it) ButtonBlue else DarkerButtonBlue
                    )
                    .padding(15.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = chips[it], color = TextWhite)

            }

        }

    }
}

@Composable
fun CurrentMeditation(
    color: Color = LightRed

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .background(color = color)
            .padding(vertical = 20.dp, horizontal = 15.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
    )
    {
        Column(verticalArrangement = Arrangement.Center) {


            Text(
                text = "Daily Thought",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "Meditation.3-10 min",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )

        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
                .clickable { }
        ) {
            Icon(
                painter = painterResource(id = com.example.composemeditation_ui.R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )

        }


    }


}

@ExperimentalFoundationApi
@Composable
fun FeaturesSection(features: List<Feature>) {
    Column(
        modifier = Modifier.fillMaxWidth()

    ) {
        Text(
            text = "Feature",
            style = MaterialTheme.typography.h1,
            color = TextWhite,
            modifier = Modifier.padding(15.dp)
        )
        //Fixed constructor argument-> "count" is for identify how many items per row you need
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {

            items(features.size) {
                FeatureItem(Feature = features[it])
            }

        }

    }

}

@Composable
fun FeatureItem(Feature: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(7.5.dp)
            .aspectRatio(1f)
            .background(Feature.darkColor)
            .clip(RoundedCornerShape(10.dp))
    )
    {
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        //medium colored path
        val mediumCoLorPoint1 = Offset(0f, height * 0.3f)
        val mediumCoLorPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumCoLorPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumCoLorPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumCoLorPoint5 = Offset(width * 1.4f, -height.toFloat())
        val mediumColorPath = Path().apply {
            //move the cursor to start point
            moveTo(mediumCoLorPoint1.x, mediumCoLorPoint1.y)
            //draw a curve that we want to fill it with medium color
            quadBezierFromTo(mediumCoLorPoint1, mediumCoLorPoint2)
            quadBezierFromTo(mediumCoLorPoint2, mediumCoLorPoint3)
            quadBezierFromTo(mediumCoLorPoint3, mediumCoLorPoint4)
            quadBezierFromTo(mediumCoLorPoint4, mediumCoLorPoint5)
            //then connect the curve we made to this two lines to make a area to fill it with medium color
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            //close the  path
            close()

        }
        //light colored path
        val lightCoLorPoint1 = Offset(0f, height * 0.35f)
        val lightCoLorPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightCoLorPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightCoLorPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightCoLorPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)
        val lightColorPath = Path().apply {
            //move the cursor to start point
            moveTo(lightCoLorPoint1.x, lightCoLorPoint1.y)
            //draw a curve that we want to fill it with medium color
            quadBezierFromTo(lightCoLorPoint1, lightCoLorPoint2)
            quadBezierFromTo(lightCoLorPoint2, lightCoLorPoint3)
            quadBezierFromTo(lightCoLorPoint3, lightCoLorPoint4)
            quadBezierFromTo(lightCoLorPoint4, lightCoLorPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()

        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = mediumColorPath, color = Feature.mediumColor)
            drawPath(path = lightColorPath, color = Feature.lightColor)

        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = Feature.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(
                    Alignment.TopStart
                )
            )
            Icon(
                painter = painterResource(id = Feature.iconID),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart),
                tint = Color.White
            )

            Box(modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 15.dp)
                .clickable { }
                .background(ButtonBlue)
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.BottomEnd), contentAlignment = Alignment.Center) {
                Text(
                    text = "Start",
                    color = TextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,

                    )

            }


        }

    }
}

@Composable
fun BottomMenu(
    items: List<BottomNavItem>,
    modifier: Modifier,


    ) {
    val selectedItemIndex = remember {
        mutableStateOf(0)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEachIndexed { index, bottomNavItem ->

            BottomNavItem(bottomNavItem, isSelected = index == selectedItemIndex.value) {
                selectedItemIndex.value = index

            }


        }


    }


}

@Composable
fun BottomNavItem(
    Item: BottomNavItem,
    selectedIConColor: Color = ButtonBlue,
    inactiveIconColor: Color = Color.White,
    selectedTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    isSelected: Boolean = false,
    OnItemClick: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(6.dp)
            .clickable {
                OnItemClick
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(if (isSelected) selectedIConColor else Color.Transparent)
                .padding(10.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = Item.icon),
                contentDescription = null,
                tint = if (isSelected) selectedTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = Item.title,
            color = (if (isSelected) selectedTextColor else inactiveTextColor)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MeditationUIYouTubeTheme{
        HomeScreen()
    }


}
