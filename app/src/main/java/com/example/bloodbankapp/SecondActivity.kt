package com.example.bloodbankapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.bloodbankapp.ui.theme.BloodBankAppTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodBankAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    welcomeUI()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun welcomeUI() {
        val constraints = ConstraintSet {
            val imageWelcome = createRefFor("imageWelcome")
            val buttonSignIn = createRefFor("buttonSubmit")
            val textHeadLine = createRefFor("textHeadLine")
            val textButtonDescription = createRefFor("textButtonDescription")
            val assistChipA = createRefFor("assistChipA")
            val assistChipB = createRefFor("assistChipB")
            val assistChipO = createRefFor("assistChipO")
            val assistChipAB = createRefFor("assistChipAB")
            val assistChipPlus = createRefFor("assistChipPlus")
            val assistChipMinus = createRefFor("assistChipMinus")
            val checkBoxLayout = createRefFor("checkBoxLayout")
            constrain(imageWelcome) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.value(150.dp)
                width = Dimension.value(150.dp)
            }
            constrain(textHeadLine) {
                top.linkTo(imageWelcome.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(textButtonDescription) {
                top.linkTo(textHeadLine.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(assistChipA) {
                top.linkTo(textButtonDescription.bottom)
                start.linkTo(parent.start)
                width = Dimension.percent(0.5F)
                height = Dimension.value(100.dp)
            }
            constrain(assistChipB) {
                top.linkTo(assistChipA.top)
                end.linkTo(parent.end)
                start.linkTo(assistChipA.end)
                width = Dimension.percent(0.5F)
                height = Dimension.value(100.dp)
            }
            constrain(assistChipO) {
                top.linkTo(assistChipA.bottom)
                start.linkTo(parent.start)
                width = Dimension.percent(0.5F)
                height = Dimension.value(100.dp)
            }
            constrain(assistChipAB) {
                top.linkTo(assistChipO.top)
                end.linkTo(parent.end)
                start.linkTo(assistChipO.end)
                width = Dimension.percent(0.5F)
                height = Dimension.value(100.dp)
            }
            constrain(assistChipPlus) {
                top.linkTo(assistChipO.bottom)
                end.linkTo(assistChipO.end)
            }
            constrain(assistChipMinus) {
                top.linkTo(assistChipAB.bottom)
                start.linkTo(assistChipAB.start)
            }
            constrain(checkBoxLayout) {
                bottom.linkTo(buttonSignIn.top)
            }
            constrain(buttonSignIn) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                end.linkTo(parent.end)
                width = Dimension.matchParent
            }
        }
        ConstraintLayout(
            constraints,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.imge_blood_drop),
                contentDescription = "Welcome image",
                modifier = Modifier
                    .layoutId("imageWelcome")
                    .padding(top = 50.dp)
            )
            Text(
                text = "Please pick your blood type",
                modifier = Modifier
                    .layoutId("textHeadLine"),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("textButtonDescription")
            ) {
                Text(
                    text = "Don't know about your blood type?"
                )
            }
            var chipASelectedValue by remember {
                mutableStateOf(false)
            }
            var chipBSelectedValue by remember {
                mutableStateOf(false)
            }
            var chipOSelectedValue by remember {
                mutableStateOf(false)
            }
            var chipABSelectedValue by remember {
                mutableStateOf(false)
            }
            InputChip(
                selected = chipASelectedValue,
                onClick = {
                    chipASelectedValue = !chipASelectedValue
                    if (chipBSelectedValue) {
                        chipBSelectedValue = !chipBSelectedValue
                    } else if (chipOSelectedValue) {
                        chipOSelectedValue = !chipOSelectedValue
                    } else if (chipABSelectedValue) {
                        chipABSelectedValue = !chipABSelectedValue
                    }
                },
                label = {
                    Text(
                        text = "A", textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier
                    .layoutId("assistChipA")
                    .padding(all = 10.dp)
            )

            InputChip(
                selected = chipBSelectedValue,
                onClick = {
                    chipBSelectedValue = !chipBSelectedValue
                    if (chipASelectedValue) {
                        chipASelectedValue = !chipASelectedValue
                    } else if (chipOSelectedValue) {
                        chipOSelectedValue = !chipOSelectedValue
                    } else if (chipABSelectedValue) {
                        chipABSelectedValue = !chipABSelectedValue
                    }
                },
                label = {
                    Text(
                        text = "B", textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier
                    .layoutId("assistChipB")
                    .padding(all = 10.dp)
            )

            InputChip(
                selected = chipOSelectedValue,
                onClick = {
                    chipOSelectedValue = !chipOSelectedValue
                    if (chipBSelectedValue) {
                        chipBSelectedValue = !chipBSelectedValue
                    } else if (chipASelectedValue) {
                        chipASelectedValue = !chipASelectedValue
                    } else if (chipABSelectedValue) {
                        chipABSelectedValue = !chipABSelectedValue
                    }
                },
                label = {
                    Text(
                        text = "O", textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier
                    .layoutId("assistChipO")
                    .padding(all = 10.dp)
            )

            InputChip(
                selected = chipABSelectedValue,
                onClick = {
                    chipABSelectedValue = !chipABSelectedValue
                    if (chipBSelectedValue) {
                        chipBSelectedValue = !chipBSelectedValue
                    } else if (chipOSelectedValue) {
                        chipOSelectedValue = !chipOSelectedValue
                    } else if (chipASelectedValue) {
                        chipASelectedValue = !chipASelectedValue
                    }
                },
                label = {
                    Text(
                        text = "AB", textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier
                    .layoutId("assistChipAB")
                    .padding(all = 10.dp)
            )
            var chipPlusSelectedValue by remember {
                mutableStateOf(false)
            }
            var chipMinusSelectedValue by remember {
                mutableStateOf(false)
            }
            InputChip(
                selected = chipPlusSelectedValue,
                onClick = {
                    chipPlusSelectedValue = !chipPlusSelectedValue
                    if (chipMinusSelectedValue) chipMinusSelectedValue = !chipMinusSelectedValue
                },
                label = {},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_plus_24),
                        contentDescription = "Plus",
                    )
                },
                modifier = Modifier
                    .layoutId("assistChipPlus")
                    .padding(all = 10.dp)
            )


            InputChip(
                selected = chipMinusSelectedValue,
                onClick = {
                    chipMinusSelectedValue = !chipMinusSelectedValue
                    if (chipPlusSelectedValue) chipPlusSelectedValue = !chipPlusSelectedValue
                },
                label = {},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_horizontal_rule_24),
                        contentDescription = "Plus",
                    )
                },
                modifier = Modifier
                    .layoutId("assistChipMinus")
                    .padding(all = 10.dp)
            )
            val (checkedState, onStateChange) = remember { mutableStateOf(true) }
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .layoutId("checkBoxLayout")
                    .toggleable(
                        value = checkedState,
                        onValueChange = { onStateChange(!checkedState) },
                        role = Role.Checkbox
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedState,
                    onCheckedChange = null // null recommended for accessibility with screenreaders
                )
                Text(
                    text = "I want to receive the notification about the blood donation camp",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Button(
                onClick = {
                    finish()
                },
                modifier = Modifier
                    .layoutId("buttonSubmit")
                    .padding(start = 60.dp, end = 60.dp, top = 10.dp, bottom = 50.dp)
            ) {
                Text(text = "Submit")
            }
        }
    }

    @Preview
    @Composable
    fun previewApp() {
        BloodBankAppTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                welcomeUI()
            }
        }
    }
}