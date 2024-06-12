package com.example.instagramclone.auth.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorText(
    modifier: Modifier = Modifier,
    text: String
){
    Text(
        text = text,
        textAlign = TextAlign.End,
        fontSize = MaterialTheme.typography.bodySmall.fontSize,
        color = MaterialTheme.colorScheme.error,
        modifier = modifier.fillMaxWidth()
    )
}