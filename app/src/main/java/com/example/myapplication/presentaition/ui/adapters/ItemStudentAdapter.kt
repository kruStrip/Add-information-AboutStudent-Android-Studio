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
import com.example.myapplication.domain.models.Student
import com.example.myapplication.presentaition.ui.fragments.courses.AboutCourseFragment
import com.example.myapplication.presentaition.ui.fragments.students.AboutStudentFragment

class ItemStudentAdapter(private val activity: FragmentActivity) :
    RecyclerView.Adapter<ItemStudentAdapter.StudentViewHolder>() {

    private var studentList: List<Student> = emptyList() // Измените на var

    fun updateList(newList: List<Student>) {
        studentList = newList
        notifyDataSetChanged()
    }

    // Класс ViewHolder для хранения ссылок на элементы представления
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentImage: ImageView = itemView.findViewById(R.id.student_image_id)
        val studentName: TextView = itemView.findViewById(R.id.student_name_id)
        val studentPhone: TextView = itemView.findViewById(R.id.student_phone_id)
        val studentButton: Button = itemView.findViewById(R.id.about_student_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        // Создаем новый ViewHolder с нашим макетом элемента
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment == null) {
            throw IllegalArgumentException("Fragment cannot be null")
        }

        try {
            // Замена фрагмента
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_id, fragment)
                .addToBackStack(null) // Добавление транзакции в back stack
                .commitAllowingStateLoss() // Подтверждение транзакции
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Failed to replace fragment", e)
        }
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        // Получаем данные для текущей позиции
        val currentItem = studentList[position]

        // Устанавливаем данные в элементы
        holder.studentName.text = currentItem.name
        holder.studentPhone.text = currentItem.phoneNumber

        // Для изображения можно использовать Glide/Picasso
        Glide.with(holder.itemView.context)
            .load(currentItem.profilePicture)
            .into(holder.studentImage)

        holder.studentButton.setOnClickListener {
            val fragment = AboutStudentFragment.newInstance(currentItem)
            replaceFragment(fragment)
        }
    }
    override fun getItemCount() = studentList.size
}