package com.example.baitapnangcao2_tuan6.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.baitapnangcao2_tuan6.Coin

@Composable
fun CryptoScreen(coins: List<Coin>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E111A))
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // 🔶 BREAKING NEWS (to và nổi bật hơn)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFFFC107))
                .padding(vertical = 22.dp, horizontal = 20.dp)
        ) {
            Column {
                Text(
                    text = "Breaking News",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF111111)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Bitcoin hits new high as investors rush in!",
                    fontSize = 16.sp,
                    color = Color(0xFF222222)
                )
            }
        }

        // 🟣 HAI Ô VUÔNG GIỮA (to hơn và cách xa nhau)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.1f) // vuông to hơn
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF1C1F2A))
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Top Gainers",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "+5.23%",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF4CAF50)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1.1f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF1C1F2A))
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Top Losers",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "-3.14%",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFF44336)
                    )
                }
            }
        }

        // ⚪ Tiêu đề danh sách
        Text(
            text = "All Fluctuations",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        // 💰 Danh sách coin
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(bottom = 60.dp)
        ) {
            items(coins) { c ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1F2A))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = c.image,
                            contentDescription = c.name,
                            modifier = Modifier
                                .size(54.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = c.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                            Text(
                                text = c.symbol.uppercase(),
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = String.format("%.2f$", c.current_price),
                                fontSize = 17.sp,
                                color = Color.White
                            )
                            Text(
                                text = "${String.format("%.2f", c.price_change_percentage_24h)}%",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (c.price_change_percentage_24h >= 0)
                                    Color(0xFF4CAF50)
                                else
                                    Color(0xFFF44336)
                            )
                        }
                    }
                }
            }
        }
    }
}
