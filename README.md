# Penjelasan Kode
Pada bagian setContent
```kotlin
setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.download),
                    contentDescription = "Foto Profil",
                    modifier = Modifier.size(120.dp).clip(CircleShape)
                )
                Text("Nama: Muhammad Arif Rifki", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("NIM:23515020111066")
                Text("Mahasiswa Teknik Informatika")
                Spacer(modifier = Modifier.height(8.dp))
                FollowButton()
            }
        }
```
Pada bagian column `fillMaxSize` digunakan agar column memiliki size yang sama dengan layar device. kemudian `horizontalAlignment` dan `vertical Alignment` digunakan untuk memposisikan profil ditengah layar. Kemudian `Image` memiliki size sebesar `120 dp` dan memiliki border circle. Kemudian juga ada 3 text, `Spacer` yang berfungsi untuk memberikan jarak sebesar 8dp. Kemudian pemanggilan fungsi `FollowButton()`
```kotlin
@Composable
fun FollowButton() {
    var isFollowed by remember { mutableStateOf(false) }
    Button(onClick = { isFollowed = !`isFollowed` }) {
        Text(if (isFollowed) "Unfollow" else "Follow")
    }
}
```
Fungsi ini berisi var `isFollowed` menggunakan **remember mutableStateOf** untuk menyimpan state dari nilai isFollowed untuk memastikan perubahan pada tombol ketika di klik. State ini dapat berupa `Follow` atau `Unfollow`.
