package com.example.sjtu_network

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sjtu_network.api.YouDaoBean
import com.example.sjtu_network.interceptor.TimeConsumeInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var requestBtn: Button? = null
    var showText: TextView? = null
    var editText: EditText? = null

    val okhttpListener = object : EventListener() {
        override fun dnsStart(call: Call, domainName: String) {
            super.dnsStart(call, domainName)
            showText?.text = showText?.text.toString() + "\nDns Search:" + domainName
        }

        override fun responseBodyStart(call: Call) {
            super.responseBodyStart(call)
            showText?.text = showText?.text.toString() + "\n正在查询"
        }
    }
    val client: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(TimeConsumeInterceptor())
        .eventListener(okhttpListener).build()

    val gson = GsonBuilder().create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestBtn = findViewById(R.id.send_request)
        showText = findViewById(R.id.show_text)
        editText = findViewById(R.id.input)

        requestBtn?.setOnClickListener {
            showText?.text = ""
            click()
        }
    }

    fun request(url: String, callback: Callback) {
        val request: Request = Request.Builder()
            .url(url)
            .header("User-Agent", "Sjtu-Android-OKHttp")
            .build()
        client.newCall(request).enqueue(callback)
    }

    fun click() {
        val input = editText!!.text.toString()
        val url = "http://fanyi.youdao.com/translate?&doctype=json&type=AUTO&i=$input"
        request(url, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                showText?.text = e.message
            }

            override fun onResponse(call: Call, response: Response) {
                val bodyString = response.body?.string()

                //TEST CODE : 测试返回结果用于生成解析文件
                //showText?.text = bodyString
                //apple：{"type":"EN2ZH_CN","errorCode":0,"elapsedTime":1,"translateResult":[[{"src":"apple","tgt":"苹果"}]]}


                val YouDaoBean = gson.fromJson(bodyString, YouDaoBean::class.java)


                showText?.text = "${showText?.text.toString()} \n\n\n" +
                        "English: $input \n" +
                        "Chinese: ${YouDaoBean.translateResult[0][0].tgt} \n"
            }
        })
    }
}