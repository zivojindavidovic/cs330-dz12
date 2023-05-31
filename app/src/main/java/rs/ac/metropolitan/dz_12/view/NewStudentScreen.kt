package rs.ac.metropolitan.dz_12.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.common.Common
import rs.ac.metropolitan.common.DatingItem
import rs.ac.metropolitan.dz_12.AppViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDatingScreen(vm: AppViewModel, paddingValues: PaddingValues){
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var country by remember { mutableStateOf(TextFieldValue("")) }
    var sex by remember { mutableStateOf(TextFieldValue("")) }
    var sexFor by remember { mutableStateOf(TextFieldValue("")) }
    var username by remember { mutableStateOf(TextFieldValue("")) }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(paddingValues)
        ){
            item{
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .scale(1.5f),
                        onClick = { vm.goBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "New Dating", style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            item{
                TextField(
                    value = username,
                    onValueChange = { newText ->
                        username = newText
                    },
                    label = { Text("Username") },
                    placeholder = { Text("Enter your username") },
                )
            }
            item{
                TextField(
                    value = city,
                    onValueChange = { newText ->
                        city = newText
                    },
                    label = { Text("City") },
                    placeholder = { Text("Enter your city") },
                )
            }
            item{
                TextField(
                    value = country,
                    onValueChange = { newText ->
                        country = newText
                    },
                    label = { Text("Country") },
                    placeholder = { Text("Enter your country") },
                )
            }
            item{
                TextField(
                    value = sex,
                    onValueChange = { newText ->
                        sex = newText
                    },
                    label = { Text("Sex") },
                    placeholder = { Text("Enter your sex") },
                )
            }
            item{
                TextField(
                    value = sexFor,
                    onValueChange = { newText ->
                        sexFor = newText
                    },
                    label = { Text("Interested in") },
                    placeholder = { Text("Enter sex you are interested in") },
                )
            }
            item {
                Button(onClick = { vm.addDating(
                    DatingItem(
                        id =  UUID.randomUUID().toString(),
                        city = city.text,
                        country = country.text,
                        avatar = Common.generateAvatarImage("${username.text}").toString(),
                        sex = sex.text,
                        sexFor = sexFor.text,
                        username = username.text
                    )
                )
                    vm.goBack()
                }) {
                    Text(text = "Submit")
                }
            }
        }
    }

}