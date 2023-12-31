package ru.wwerlosh.footprint.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import ru.wwerlosh.footprint.R
import ru.wwerlosh.footprint.util.GlobalData

class FourthBlockFragment : Fragment(){
    private var backButtonPressCount = 0
    private val requiredBackButtonPresses = 2

    var selectedCarType: String? = null
    var day: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fourthblock, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layout2 = layoutInflater.inflate(R.layout.toast_exit, requireView().findViewById(R.id.toast_exit_root))
        val toastExit = Toast(requireContext())
        toastExit.duration = Toast.LENGTH_SHORT
        toastExit.view = layout2
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val timer = object: CountDownTimer(5000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        backButtonPressCount = 0
                    }
                }
                timer.start()
                if (backButtonPressCount < requiredBackButtonPresses - 1) {
                    backButtonPressCount++
                    toastExit.show()
                } else {
                    isEnabled = false
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

        val layout = layoutInflater.inflate(R.layout.toast_layout, requireView().findViewById(R.id.toast_root))
        val toast = Toast(requireContext())
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout

        val publicCarCheckBox = view.findViewById<CheckBox>(R.id.publicCarCheckBox)
        val inputMileage = view.findViewById<EditText>(R.id.inputPublicCarMileage)
        val inputMilTextView = view.findViewById<TextView>(R.id.inputMilTextView2)
        val inputUsageDaysTextView = view.findViewById<TextView>(R.id.inputUsageDaysTextView2)
        val secLay = view.findViewById<LinearLayout>(R.id.secLay)

        super.onViewCreated(view, savedInstanceState)
        val carTypeSpinner: Spinner = view.findViewById(R.id.publicCarTypeSpinner)
        val daysSpinner: Spinner = view.findViewById(R.id.daysSpinner)
        carTypeSpinner.setBackgroundResource(R.drawable.spinner_border)
        daysSpinner.setBackgroundResource(R.drawable.spinner_border)

        val days = arrayOf("Выберите количество дней","1","2","3","4","5","6","7")

        val carTypes = arrayOf(
            "Выберите тип транспорта",
            "Такси",
            "Автобус",
            "Метро"
        )
        val carTypeSpinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_layout, carTypes)
        val daysTypeSpinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_layout, days)

        carTypeSpinner.adapter = carTypeSpinnerAdapter
        daysSpinner.adapter = daysTypeSpinnerAdapter

        carTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCarType = carTypes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedCarType = "Выберите тип транспорта"
            }
        }

        daysSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                day = days[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                day = "Выберите количество дней"
            }

        }

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        val fadeOutAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out)

        publicCarCheckBox.setOnClickListener {
            if (publicCarCheckBox.isChecked) {
                carTypeSpinner.startAnimation(fadeOutAnimation)
                secLay.startAnimation(fadeOutAnimation)
                carTypeSpinner.visibility = View.GONE
                secLay.visibility = View.GONE
            } else {
                carTypeSpinner.visibility = View.VISIBLE
                secLay.visibility = View.VISIBLE
                carTypeSpinner.startAnimation(fadeInAnimation)
                secLay.startAnimation(fadeInAnimation)
            }
        }

        val fourthBlockButton = view.findViewById<Button>(R.id.fourthBlockButton)
        fourthBlockButton.setOnClickListener {
            val mileage = inputMileage.text.toString()
            val usageDays = day

            if (publicCarCheckBox.isChecked) {
                val fifthBlockFragment = FifthBlockFragment()
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                fragmentTransaction.replace(R.id.fragmentContainer, fifthBlockFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
                return@setOnClickListener
            }

            if (selectedCarType == "Выберите тип транспорта") {
                toast.show()
                return@setOnClickListener
            }

            // Проверка на пустое поле
            if (mileage.isEmpty()) {
                val text = "*   Какое расстояние (в среднем) вы проезжаете за день? (в км)"
                val spannable = SpannableString(text)

                val colorSpan1 = ForegroundColorSpan(Color.rgb(199, 54, 54))
                val colorSpan2 = ForegroundColorSpan(Color.WHITE)

                spannable.setSpan(colorSpan1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannable.setSpan(colorSpan2, 2, 62, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                inputMilTextView.text = spannable
                toast.show()
                return@setOnClickListener
            }

            if (day == "Выберите количество дней") {
                val text = "*   Сколько дней в неделю в используете личный транспорт?"
                val spannable = SpannableString(text)

                val colorSpan1 = ForegroundColorSpan(Color.rgb(199, 54, 54))
                val colorSpan2 = ForegroundColorSpan(Color.WHITE)

                spannable.setSpan(colorSpan1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannable.setSpan(colorSpan2, 2, 56, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                inputUsageDaysTextView.text = spannable
                toast.show()
                return@setOnClickListener
            }
            val transportEmission: Double
            val emissionCoefficient: Double = if (selectedCarType == "Такси")
                0.20369
            else if (selectedCarType == "Метро") 0.03694 else 0.1195
            transportEmission = (emissionCoefficient *
                    inputMileage.text.toString().toDouble() *
                    usageDays.toString().toDouble())

            GlobalData.total += transportEmission

            val fifthBlockFragment = FifthBlockFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
            fragmentTransaction.replace(R.id.fragmentContainer, fifthBlockFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}