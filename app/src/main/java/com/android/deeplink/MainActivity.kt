package com.android.deeplink

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.deeplink.ui.theme.DeeplinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val action: String? = intent?.action
        val data: Uri? = intent?.data
        var splitPath: List<String>? = null
        
        data?.let {uri: Uri ->  
            splitPath = uri.toString().split("/")
        }

        setContent {
            DeeplinkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    splitPath?.let { pathList ->
                        pathList.last { lastItem ->
                            when(lastItem) {
                                "FirstPage" -> FirstPage(title = lastItem)
                                "SecondPage" -> SecondPage(title = lastItem)
                                else -> InvalidPage()
                            }
                            true
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FirstPage(title: String) {
    Box(modifier = Modifier.fillMaxSize(1f)) {
        Text(text = title)
    }
}

@Composable
fun SecondPage(title: String) {
    Box(modifier = Modifier.fillMaxSize(1f)) {
        Text(text = title)
    }
}

@Composable
fun InvalidPage() {
    Box(modifier = Modifier.fillMaxSize(1f)) {
        Text(text = "Invalid Path")
    }
}