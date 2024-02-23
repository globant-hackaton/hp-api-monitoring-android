package com.globant.services

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.globant.data.repository.Service
import com.globant.data.repository.ServiceStatus
import com.globant.designsystem.components.BottomSheet

@Composable
fun ServicesScreen(
    servicesViewModel: ServicesViewModel = hiltViewModel()
) {
    val uiState by servicesViewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        ServiceItem(service = uiState.characters?.service)
        ServiceItem(service = uiState.characterById?.service)
        ServiceItem(service = uiState.students?.service)
        ServiceItem(service = uiState.studentsByHouse?.service)
        ServiceItem(service = uiState.staff?.service)
        ServiceItem(service = uiState.spells?.service)
    }
}

@Composable
private fun ServiceItem(
    service: Service? = null,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    if (showBottomSheet) {
        BottomSheet(onDismissRequest = { showBottomSheet = false })
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp)
            .clickable {
                if (service?.status == ServiceStatus.ERROR) {
                    showBottomSheet = true
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = service?.name ?: "Error",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text("Status code: ${service?.code}")
            }

            Text(
                text = if (service?.status == ServiceStatus.SUCCESS)
                    stringResource(R.string.active_status) else stringResource(R.string.inactive_status),
                fontWeight = FontWeight.Bold,
                color = if (service?.status == ServiceStatus.SUCCESS)
                    Color.Green else MaterialTheme.colorScheme.error
            )
        }
    }
}


/*
@Composable
@Preview(showBackground = true)
fun ServiceScreenPreview() {
    HarryPotterTheme {
        ServiceItem(Service(200, ServiceStatus.SUCCESS, "Character"))
        ServiceItem(Service(400, ServiceStatus.ERROR, "By House"))
    }
}*/
