package com.example.restaurantapp.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.R
import com.example.restaurantapp.adapter.RestaurantAdapter
import com.example.restaurantapp.model.RestaurantItem

class HomeActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurantAdapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        setupRecyclerView()
        setupWelcomeMessage()
    }

    private fun initViews() {
        tvWelcome = findViewById(R.id.tvWelcome)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun setupRecyclerView() {
        restaurantAdapter = RestaurantAdapter()
        
        recyclerView.apply {
            adapter = restaurantAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }

        restaurantAdapter.setOnItemClickListener { item ->
            Toast.makeText(
                this,
                "Выбрано: ${item.name} - ₽${item.price}",
                Toast.LENGTH_SHORT
            ).show()
        }
            RestaurantItem(
                id = 1,
                name = "Борщ украинский",
                description = "Традиционный борщ со свеклой, капустой и сметаной",
                price = 320.0,
                imageResource = R.drawable.ic_food_placeholder,
                category = "Первые блюда",
                rating = 4.8f
            ),
            RestaurantItem(
                id = 2,
                name = "Стейк из говядины",
                description = "Сочный стейк средней прожарки с картофелем и овощами",
                price = 890.0,
                imageResource = R.drawable.ic_food_placeholder,
                category = "Мясные блюда",
                rating = 4.9f
            ),
            RestaurantItem(
                id = 3,
                name = "Паста Карбонара",
                description = "Классическая итальянская паста с беконом и сыром",
                price = 450.0,
                imageResource = R.drawable.ic_food_placeholder,
                category = "Итальянская кухня",
                rating = 4.7f
            ),
            RestaurantItem(
                id = 4,
                name = "Сёмга на гриле",
                description = "Филе сёмги на гриле с лимоном и зеленью",
                price = 650.0,
                imageResource = R.drawable.ic_food_placeholder,
                category = "Рыбные блюда",
                rating = 4.6f
            ),
            RestaurantItem(
                id = 5,
                name = "Цезарь с курицей",
                description = "Свежий салат Цезарь с курицей-гриль и сухариками",
                price = 380.0,
                imageResource = R.drawable.ic_food_placeholder,
                category = "Салаты",
                rating = 4.5f
            ),
            RestaurantItem(
                id = 6,
                name = "Тирамису",
                description = "Нежный итальянский десерт с маскарпоне и кофе",
                price = 290.0,
                imageResource = R.drawable.ic_food_placeholder,
                category = "Десерты",
                rating = 4.8f
            ),
            RestaurantItem(
                id = 7,
                name = "Том Ям",
                description = "Острый тайский суп с креветками и грибами",
                price = 420.0,
                imageResource = R.drawable.ic_food_placeholder,
                category = "Азиатская кухня",
                rating = 4.4f
            ),
            RestaurantItem(
                id = 8,
                name = "Пицца Маргарита",
                description = "Классическая пицца с томатами, моцареллой и базиликом",
                price = 520.0,
                imageResource = R.drawable.ic_food_placeholder,
                category = "Пицца",
                rating = 4.6f
            )
        )

        restaurantAdapter.submitList(restaurantItems)
    }
}
