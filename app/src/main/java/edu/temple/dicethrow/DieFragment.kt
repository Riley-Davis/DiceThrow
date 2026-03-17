package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    private val DIESIDE = "sidenumber"
    private val ROLL_KEY = "current_roll"

    lateinit var dieTextView: TextView

    var currentRoll = 1

    var dieSides: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
        savedInstanceState?.run {
            currentRoll = getInt(ROLL_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (currentRoll == 0){
            throwDie()
        }
        else {
            dieTextView.text = currentRoll.toString()
        }

//        view.setOnClickListener{
//            throwDie()
//        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ROLL_KEY, currentRoll)
    }

    fun throwDie() {
        currentRoll = Random.nextInt(1,dieSides + 1)
        dieTextView.text = currentRoll.toString()
    }

    companion object {
        fun newInstance (sides: Int) = DieFragment().apply {
            arguments = Bundle().apply{
                putInt(DIESIDE, sides)
            }
        }
    }
}