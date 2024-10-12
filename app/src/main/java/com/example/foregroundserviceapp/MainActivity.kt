package com.example.foregroundserviceapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.foregroundserviceapp.ui.theme.ForegroundServiceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //notification permision
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS
                ),
                111
            )
        }

        setContent {
            ForegroundServiceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                   Column(
                       modifier = Modifier.fillMaxSize()
                           .padding(innerPadding),
                       horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.Center
                   ) {
                       Button(
                           onClick = { startService(this@MainActivity)}
                       ) {
                           Text("Start Service")
                       }
                   }

                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        stopService(this)
    }

    override fun onPause() {
        super.onPause()
        startService(this)
    }


}


fun startService(context: Context){
    Intent(context,RunningService::class.java).also {
        it.action = RunningService.Actions.START.toString()
        context.startService(it)
    }
}

fun stopService(context: Context){
    Intent(context,RunningService::class.java).also {
        it.action = RunningService.Actions.STOP.toString()
        context.stopService(it)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ForegroundServiceAppTheme {

    }
}