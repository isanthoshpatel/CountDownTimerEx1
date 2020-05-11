package com.example.countdowntimerex1

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var timer: CountDownTimer
    var initialTime: Long = 600000
    var leftTime: Long = initialTime

    var isTimerRunning: Boolean = false


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bt_start.setOnClickListener {

            if (!isTimerRunning) { //start

                timer = object : CountDownTimer(leftTime, 100) {

                    override fun onTick(millisUntilFinished: Long) {

                        leftTime = millisUntilFinished

                        var min = millisUntilFinished / 1000 / 60
                        var sec = millisUntilFinished / 1000 % 60

                        var string = String.format(Locale.getDefault(), "%02d:%02d", min, sec)
                        tv_countdowntime.text = string

                        bt_start.text = "Pause"
                        isTimerRunning = true

                    }

                    override fun onFinish() {


                        bt_start.text = "Start"
                        isTimerRunning = false

                    }
                }.start()

            }
            if (isTimerRunning) {//pause
                timer.cancel()

                bt_start.text = "start"
                isTimerRunning = false
            }
        }

        bt_reset.setOnClickListener {
            timer.cancel()
            leftTime = initialTime
            tv_countdowntime.text = (initialTime / 1000 / 60).toString() + ":" + "00"

            bt_start.text = "start"
            isTimerRunning = false
        }
    }
}
