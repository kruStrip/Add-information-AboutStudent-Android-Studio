package com.example.myapplication.presentaition.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.domain.models.Course
import com.example.myapplication.presentaition.ui.fragments.courses.AboutCourseFragment

class ItemCourseAdapter(private val activity: FragmentActivity) :
    RecyclerView.Adapter<ItemCourseAdapter.CourseViewHolder>() {
    private var courseList: List<Course> = emptyList()


    fun updateList(newList: List<Course>){
        courseList = newList
        notifyDataSetChanged()
    }


    // Класс ViewHolder для хранения ссылок на элементы представления
    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseImage: ImageView = itemView.findViewById(R.id.course_image_id)
        val courseName: TextView = itemView.findViewById(R.id.course_name_id)
        val courseIntro: TextView = itemView.findViewById(R.id.course_intro_id)
        val courseButton: Button = itemView.findViewById(R.id.about_course_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        // Создаем новый ViewHolder с нашим макетом элемента
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }
    private fun replaceFragment(fragmentName: String) {
        if (fragmentName.isEmpty()) {
            throw IllegalArgumentException("Fragment name cannot be empty")
        }

        try {
            // Создание фрагмента через фабрику фрагментов
            val fragment = activity.supportFragmentManager.fragmentFactory
                .instantiate(activity.classLoader, fragmentName)

            // Замена фрагмента
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_id, fragment)
                .addToBackStack(null) // Добавление транзакции в back stack
                .commitAllowingStateLoss() // Подтверждение транзакции
        } catch (e: Fragment.InstantiationException) {
            e.printStackTrace()
            throw RuntimeException("Failed to instantiate fragment: $fragmentName", e)
        }
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        // Получаем данные для текущей позиции
        val currentItem = courseList[position]

        // Устанавливаем данные в элементы
        holder.courseName.text = currentItem.name
        holder.courseIntro.text = currentItem.intro

        // Для изображения можно использовать Glide/Picasso
        Glide.with(holder.itemView.context)
            .load(currentItem.coursePicture)
            .into(holder.courseImage)

        holder.courseButton.setOnClickListener{
            replaceFragment(AboutCourseFragment::class.java.name)

        }
    }

    override fun getItemCount() = courseList.size
}

