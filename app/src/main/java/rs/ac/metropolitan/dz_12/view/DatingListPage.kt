package rs.ac.metropolitan.dz_12.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import rs.ac.metropolitan.common.DatingItem
import rs.ac.metropolitan.dz_12.AppViewModel

@Composable
fun DatingListPage(vm: AppViewModel, paddingValues: PaddingValues){
    val datings = vm.datings.observeAsState()
    LaunchedEffect(vm.loadDatings()) {
    }

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        datings.value?.let {
            items(it){dating->
                DatingCardView(dating = dating){
                    vm.navToDatingDetails(it)
                }
            }
        }
    }
}

@Composable
fun DatingCardView(dating: DatingItem, onSelected: (String) -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSelected(dating.id) }
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ){
            AsyncImage(
                model = dating.avatar,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .width(250.dp),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = "City: ${dating.city} Coutntry: ${dating.country}",
                    fontSize = 18.sp
                )
                Text(
                    text = "Sex: ${dating.sex} Sex For: ${dating.sexFor}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${dating.username}", modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}