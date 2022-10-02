package es.unex.giiis.asee.masterdetailkotlin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.unex.giiis.asee.masterdetailkotlin.dummy.DummyContent

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.item_list, container, false)
        val recyclerView = v.findViewById<View>(R.id.item_list)!!
        setupRecyclerView(recyclerView as RecyclerView)
        return v
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS)
    }

    private class SimpleItemRecyclerViewAdapter constructor(
        parent: ItemListFragment,
        items: List<DummyContent.DummyItem>
    ) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
        private val mParentFragment: ItemListFragment
        private val mValues: List<DummyContent.DummyItem>
        private val mOnClickListener =
            View.OnClickListener { view ->
//                val item: DummyContent.DummyItem = view.tag as DummyContent.DummyItem
//                val context = view.context
//                val intent = Intent(context, ItemDetailActivity::class.java)
//                intent.putExtra(ItemDetailActivity.ARG_ITEM_ID, item.id)
//                context.startActivity(intent)
            }

        init {
            mValues = items
            mParentFragment = parent
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItemListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
