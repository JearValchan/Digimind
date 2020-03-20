package armando.valencia.itson.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import armando.valencia.itson.digimind.R
import armando.valencia.itson.digimind.Task
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_board.view.*

class HomeFragment : Fragment() {

    private var adaptador: AdaptadorTareas? = null

    companion object {
        var tasks = ArrayList<Task>()
        var first = true
    }

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if (first){
            fillTasks()
            first = false
        }

        adaptador = AdaptadorTareas(root.context, tasks)
        root.gridview.adapter = adaptador

        return root
    }

    fun fillTasks() {
        tasks.add(Task("Practice 1", arrayListOf("Tuesday"), "17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Monday, Sunday"), "17:30"))
        tasks.add(Task("Practice 3", arrayListOf("Wednesday"), "14:00"))
        tasks.add(Task("Practice 4", arrayListOf("Saturday"), "11:00"))
        tasks.add(Task("Practice 5", arrayListOf("Friday"), "13:00"))
        tasks.add(Task("Practice 6", arrayListOf("Thursday"), "10:40"))
        tasks.add(Task("Practice 7", arrayListOf("Monday"), "12:00"))
    }
}

private class AdaptadorTareas : BaseAdapter {
    var tasks = ArrayList<Task>()
    var contexto: Context? = null

    constructor(contexto: Context, tasks: ArrayList<Task>) {
        this.contexto = contexto
        this.tasks = tasks
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var task = tasks[p0]
        var inflador = LayoutInflater.from(contexto)
        var vista = inflador.inflate(R.layout.item_board, null)

        vista.cellTitle.text = task.title
        vista.cellDays.text = task.days.toString()
        vista.cellTime.text = task.time

        return vista
    }

    override fun getItem(p0: Int): Any {
        return tasks[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return tasks.size
    }

}