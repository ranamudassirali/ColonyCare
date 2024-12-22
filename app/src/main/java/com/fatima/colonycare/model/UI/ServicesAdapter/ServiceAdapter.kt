package com.fatima.colonycare.model.UI.ServicesAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fatima.colonycare.databinding.ItemDesignBinding
import com.fatima.colonycare.model.UI.ModelClasses.ServicesModelClass
import com.fatima.colonycare.model.UI.ServiceViewHolder.ServiceViewHolder
import com.google.gson.Gson


class ServiceAdapter(val services:List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        if (viewType == 0) {
            val binding =
                ItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ServiceViewHolder(binding)
//        }
//        return ServiceViewHolder(binding)

//        else if (viewType == 1) {
//            val binding =
//                RequestDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            return RequestViewHolder(binding)
//        } else {
//            val binding=HistoryDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            return HistoryViewHolder(binding)
//        }

    }


    override fun getItemCount(): Int {
        return services.size
    }

    override fun getItemViewType(position: Int): Int {
        if (services.get(position) is ServicesModelClass) return 0
//        if (donors.get(position) is RequestModelClass) return 1
//        if (donors.get(position) is HistoryModelClass) return 2
        return 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ServiceViewHolder) {
            val project = services.get(position) as ServicesModelClass
            holder.binding.sname.text = project.ServiceName
            holder.binding.sdesc.text = project.ServiceDescription
            holder.binding.sprice.text = project.ServicePrice.toString()
            holder.binding.spname.text = project.ServiceProviderName
            holder.binding.sprole.text = project.ServiceRole
            holder.binding.textReviews.text = "130 Reviews"
            holder.binding.star1.setImageResource(holder.itemView.context.resources.getIdentifier("round_star_24", "drawable", holder.itemView.context.packageName))
            holder.binding.star2.setImageResource(holder.itemView.context.resources.getIdentifier("round_star_24", "drawable", holder.itemView.context.packageName))
            holder.binding.star3.setImageResource(holder.itemView.context.resources.getIdentifier("round_star_24", "drawable", holder.itemView.context.packageName))
            holder.binding.star4.setImageResource(holder.itemView.context.resources.getIdentifier("round_star_24", "drawable", holder.itemView.context.packageName))
            holder.binding.star5.setImageResource(holder.itemView.context.resources.getIdentifier("round_star_24", "drawable", holder.itemView.context.packageName))
            holder.binding.imageProvider.setImageResource(holder.itemView.context.resources.getIdentifier("fatima", "drawable", holder.itemView.context.packageName))
            holder.binding.imageService.setImageResource(project.Serviceimage.toInt())




            holder.itemView.setOnClickListener {
//                holder.itemView.context.startActivity(
//                    Intent(
//                        holder.itemView.context,
//                        ProjectDetailsActivity::class.java
//                    ).putExtra("id", project.id)
//                )
            }

        }
//        if (holder is RequestViewHolder) {
//            val project1 = donors.get(position) as RequestModelClass
//            holder.binding.aname.text = project1.rname
//            holder.binding.anumber.text = project1.rphone
//            holder.binding.adesc.text = project1.rdesc
//            holder.binding.aamount.text = project1.rAmount.toString()
//            holder.binding.adate.text = project1.rdate
//
//
//            holder.itemView.setOnClickListener {
//                holder.itemView.context.startActivity(
//                    Intent(
//                        holder.itemView.context,
//                        DonationAmountActivity::class.java
//                    ).putExtra("data", Gson().toJson(project1))
//                )
//            }
//
//
//        }
//
//        if(holder is HistoryViewHolder){
//            val project2 = donors.get(position) as HistoryModelClass
//            holder.binding.aname.text = project2.req?.rname
//            holder.binding.anumber.text = project2.req?.rphone
//            holder.binding.adesc.text = project2.req?.rdesc
//            holder.binding.adate.text = project2.req?.rdate
//            holder.binding.ddamount.text = project2.req?.rAmount.toString()
//            holder.binding.ddamount2.text = project2.hAmount.toString()
//
//
       }
    }