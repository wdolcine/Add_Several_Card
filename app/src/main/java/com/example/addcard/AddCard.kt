package com.example.addcard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class AddCard : AppCompatActivity() {
    private lateinit var flashcardDatabase: FlashcardDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_card)
        flashcardDatabase = FlashcardDatabase(this)

        val crossAddCard = findViewById<ImageView>(R.id.imageView4)
        val savebtn = findViewById<ImageView>(R.id.imageView5)
        val editTextField =findViewById<EditText>(R.id.editTextText)
        val editTextField1 =findViewById<EditText>(R.id.editTextText2)
        val editTextField2 =findViewById<EditText>(R.id.editTextText3)
        val editTextField3 =findViewById<EditText>(R.id.editTextText4)


        crossAddCard.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val question = intent.getStringExtra("question")
        val answer = intent.getStringExtra("answer")
        val wrongAnswer1 = intent.getStringExtra("wrongAnswer1")
        val wrongAnswer2 = intent.getStringExtra("wrongAnswer2")

        editTextField.setText(question)
        editTextField1.setText(answer)
        editTextField2.setText(wrongAnswer1)
        editTextField3.setText(wrongAnswer2)

        savebtn.setOnClickListener {
            val question1 = editTextField.text.toString()
            val answer0 = editTextField1.text.toString()
            val wrongAnswer = editTextField2.text.toString()
            val wrongAnswer0 = editTextField3.text.toString()

            if(question1.isBlank() || answer0.isBlank()){
                Snackbar.make(findViewById(R.id.imageView5), "Veuillez remplir tous les champs", Snackbar.LENGTH_SHORT).show()
            } else{
                Snackbar.make(findViewById(R.id.imageView5), "Card succesful Created", Snackbar.LENGTH_SHORT).show()


                val data = Intent()
                data.putExtra("question", question1)
                data.putExtra("answer", answer0)
                data.putExtra("wrongAnswer1", wrongAnswer)
                data.putExtra("wrongAnswer2", wrongAnswer0)

                flashcardDatabase.insertCard(Flashcard(question1, answer0,wrongAnswer,wrongAnswer0))


                setResult(Activity.RESULT_OK,intent)
                // reinitialiser l'activite
                val intent1 = Intent(this,MainActivity::class.java)
                startActivity(intent1)
                finish()
            }



        }
    }
}