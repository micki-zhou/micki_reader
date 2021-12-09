package micki.zhou.micki_reader

import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import micki.zhou.micki_reader.ui.theme.Micki_readerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Micki_readerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CanvasTest()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CanvasTest() {
    androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
//        drawCircle(
//            color = Color.Red,
//            radius = 200f,
//            center = Offset(23f, 40f)
//        )
//        drawLine(
//            start = Offset(0f, 0f),
//            end = Offset(width, height),
//            color = Color.Blue,
//            strokeWidth = 10f
//        )


        drawRect(color = Color(0xffeee8aa), size = Size(width = width, height = height))

        drawIntoCanvas {
            val text = "我不知道啊，真的不知道啊，换换，怎么办啊咯咯烦死了" +
                    "可以啊，不错啊，兄弟，啦啦啦，简体平庸，周五了，好牛逼"
            val textPaint = Paint().asFrameworkPaint().apply {
                color = android.graphics.Color.BLACK
                textSize = 60f
                isAntiAlias = true
                isDither = true
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
//                textAlign = android.graphics.Paint.Align.CENTER
            }

            var leftRightOffset = 10f
            var topOffset = 10f

            val textCharArr = text.toCharArray()
            val fontMetrics = textPaint.fontMetrics
            val a = fontMetrics.ascent
            val d = fontMetrics.descent
            var curX = leftRightOffset
            var curY = d - a + topOffset
            textCharArr.forEach { char ->
                val textMeasure = textPaint.measureText(char.toString())
                val paint = it.nativeCanvas
                paint.drawText(char.toString(), curX, curY, textPaint)
                curX += textMeasure
                if (curX + textMeasure > width) {
                    curX = 0f
                    curY += textMeasure + topOffset
                }
            }
        }

    }
}
