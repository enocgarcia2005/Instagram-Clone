package com.example.instagramclone.auth.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        enabled = enabled,
        contentPadding = PaddingValues(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.75f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f)
        ),
        modifier = modifier
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )
    }
}