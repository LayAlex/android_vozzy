package com.example.vozzy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    val commandsMap = mapOf(
        "К навес" to 322,
        "К2 улица" to 323,
        "К3 мастерская" to 324,
        "К4 огород" to 325,
        "К5 мото" to 326,
        "К6 гараж" to 327,
        "Свет навес" to 328,
        "Свет улица" to 329,
        "Свет мастерская" to 330,
        "Свет огород" to 331,
        "Свет мото" to 332,
        "Свет гараж" to 333,
        "Откл свет везде" to 334,
        "Вкл свет везде" to 386,
        "Alarm светом" to 388,
        "Сирена alarm 10 сек" to 300,
        "Сирена 3 коротких вызова" to 301,
        "Прожектор Вкл" to 257,
        "Прожектор Откл" to 258,
        "Гостин Вкл" to 304,
        "Гостин Откл" to 305,
        "Переключать со светом" to 537,
        "Переключать без света" to 538,
        "Вкл охрану Воз" to 579,
        "Снять с охраны Воз" to 580,
        "Вкл охрану Оф" to 577,
        "Снять с охраны Оф" to 578
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cam_naves = findViewById<Button>(R.id.btn_cam_naves);
        cam_naves.setOnClickListener{
            makeListener(cam_naves.text.toString());
        }

        val cam_ulica = findViewById<Button>(R.id.btn_cam_ulica);
        cam_ulica.setOnClickListener{
            makeListener(cam_ulica.text.toString());
        }

        val cam_master = findViewById<Button>(R.id.btn_cam_master);
        cam_master.setOnClickListener{
            makeListener(cam_master.text.toString());
        }

        val cam_ogorod = findViewById<Button>(R.id.btn_cam_ogorod);
        cam_ogorod.setOnClickListener{
            makeListener(cam_ogorod.text.toString());
        }

        val cam_moto = findViewById<Button>(R.id.btn_cam_moto);
        cam_moto.setOnClickListener{
            makeListener(cam_moto.text.toString());
        }

        val cam_garage = findViewById<Button>(R.id.btn_cam_garage);
        cam_garage.setOnClickListener{
            makeListener(cam_garage.text.toString());
        }


        val light_naves = findViewById<Button>(R.id.btn_light_naves);
        light_naves.setOnClickListener{
            makeListener(light_naves.text.toString());
        }

        val light_ulica = findViewById<Button>(R.id.btn_light_ulica);
        light_ulica.setOnClickListener{
            makeListener(light_ulica.text.toString());
        }

        val light_master = findViewById<Button>(R.id.btn_light_master);
        light_master.setOnClickListener{
            makeListener(light_master.text.toString());
        }

        val light_ogorod = findViewById<Button>(R.id.btn_light_ogorod);
        light_ogorod.setOnClickListener{
            makeListener(light_ogorod.text.toString());
        }

        val light_moto= findViewById<Button>(R.id.btn_light_moto);
        light_moto.setOnClickListener{
            makeListener(light_moto.text.toString());
        }

        val light_garage = findViewById<Button>(R.id.btn_light_garage);
        light_garage.setOnClickListener{
            makeListener(light_garage.text.toString());
        }

        val lights_on = findViewById<Button>(R.id.btn_lights_on);
        lights_on.setOnClickListener{
            makeListener(lights_on.text.toString());
        }

        val lights_off = findViewById<Button>(R.id.btn_lights_off);
        lights_off.setOnClickListener{
            makeListener(lights_off.text.toString());
        }

        val alarmbtn = findViewById<Button>(R.id.btn_alarm);
        alarmbtn.setOnClickListener{
            makeListener(alarmbtn.text.toString());
        }

        val sirena_call = findViewById<Button>(R.id.btn_sirena_call);
        sirena_call.setOnClickListener{
            makeListener(sirena_call.text.toString());
        }

        val light_gostin_on = findViewById<Button>(R.id.btn_light_gostinaya_on);
        light_gostin_on.setOnClickListener{
            makeListener(light_gostin_on.text.toString());
        }

        val light_gostin_off = findViewById<Button>(R.id.btn_light_gostinaya_off);
        light_gostin_off.setOnClickListener{
            makeListener(light_gostin_off.text.toString());
        }

        val light_koridor_on = findViewById<Button>(R.id.btn_light_koridor_on);
        light_koridor_on.setOnClickListener {
            makeListener(light_koridor_on.text.toString());
        }

        val light_koridor_off = findViewById<Button>(R.id.btn_light_koridor_off);
        light_koridor_off.setOnClickListener{
                makeListener(light_koridor_off.text.toString());
        }

        val office_alarm_on = findViewById<Button>(R.id.btn_office_alarm_on);
        office_alarm_on.setOnClickListener{
            makeListener(office_alarm_on.text.toString());
        }

        val office_alarm_off = findViewById<Button>(R.id.btn_office_alarm_off);
        office_alarm_off.setOnClickListener{
            makeListener(office_alarm_off.text.toString());
        }

        var voz_alarm_off= findViewById<Button>(R.id.btn_voz_alarm_off);
        voz_alarm_off.setOnClickListener{
            makeListener(voz_alarm_off.text.toString());
        }

        var voz_alarm_on= findViewById<Button>(R.id.btn_voz_alarm_on);
        voz_alarm_on.setOnClickListener{
               makeListener(voz_alarm_on.text.toString());
        }

        val mpxr_cams_perek_light = findViewById<Button>(R.id.btn_perek_light);
        mpxr_cams_perek_light.setOnClickListener{
            makeListener(mpxr_cams_perek_light.text.toString());
        }

        val mpxr_cams_perek_no_light = findViewById<Button>(R.id.btn_perek_no_light);
        mpxr_cams_perek_no_light.setOnClickListener{
            makeListener(mpxr_cams_perek_no_light.text.toString());
        }

    }


    private fun makeListener(cmdLabel : String) {
        val commandId = commandsMap[cmdLabel]
        if(commandId!=null){
            sendRunCommandPostRequest(commandId);
        }else
        {
            runOnUiThread {
                Toast.makeText(applicationContext, "не найден cmdId, команда не отправлена", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendRunCommandPostRequest(cmdId : Int) {
        val url = "https://www.vozzy.ru/api/AnyDevicesApi/RunCommand"
        val json = "{\"CommandId\":$cmdId,\"CommandTypeId\":0, \"Token\":\"48640a59-2692-44dd-b776-f49b00284074\"}"

        val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                // Обработка успешного ответа сервера
                val responseBody = response.body?.string()

                var msg = "";

                if(responseBody == "\"True\"")
                {
                    msg = "Команда успешно добавлена в очередь";
                }
                else
                {
                    msg = responseBody?.toString()?:"Пустой ответ от сервера"
                }

                runOnUiThread {
                    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                }


                // Добавьте свою логику обработки ответа
            }

            override fun onFailure(call: Call, e: IOException) {
                // Обработка ошибки при выполнении запроса
                e.printStackTrace()

                runOnUiThread {
                    Toast.makeText(applicationContext, "Ошибка отправки команды", Toast.LENGTH_SHORT).show()
                }
                // Добавьте свою логику обработки ошибки
            }
        })
    }
}