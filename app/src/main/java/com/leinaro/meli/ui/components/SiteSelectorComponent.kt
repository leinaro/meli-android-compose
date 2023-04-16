package com.leinaro.meli.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leinaro.meli.domain.entities.Site
import com.leinaro.meli.ui.SiteSelectorViewModel

@Composable
fun SiteSelectorComponent(
    viewModel: SiteSelectorViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    SiteSelectorView(
        sites = uiState.sites,
        onSiteClick = viewModel::onSiteSelected
    )
}

@Composable
fun SiteSelectorView(
    sites: List<Site>,
    onSiteClick: (Site) -> Unit = {},
) {
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        item {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = "Selecciona un país",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
            )
        }
        item {
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()  //fill the max height
                    .width(1.dp)
            )
        }
        items(sites) { site ->
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSiteClick(site)
                        }
                        .padding(8.dp),
                    text = site.name,
                    textAlign = TextAlign.Center,
                )
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()  //fill the max height
                        .width(1.dp)
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SiteSelectorViewPreview() {
    SiteSelectorView(
        listOf(
            Site("", "Argentina"),
            Site("", "Mexico"),
            Site("", "Colombia"),
        )
    )
}