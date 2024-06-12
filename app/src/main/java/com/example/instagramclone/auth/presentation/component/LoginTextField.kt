package com.example.instagramclone.auth.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onIconPress: () -> Unit = {},
    label: String,
    isError: Boolean,
    icon: ImageVector? = null,
    iconDescription: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions,
    errorMessage: String = "",
) {
    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            isError = isError,
            trailingIcon = {
                if (icon != null) {
                    IconButton(onClick = onIconPress) {
                        Icon(
                            imageVector = icon,
                            contentDescription = iconDescription
                        )
                    }
                }
            },
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                errorContainerColor = MaterialTheme.colorScheme.background,
            ),
            shape = MaterialTheme.shapes.medium
        )
        ErrorText(
            text = errorMessage,
        )
    }
}