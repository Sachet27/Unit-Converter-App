package com.example.unitconverter

import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Start
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier= Modifier
                        .fillMaxSize()
                        .padding(top = 35.dp),
                    color= MaterialTheme.colorScheme.background
                ){
                    UnitConverter()
                }
            }
        }
    }
}



@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpand by remember{ mutableStateOf(false) }
    var oExpand by remember{ mutableStateOf(false) }
    var iConversionFactor= remember{ mutableStateOf(1.0) }
    var oConversionFactor= remember{ mutableStateOf(1.0) }


    fun ConvertUnit(){
        val input= inputValue.toDoubleOrNull() ?: 0.0
        val result= ((input* iConversionFactor.value*100.0/ oConversionFactor.value ).roundToInt())/100.0
        outputValue= result.toString()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //displays text on same column (multiple lines)
        Text(
            text="Unit Converter",
            style= MaterialTheme.typography.headlineLarge
        )
        Spacer(
            modifier= Modifier
                .height(16.dp)

        )
        OutlinedTextField(
            value=inputValue, //Text displayed initially
            onValueChange = {
                inputValue=it
            }, //Logic for change during text entering
            label={Text("Enter value:")}
        )
        Spacer(
            modifier= Modifier
                .height(16.dp)
                .width(25.dp)
        )
        Row {
            //allows stacking of button and dropdown menu
           Box {
               //input button
               Button(
                   onClick={
                       iExpand=!iExpand
                   }
               ) {
                   Text("${inputUnit}")
                   Icon(
                       imageVector = Icons.Default.ArrowDropDown,
                       contentDescription = "Down arrow"
                   )
               }
               DropdownMenu(
                   expanded = iExpand,
                   onDismissRequest= { iExpand=false}
               ) {
                   DropdownMenuItem(
                       text= {Text(" Centimeters")},
                       onClick= {
                           inputUnit= "Centimeters"
                           iExpand=false
                           iConversionFactor.value= 0.01
                       }
                   )
                   DropdownMenuItem(
                       text={Text("Meters")},
                       onClick={
                           inputUnit= "Meters"
                           iExpand=false
                           iConversionFactor.value= 1.0
                       }
                   )
                   DropdownMenuItem(
                       text={Text("Kilometers")},
                       onClick={
                           inputUnit= "Kilometers"
                           iExpand=false
                           iConversionFactor.value= 1000.0
                       }
                   )
                   DropdownMenuItem(
                       text={Text("Inches")},
                       onClick={
                           inputUnit= "Inches"
                           iExpand=false
                           iConversionFactor.value= 0.0254
                       }
                   )
               }
           }
            Spacer(
                modifier= Modifier
                    .width(16.dp)
            )

            //allows stacking of button and dropdown menu
            Box {
                //output button
                Button(
                    onClick = {
                        oExpand=!oExpand
                    }
                ) {
                    Text("${outputUnit}")
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription= "Down arrow"
                    )
                }
                DropdownMenu(
                    expanded = oExpand,
                    onDismissRequest= {oExpand=false}
                ) {
                    DropdownMenuItem(
                        text= {Text(" Centimeters")},
                        onClick= {
                            outputUnit= "Centimeters"
                            iExpand=false
                            oConversionFactor.value= 0.01
                        }
                    )
                    DropdownMenuItem(
                        text={Text("Meters") },
                        onClick={
                            outputUnit= "Meters"
                            iExpand=false
                            oConversionFactor.value= 1.0
                        }
                    )
                    DropdownMenuItem(
                        text={Text("Kilometers")},
                        onClick={
                            outputUnit= "Kilometers"
                            iExpand=false
                            oConversionFactor.value= 1000.0

                        }
                    )
                    DropdownMenuItem(
                        text={Text("Inches")},
                        onClick={
                            outputUnit= "Inches"
                            iExpand=false
                            oConversionFactor.value= 0.0254
                        }
                    )
                }
            }


        }
        Button(
            onClick={ConvertUnit()}
        ) {
            Text("Convert")
        }
        //Result
        Text(
            text="Result: ${outputValue} ${outputUnit}",
            style = TextStyle(
                color= Color.Blue,
                fontSize = 24.sp,
                fontFamily= FontFamily.SansSerif
                ),
            modifier=Modifier.padding(top=15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}

