package com.example.ca_prep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ca_prep.ui.theme.CA_PrepTheme

class CA3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CA_PrepTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FitnessScreen()
                }
            }
        }
    }
}

data class UiDisplay(
    val workoutSession: String,
    val currentExercise: String,
    val reps: Int
)

data class BusinessLogic(
    val currentStatus: String
)

class FitnessViewModel : ViewModel() {

    private val initialUi = UiDisplay(
        workoutSession = "Morning Workout",
        currentExercise = "Push-ups",
        reps = 0
    )

    private val initialBusiness = BusinessLogic(
        currentStatus = "Not Started"
    )

    var currentSession by mutableStateOf(initialUi.workoutSession)
        private set

    var currentExercise by mutableStateOf(initialUi.currentExercise)
        private set

    var reps by mutableIntStateOf(initialUi.reps)
        private set

    var currentStatus by mutableStateOf(initialBusiness.currentStatus)
        private set

    fun startWorkout() {
        currentStatus = "In Progress"
    }

    fun pauseWorkout() {
        currentStatus = "Paused"
    }

    fun resumeWorkout() {
        currentStatus = "In Progress"
    }

    fun completeWorkout() {
        currentStatus = "Completed"
    }
}

@Composable
fun FitnessScreen(
    viewModel: FitnessViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Fitness Tracker",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Session: ${viewModel.currentSession}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Exercise: ${viewModel.currentExercise}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Completed Reps: ${viewModel.reps}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Status: ${viewModel.currentStatus}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.startWorkout() }
            ) {
                Text("Start Workout")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.pauseWorkout() }
                ) {
                    Text("Pause")
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.resumeWorkout() }
                ) {
                    Text("Resume")
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.completeWorkout() }
            ) {
                Text("Complete Workout")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FitnessScreenPreview() {
    CA_PrepTheme {
        FitnessScreen()
    }
}
