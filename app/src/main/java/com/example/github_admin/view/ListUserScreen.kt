package com.example.github_admin.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavController
import com.example.github_admin.view.compose.ListUserItem
import com.example.github_admin.viewmodel.ListUserViewModel

@Composable
fun ListUserScreen(navController: NavController, listUserViewModel: ListUserViewModel) {

    val listUser by listUserViewModel.listUser.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp)) {
            Text(
                text = "Github User",
                modifier = Modifier.align(Alignment.TopCenter),
                fontSize = 30.sp
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(listUser) { item ->
                Box(modifier = Modifier.clickable(onClick = dropUnlessResumed {
                    navController.navigate("detail_user/${item.login}")
                })) {
                    ListUserItem(item)
                }

            }
            item {
                LaunchedEffect(true) {
                    listUserViewModel.getListUser()
                }
            }
        }
    }
}