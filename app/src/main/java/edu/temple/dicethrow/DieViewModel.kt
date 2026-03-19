package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DieViewModel: ViewModel() {
    private val currentRoll = MutableLiveData<Int>().apply{value=0}
    private val dieSides = MutableLiveData<Int>()

    fun throwDie(){
        currentRoll.value =Random.nextInt(1,(dieSides.value?:6) + 1)
    }
    fun getCurrentRoll(): LiveData<Int>{
        return currentRoll
    }

    fun setCurrentRoll(roll:Int){
        currentRoll.value = roll
    }

    fun setDieSides(sides: Int){
        dieSides.value = sides
    }

    fun getDieSides(): LiveData<Int>{
        return dieSides
    }


}