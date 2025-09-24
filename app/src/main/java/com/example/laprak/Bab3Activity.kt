package com.example.laprak

import android.net.ProxyInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.laprak.ui.theme.LaprakTheme
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class Bab3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            changeBox()
//                CounterApp()
                ProfileCard()
        }
    }
}

@Composable
fun CounterApp() {
    var count by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Jumlah Klik: $count")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .width(12.dp)
            )
            Button(
                onClick = { count++ }) {
                Text("Tambah(+)")
            }
            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .width(12.dp)
            )
            Button(
                onClick = { if (count > 0) count-- else 0 }) {
                Text("Kurang(-)")
            }
        }
    }
}

@Composable
fun FollowButton(
    isFollowed: Boolean, onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(if (isFollowed) "Unfollow" else "Follow")
    }
}

@Composable
fun FollowApp() {
    var isFollowed by remember { mutableStateOf(false) }
    FollowButton(
        isFollowed = isFollowed,
        onClick = { isFollowed = !isFollowed }
    )
}

@Composable
fun ProfileCard() {
    var isFollowed by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.download),
            contentDescription = "Foto Profil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Nama: Andi", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Mahasiswa Teknik Informatika")
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { isFollowed = !isFollowed }) {
            Text(if (isFollowed) "Unfollow" else "Follow")
        }
        Text(if(isFollowed)"Anda mengikuti akun ini" else "Anda belum mengikuti akun ini")
    }
}

@Composable
fun changeBox() {
    var isRed by remember { mutableStateOf(true) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(if (isRed) Color.Red else Color.Green)
                .clickable { isRed = !isRed }
        )
    }
}