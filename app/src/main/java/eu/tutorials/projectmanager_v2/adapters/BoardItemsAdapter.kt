package eu.tutorials.projectmanager_v2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eu.tutorials.projectmanager_v2.R
import eu.tutorials.projectmanager_v2.models.Board
import kotlinx.android.synthetic.main.item_board.view.*

open class BoardItemsAdapter(private val context:Context,
                             private var list:ArrayList<Board>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickListener:OnClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater
            .from(context)
            .inflate(R.layout.item_board,
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model=list[position]
        if(holder is MyViewHolder){
            Glide
                .with(context)
                .load(model.image)
                .centerCrop()
                .placeholder(R.drawable.ic_board_place_holder_24dp)
                .into(holder.itemView.iv_board_image_item_board)

            holder.itemView.tv_name.text=model.name
            holder.itemView.tv_created_by.text="Created by: ${model.createdBy}"

            holder.itemView.setOnClickListener {
                if(onClickListener!=null){
                    onClickListener!!.OnClick(position,model)
                }
            }
        }
    }

    interface OnClickListener{
        fun OnClick(position:Int,model:Board)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private class MyViewHolder(view:View):RecyclerView.ViewHolder(view)
}