package me.partypronl.quibble.pages.entry.picture

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import me.partypronl.quibble.R

@Composable
fun WritePictureEntryPage(
    innerPadding: PaddingValues,
    navController: NavController,
    date: Long
) {
    var caption by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageURI by remember { mutableStateOf("") }

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context.contentResolver.takePersistableUriPermission(uri, flag)
            imageURI = uri.toString()
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    Column(
        modifier = Modifier.padding(innerPadding).padding(16.dp).verticalScroll(rememberScrollState()).fillMaxSize()
    ) {
        Spacer(Modifier.height(8.dp))

        BasicTextField(
            value = caption,
            onValueChange = { caption = it },
            textStyle = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onSurface),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            ),
            decorationBox = { innerTextField ->
                if (caption.isEmpty()) {
                    Text(
                        text = "Add a caption...",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                innerTextField()
            }
        )

        Spacer(Modifier.height(16.dp))

        if(imageURI == "") {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1F)
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .clickable {
                        launcher.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_photo_camera_back_24),
                    contentDescription = "Camera",
                    modifier = Modifier.size(36.dp)
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Select picture",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "Click anywhere on this box"
                )
            }
        } else {
            val painter = rememberAsyncImagePainter(imageURI)
            Image(
                painter = painter,
                contentDescription = "Selected image",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .aspectRatio(1f)
                    .clickable {
                        launcher.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
                    }
            )
        }

        Spacer(Modifier.height(8.dp))

        BasicTextField(
            value = description,
            onValueChange = { description = it },
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            ),
            decorationBox = { innerTextField ->
                if (description.isEmpty()) {
                    Text(
                        text = "Write a description...",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                innerTextField()
            }
        )
    }
}