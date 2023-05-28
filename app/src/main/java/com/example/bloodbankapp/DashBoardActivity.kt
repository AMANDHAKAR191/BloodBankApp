package com.example.bloodbankapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import coil.compose.AsyncImage
import com.example.bloodbankapp.ui.theme.BloodBankAppTheme

class DashBoardActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodBankAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DashBoardUI()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DashBoardUI() {
        val constraints = ConstraintSet {
            val scaffoldTopBar = createRefFor("scaffoldTopBar")
            val asyncImageProfile = createRefFor("asyncImageProfile")
            val textUserName = createRefFor("textUserName")
            val buttonSignOut = createRefFor("buttonSignOut")
            val cardMenu = createRefFor("cardMenu")
            val cardSubMenu = createRefFor("cardSubMenu")

            constrain(scaffoldTopBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(asyncImageProfile) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(textUserName) {
                top.linkTo(asyncImageProfile.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(buttonSignOut) {
                top.linkTo(textUserName.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(cardMenu) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
            constrain(cardSubMenu) {
                top.linkTo(cardMenu.top)
                end.linkTo(cardMenu.start)
            }

        }
        ConstraintLayout(constraints, modifier = Modifier) {
            var expanded by remember {
                mutableStateOf(false)
            }
            var subMenuExpanded by remember {
                mutableStateOf(false)
            }
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                "Dashboard",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { expanded = !expanded }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "Localized description"
                                )
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier
                                    .layoutId("cardMenu")
                                    .padding(top = 15.dp, end = 45.dp)
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Refresh") },
                                    onClick = { subMenuExpanded = !subMenuExpanded }
                                )
                                DropdownMenuItem(
                                    text = { Text("Settings") },
                                    onClick = { /* Handle settings! */ }
                                )
                                Divider()
                                DropdownMenuItem(
                                    text = { Text("Send Feedback") },
                                    onClick = { /* Handle send feedback! */ }
                                )
                            }
                            DropdownMenu(
                                expanded = subMenuExpanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier
                                    .layoutId("cardSubMenu")
                                    .padding(top = 15.dp, end = 45.dp)
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Change pin") },
                                    onClick = { subMenuExpanded = !subMenuExpanded }
                                )
                                DropdownMenuItem(
                                    text = { Text("Remove pin") },
                                    onClick = { /* Handle settings! */ }
                                )
                            }
                        }
                    )
                },
                content = { innerPadding ->
                    LazyColumn(
                        contentPadding = innerPadding,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                    }
                },
                modifier = Modifier.layoutId("scaffoldTopBar")
            )
            AsyncImage(
                model = "name",
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .layoutId("asyncImageProfile")
                    .padding(top = 100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "name",
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.layoutId("textUserName")
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {}, modifier = Modifier.layoutId("buttonSignOut")) {
                Text(text = "Sign out")
            }

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BloodBankAppTheme {
            DashBoardUI()
        }
    }
}


