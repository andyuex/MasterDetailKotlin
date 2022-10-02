package es.unex.giiis.asee.masterdetailkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import es.unex.giiis.asee.masterdetailkotlin.dummy.DummyContent


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mItem: DummyContent.DummyItem? = null

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
        val v = inflater.inflate(R.layout.activity_item_detail, container, false)

//        val toolbar = findViewById<View>(R.id.detail_toolbar) as Toolbar
//        setSupportActionBar(toolbar)
        val fab = v.findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Show the Up button in the action bar.
//        val actionBar = v.supportActionBar
//        actionBar?.setDisplayHomeAsUpEnabled(true)

        // Get dummy content for the selected item
//        val item = intent.getStringExtra(ItemDetailActivity.ARG_ITEM_ID)
        val item = "1"
        mItem = DummyContent.ITEM_MAP[item]

        // Change Title according to item
        val appBarLayout = v.findViewById<View>(R.id.toolbar_layout) as CollapsingToolbarLayout
        appBarLayout.title = mItem?.content

        // Show item content
        val tvDetail = v.findViewById<TextView>(R.id.item_detail)
        tvDetail.text = mItem?.details ?: ""
        return v
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar?.title = mItem?.content ?: ""
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = (activity as AppCompatActivity?)!!.title
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItemDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
