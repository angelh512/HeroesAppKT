package mx.haya.heroesappkt.ui.adapters

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import mx.haya.heroesappkt.R
import mx.haya.heroesappkt.data.model.SuperHeroModel
import mx.haya.heroesappkt.util.Constants

class RecyclerViewItemAdapter(
    private var items: ArrayList<SuperHeroModel?>,
    private val listener: RecyclerViewItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var adapterContext: Context

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addData(dataViews: ArrayList<SuperHeroModel?>) {
        this.items.addAll(dataViews)
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): SuperHeroModel? {
        return items[position]
    }

    fun addLoadingView() {
        //Add loading item
        Handler(Looper.getMainLooper()).post {
            items.add(null)
            notifyItemInserted(items.size - 1)
        }
    }

    fun removeLoadingView() {
        //Remove loading item
        if (items.size != 0) {
            items.removeAt(items.size - 1)
            notifyItemRemoved(items.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        adapterContext = parent.context
        return if (viewType == Constants.VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(adapterContext).inflate(R.layout.loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == Constants.VIEW_TYPE_ITEM) {

            val tvNombre = holder.itemView.findViewById<TextView>(R.id.tvHeroNameItem)
            tvNombre.text = items[position]!!.name

            val ivHero = holder.itemView.findViewById<ImageView>(R.id.ivHeroItem)
            ivHero.load(items[position]!!.image.url)

            holder.itemView.setOnClickListener {
                listener.onItemClickListener(items[position]!!)
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] == null) {
            Constants.VIEW_TYPE_LOADING
        } else {
            Constants.VIEW_TYPE_ITEM
        }
    }

}
