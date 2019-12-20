package com.example.android.navigation

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.navigation.databinding.FragmentGameBinding
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.android.navigation.database.EredmenyEntity
import com.example.android.navigation.database.EredmenyekRoom


class GameFragment() : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_game,
                container,
                false
        )
        val application = requireNotNull(this.activity).application
        Log.i("GameFragment", "Called ViewModelProviders.of")
        // Get the viewModel
        val dataSource = EredmenyekRoom.getInstance(application).eredmenyDao
        val viewModelFactory = GameViewModelFactory(dataSource, application)

        viewModel =
                ViewModelProviders.of(
                        this, viewModelFactory).get(GameViewModel::class.java)
        binding.gameViewModel = viewModel
        //binding.gameViewModel = viewModel
//        viewModel.score.observe(this, Observer { newScore ->
//            binding.leftStepsText.text = newScore.toString()
 //       })

        viewModel.scoreString.observe(this, Observer { newScore ->
                       binding.leftStepsText.text = newScore})
        //binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        //{ view: View ->
        //    dataSource.insert(EredmenyEntity(1, "as", "fdgf", 1))
        //}
        viewModel.nev1 = binding.playerOne.text.toString()
        viewModel.nev2 = binding.playerTwo.text.toString()
        loadBoard()
        mapBoardToUi()


        binding.setLifecycleOwner(this)
        return binding.root
    }

    private fun loadBoard(){
        for (i in viewModel.boardC.indices){
            for (j in viewModel.boardC.indices) {
                viewModel.boardC[i][j] = ImageView(context)
                viewModel.boardC[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 250
                    height = 230
                    bottomMargin = 5
                    topMargin = 5
                    leftMargin = 5
                    rightMargin = 5
                }
                viewModel.boardC[i][j]?.setBackgroundColor((ContextCompat.getColor(requireContext(), R.color.colorPrimary)))
                viewModel.boardC[i][j]?.setOnClickListener(CellClickListener(i, j))
                binding.gameBoard?.addView(viewModel.boardC[i][j])
            }

        }
    }
    inner class CellClickListener(private val i:Int, private val j:Int) : View.OnClickListener{

        override fun onClick(p0: View?) {
            //updateScoreText()
            viewModel.updateScoreText()
            if(!viewModel.board.playerOneWon()) {
                val cell = Cell(i, j)
                if(viewModel.next == 0){
                    viewModel.board.move(cell, Board.PLAYER1)
                    binding.textView.text = "X következik"
                    viewModel.next = 1
                } else {
                    viewModel.board.move(cell, Board.PLAYER2)
                    binding.textView.text = "O következik"
                    viewModel.next = 0
                }
            }

            when {
                viewModel.board.playerOneWon() -> {
                    gameFinished()
                   // view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment())

                }
            }
            mapBoardToUi()
        }
    }
//safeargs
    private fun gameFinished() {
        Toast.makeText(activity, "A játék befejeződött", Toast.LENGTH_SHORT).show()
        val action = GameFragmentDirections.actionGameFragmentToGameWonFragment()
        action.score = viewModel.score.value?:0
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun mapBoardToUi(){
        for(i in viewModel.board.board.indices){
            for(j in viewModel.board.board.indices){
                when(viewModel.board.board[i][j]){
                    Board.PLAYER1 -> {
                        viewModel.boardC[i][j]?.setImageResource(R.drawable.circle)
                        viewModel.boardC[i][j]?.isEnabled = false
                    }
                    Board.PLAYER2 -> {
                        viewModel.boardC[i][j]?.setImageResource(R.drawable.cross)
                        viewModel.boardC[i][j]?.isEnabled = false
                    }
                    else -> {
                        viewModel.boardC[i][j]?.setImageResource(0)
                        viewModel.boardC[i][j]?.isEnabled = true
                    }
                }
            }
        }
    }
/*
    private fun updateScoreText() {
        viewModel.updateScoreText()
        binding.leftStepsText.text = viewModel.score.value.toString()
    }*/
}
