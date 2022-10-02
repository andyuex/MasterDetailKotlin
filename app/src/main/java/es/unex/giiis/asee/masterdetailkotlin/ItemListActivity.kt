package es.unex.giiis.asee.masterdetailkotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.unex.giiis.asee.masterdetailkotlin.dummy.DummyContent


/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity(), ItemListFragment.SelectionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val fragment = ItemDetailFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit()
        }

        if (savedInstanceState == null && !twoPane()) {
            val fragment = ItemListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, fragment)
                .commit()
        }
    }

    override fun onListItemSelected(item: DummyContent.DummyItem?) {
        if (!twoPane()) {
            val fragment = ItemDetailFragment()
            val bundle = Bundle()
            bundle.putString(ITEM_LIST_ARG_PARAM1, item?.id ?: "")
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit()
        } else {
            val fragment = supportFragmentManager.findFragmentById(R.id.detail_fragment) as ItemDetailFragment?
            fragment?.updateItem(item!!.id)
        }
    }

    private fun twoPane(): Boolean {
        return findViewById<View?>(R.id.detail_fragment) != null
    }
}
