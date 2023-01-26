package com.debduttapanda.floatlexer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.debduttapanda.floatlexer.scanners.FloatScanner
import com.debduttapanda.floatlexer.ui.theme.FloatLexerTheme
import java.util.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FloatLexerTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        var input by remember {
                            mutableStateOf("")
                        }
                        TextField(
                            value = input,
                            onValueChange ={
                                input = it
                            },
                            maxLines = 10,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Button(
                            onClick = {
                                input = ""
                            }
                        ) {
                            Text("Clear")
                        }
                        Text(
                            FloatScanner.scan(input).toString()
                        )
                    }
                }
            }
        }
    }
}