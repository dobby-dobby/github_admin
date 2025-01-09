package com.example.github_admin.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.github_admin.viewmodel.DetailUserViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun DetailUserScreen(
    userLogin: String,
    detailUserViewModel: DetailUserViewModel,
    onGoBack: () -> Unit
) {

    val userData by detailUserViewModel.userData.collectAsState()

    LaunchedEffect(userLogin) {
        detailUserViewModel.getDetailUser(userLogin)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Detail User",
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            onGoBack()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                )
            },
        ) { innerPadding ->
            Column {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(10.dp, 10.dp)
                        .background(Color.White)
                        .shadow(
                            elevation = 1.dp,
                            spotColor = Color.Gray
                        )
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(0.3f)
                                    .padding(8.dp)
                            ) {
                                GlideImage(
                                    model = userData?.avatar_url,
                                    contentDescription = userData?.gravatar_id
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .weight(0.7f)
                                    .padding(8.dp)
                            ) {
                                userData?.let {
                                    Text(
                                        it.login,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                }
                                HorizontalDivider(thickness = 2.dp)

                                Row(modifier = Modifier.padding(0.dp, 10.dp)) {
                                    Icon(
                                        Icons.Rounded.LocationOn,
                                        contentDescription = "icon location"
                                    )
                                    userData?.location?.let { Text(text = it) }
                                }

                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(8.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(64.dp)
                                .background(color = Color.LightGray, shape = CircleShape)
                        ) {
                            Icon(
                                Icons.Rounded.Face,
                                contentDescription = "icon location"
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = userData?.followers.toString(),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Follower",
                            color = Color.Gray
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(8.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(64.dp)
                                .background(color = Color.LightGray, shape = CircleShape)
                        ) {
                            Icon(
                                Icons.Rounded.Face,
                                contentDescription = "icon location"
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = userData?.following.toString(),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Flowing",
                            color = Color.Gray
                        )
                    }
                }

                Text("Blog", fontWeight = FontWeight.Bold)
                Text("https://blog.abc")
            }

        }

    }

}