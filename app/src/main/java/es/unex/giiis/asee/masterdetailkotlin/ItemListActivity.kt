package es.unex.giiis.asee.masterdetailkotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import es.unex.giiis.asee.masterdetailkotlin.dummy.DummyContent

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

//        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)
//        toolbar.title = title
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val recyclerView = findViewById<View>(R.id.item_list)!!
        setupRecyclerView(recyclerView as RecyclerView)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS)
    }

    private class SimpleItemRecyclerViewAdapter constructor(
        parent: ItemListActivity,
        items: List<DummyContent.DummyItem>
    ) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
        private val mParentActivity: ItemListActivity
        private val mValues: List<DummyContent.DummyItem>
        private val mOnClickListener =
            View.OnClickListener { view ->
                val item: DummyContent.DummyItem = view.tag as DummyContent.DummyItem
                val context = view.context
                val intent = Intent(context, ItemDetailActivity::class.java)
                intent.putExtra(ItemDetailActivity.ARG_ITEM_ID, item.id)
                context.startActivity(intent)
            }

        init {
            mValues = items
            mParentActivity = parent
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.mIdView.text = mValues[position].id
            holder.mContentView.text = mValues[position].content
            holder.itemView.tag = mValues[position]
            holder.itemView.setOnClickListener(mOnClickListener)
        }

        override fun getItemCount(): Int {
            return mValues.size
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val mIdView: TextView
            val mContentView: TextView

            init {
                mIdView = view.findViewById<View>(R.id.id_text) as TextView
                mContentView = view.findViewById<View>(R.id.content) as TextView
            }
        }
    }
}
