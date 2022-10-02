package es.unex.giiis.asee.masterdetailkotlin

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import es.unex.giiis.asee.masterdetailkotlin.dummy.DummyContent

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : AppCompatActivity() {
    /**
     * The dummy content this activity is presenting.
     */
    private var mItem: DummyContent.DummyItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

//        val toolbar = findViewById<View>(R.id.detail_toolbar) as Toolbar
//        setSupportActionBar(toolbar)
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Show the Up button in the action bar.
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // Get dummy content for the selected item
        val item = intent.getStringExtra(ARG_ITEM_ID)
        mItem = DummyContent.ITEM_MAP.get(item)

        // Change Title according to item
        val appBarLayout = findViewById<View>(R.id.toolbar_layout) as CollapsingToolbarLayout
        if (appBarLayout != null) {
            appBarLayout.title = mItem?.content
        }

        // Show item content
        val tvDetail = findViewById<TextView>(R.id.item_detail)
        tvDetail.setText(mItem?.details ?: "")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {

            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(Intent(this, ItemListActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ARG_ITEM_ID = "ITEM"
    }
}
