package rs.ac.metropolitan.dz_12.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import rs.ac.metropolitan.common.DatingItem
import rs.ac.metropolitan.dz_12.AppViewModel

@Composable
fun DatingDetailScreen(vm: AppViewModel, elementId: String, paddingValues: PaddingValues){
    DatingBasicData(dating = vm.getDating(elementId), goBack = {vm.goBack()}, delete = {vm.deleteDating(elementId)})
}

@Composable
fun DatingBasicData(dating: DatingItem?, goBack: () -> Unit, delete: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ){
        Box(modifier = Modifier.fillMaxWidth()){
            IconButton(
                modifier = Modifier
                    .background(Color.Transparent)
                    .scale(1.5f),
                onClick = { goBack() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(text = "Student details", style = MaterialTheme.typography.titleLarge, modifier = Modifier.align(
                Alignment.Center))
            IconButton(
                modifier = Modifier
                    .scale(1.5f)
                    .align(Alignment.BottomEnd),
                onClick = { delete() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
        dating?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                AsyncImage(
                    model = it.avatar,
                    contentDescription = null,
                    modifier = Modifier
                        .size(240.dp)
                        .clip(CircleShape)
                )
                Text(text = "City: ${dating.city} Coutntry: ${dating.country}")
                Text(
                    text = "Email: ${dating.username}", color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Sex: ${dating.sex} Sex For: ${dating.sexFor}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}
