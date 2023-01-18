package com.example.y20230118_yesidhuertas_nycschools.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.y20230118_yesidhuertas_nycschools.dataaccess.models.School
import com.example.y20230118_yesidhuertas_nycschools.databinding.SchoolItemBinding

class SchoolAdapter(
    private var schools: List<School> = listOf(),
    private val onSchoolSelected: (School) -> Unit
): RecyclerView.Adapter<SchoolViewHolder>() {

    fun setNewSchools(newSchools: List<School>) {
        schools = newSchools
        notifyItemChanged(0, itemCount - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder =
        SchoolViewHolder(
            SchoolItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onSchoolSelected
        )

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) =
        holder.bind(schools[position])

    override fun getItemCount(): Int = schools.size
}

class SchoolViewHolder(
    private val binding: SchoolItemBinding,
    private val onSchoolSelected: (School) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(school: School) {
        with(binding) {
            dbn.text = school.dbn
            schoolName.text = school.schoolName
            schoolTotalStudents.text = school.totalStudents
            schoolWebsite.text = school.website
            root.setOnClickListener { onSchoolSelected(school) }
        }
    }
}