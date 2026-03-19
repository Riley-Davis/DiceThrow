package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class DieFragment : Fragment() {

    private val DIESIDE = "sidenumber"
    private val ROLL_KEY = "current_roll"

    lateinit var dieTextView: TextView
    private val dieViewModel: DieViewModel by lazy {
        ViewModelProvider(this)[DieViewModel::class.java]  // 'this' instead of requireActivity()
    }

//    var currentRoll = 1

//    var dieSides: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieViewModel.setDieSides(this)
            }
        }
        savedInstanceState?.run {
            dieViewModel.setCurrentRoll(getInt(ROLL_KEY))
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
        if (dieViewModel.getCurrentRoll().value == 0){
            dieViewModel.throwDie()
        }
        else {
            dieTextView.text = dieViewModel.getCurrentRoll().value.toString()
        }

        dieViewModel.getCurrentRoll().observe(viewLifecycleOwner){
            dieTextView.text = it.toString()
        }

//        view.setOnClickListener{
//            throwDie()
//        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ROLL_KEY, dieViewModel.getCurrentRoll().value?:0)
    }

    fun throwDie() {
        dieViewModel.throwDie()
        dieTextView.text = dieViewModel.getCurrentRoll().value.toString()  // .value here
    }

    companion object {
        fun newInstance (sides: Int) = DieFragment().apply {
            arguments = Bundle().apply{
                putInt(DIESIDE, sides)
            }
        }
    }
}