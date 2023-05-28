package com.plcoding.composegooglesignincleanarchitecture.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.bloodbankapp.R

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    onSkipNowClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    val constraints = ConstraintSet {
        val imageWelcome = createRefFor("imageWelcome")
        val buttonSignIn = createRefFor("buttonSignIn")
        val outlinedButtonCreateAccount = createRefFor("outlinedButtonCreateAccount")
        val textButtonLearnMore = createRefFor("textButtonLearnMore")
        val textButtonSkipNow = createRefFor("textButtonSkipNow")

        constrain(imageWelcome) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(buttonSignIn) {
            top.linkTo(imageWelcome.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            end.linkTo(parent.end)
            width = Dimension.matchParent
        }
        constrain(outlinedButtonCreateAccount) {
            top.linkTo(buttonSignIn.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            end.linkTo(parent.end)
            width = Dimension.matchParent
        }
        constrain(textButtonLearnMore) {
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
        }
        constrain(textButtonSkipNow) {
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
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
            modifier = Modifier.layoutId("imageWelcome")
        )
        Button(
            onClick = onSignInClick,
            modifier = Modifier
                .layoutId("buttonSignIn")
                .padding(start = 60.dp, end = 60.dp, top = 100.dp)
        ) {
            Text(text = "Sign In")
        }
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .layoutId("outlinedButtonCreateAccount")
                .padding(start = 60.dp, end = 60.dp)
        ) {
            Text(text = "Create Account")
        }
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.layoutId("textButtonLearnMore")
        ) {
            Text(text = "Learn More")
        }
        TextButton(onClick = onSkipNowClick, modifier = Modifier.layoutId("textButtonSkipNow")) {
            Text(text = "Skip Now")
        }

    }
}