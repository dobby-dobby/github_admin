package com.example.github_admin.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.github_admin.model.User

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ListUserItem(
    user: User
) {
    Box(
        modifier = Modifier
            .padding(10.dp, 10.dp)
            .background(Color.White)
            .shadow(
                elevation = 1.dp,
                spotColor = Color.Gray
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier = Modifier
                    .weight(0.3f)
                    .padding(8.dp)
            ) {
                GlideImage( model = user.avatar_url, contentDescription = user.gravatar_id)
            }

            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .padding(8.dp)
            ) {
                Text(user.login, fontWeight = FontWeight.Bold)
                HorizontalDivider(thickness = 2.dp)
                Text(buildAnnotatedString {
                    withLink(LinkAnnotation.Url(
                        user.html_url, TextLinkStyles(style = SpanStyle(color = Color.Blue))
                    )){
                        append(user.html_url)
                    }
                })
            }
        }
    }
}