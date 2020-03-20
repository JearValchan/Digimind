package armando.valencia.itson.digimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import armando.valencia.itson.digimind.R
import armando.valencia.itson.digimind.Task
import armando.valencia.itson.digimind.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        //val textView: TextView = root.findViewById(R.id.text_dashboard)
        root.btn_time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.setText(SimpleDateFormat("HH:.mm").format(cal.time))
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true).show()
        }

        root.btn_save.setOnClickListener {
            var title = taskTitle.text.toString()
            var time = btn_time.text.toString()
            var days = ArrayList<String>()

            if (monday.isChecked)
                days.add("Monday")
            if (tuesday.isChecked)
                days.add("Tuesday")
            if (wednesday.isChecked)
                days.add("Wednesday")
            if (thursday.isChecked)
                days.add("Thursday")
            if (friday.isChecked)
                days.add("Friday")
            if (saturday.isChecked)
                days.add("Saturday")
            if (sunday.isChecked)
                days.add("Sunday")

            var task = Task(title, days, time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context, "New task added", Toast.LENGTH_SHORT)
        }

        return root
    }
}