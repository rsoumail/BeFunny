package com.rsoumail.befunny.feature.funnyreal.ui.recorder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rsoumail.befunny.feature.funnyreal.R
import com.rsoumail.befunny.feature.funnyreal.ui.common.model.Funny

@Composable
fun FunniesGallery(
    funnies: List<Funny>
) {
    if (funnies.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.empty_funnies)
            )
        }
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalItemSpacing = 15.dp,
            contentPadding = PaddingValues(15.dp),
            modifier = Modifier
        ) {
            items(funnies) { funny ->
                Box {
                    funny.metaData?.thumbs?.asImageBitmap()?.let {
                        Image(
                            bitmap = it,
                            contentDescription = stringResource(R.string.video_description),
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))

                        )
                    }
                    funny.metaData?.duration?.let {
                        Text(
                            text = it,
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .offset(5.dp)
                        )
                    }
                }
            }
        }
    }
}