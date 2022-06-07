package com.example.coroutinesapp

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coroutinesapp.databinding.FragmentSecondBinding
import kotlin.random.Random


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private var count = 0

    private var secondsCounter = 1

    private var pauseOffset: Long = 0

    private var chronometerIsRun: Boolean = false

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        binding.chronometer.setOnChronometerTickListener {
            /*
            millis = SystemClock.elapsedRealtime() - binding.chronometer.base
            if ((SystemClock.elapsedRealtime() - binding.chronometer.base) / 10000 == 0L){
                binding.viewFragment.setBackgroundResource(R.color.purple_200)
            }
            millis++

             */
            val millis: Long = (SystemClock.elapsedRealtime() - binding.chronometer.base)
            if (millis / secondsCounter in 20000..20999) {
                secondsCounter++
                if (count == 0) {
                    count = 1
                    randomColor()
                } else {
                    count = 0
                    randomColor()
                }
            }
        }
        binding.startButton.setOnClickListener {
            viewModel.stopPI()
            if (viewModel.isRestart) {
                viewModel.count = 0
            }
            viewModel.running = true
            viewModel.startPI()
            if (!chronometerIsRun) {
                startChronometer()
                chronometerIsRun = true
            }
        }
        binding.stopButton.setOnClickListener {
            if (chronometerIsRun) {
                stopChronometer()
                chronometerIsRun = false
            }
            viewModel.stopPI()
            viewModel.isRestart = false
        }
        binding.restartButton.setOnClickListener {
            restartChronometer()
            viewModel.stopPI()
            viewModel.count = 0
            viewModel.isRestart = true
            viewModel.running = true
            viewModel.startPI()
        }
        return binding.root
    }

    private fun startChronometer() {
        binding.chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
        binding.chronometer.start()
    }

    private fun stopChronometer() {
        binding.chronometer.stop()
        pauseOffset = SystemClock.elapsedRealtime() - binding.chronometer.base
    }

    private fun restartChronometer() {
        binding.chronometer.base = SystemClock.elapsedRealtime()
        pauseOffset = 0
        startChronometer()
    }


    private fun randomColor() {
        val rnd = Random
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        binding.viewFragment.setBackgroundColor(color)
    }

}