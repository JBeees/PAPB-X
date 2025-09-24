# Penjelasan Kode
1. Counter Plus Minux  
```kotlin
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
```
Pada fungsi ini, variable count diatur menggunakan state remember. Kemudian terdapat dua tombol, tombol tambah yang ketika diklik akan menambah 1 nilai dari count kemudian tombol kurang akan mengurangi 1 nilai dari count dan hanya bisa mengurangi count selama count lebih dari 0. jika tidak maka nilai akan tetap 0. Setiap klik akan menyimpan perubahan state pada variable count.

2. Toggle Warna   
```kotlin
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
```
Pada fungsi ini terdapat variable isRed yang menerapkan state. Setiap kali box ditekan maka akan merubah warna dari Box menjadi merah atau hijau.

3. Profil Interaktif
```kotlin
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
```
Sama hal nya seperti soal sebelumnya, pada soal ini menggunakan variable boolean isFollowed.setiap kali tombol ditekan maka nilai varibel juga berubah dan akan disimpan dalam state yang kemudian akan berpengaruh pada isi dari teks yang ditampilkan.
