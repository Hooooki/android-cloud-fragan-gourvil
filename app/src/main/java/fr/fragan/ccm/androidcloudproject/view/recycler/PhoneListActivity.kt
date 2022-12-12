package fr.fragan.ccm.androidcloudproject.view.recycler

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.fragan.ccm.androidcloudproject.view.adapter.AndroidVersionAdapter
import fr.fragan.ccm.androidcloudproject.databinding.ActivityListPhoneBinding
import fr.fragan.ccm.androidcloudproject.model.PhoneDataFooterSample
import fr.fragan.ccm.androidcloudproject.model.PhoneDataHeaderSample
import fr.fragan.ccm.androidcloudproject.model.PhoneDataSample
import fr.fragan.ccm.androidcloudproject.model.SealedRecyclerViewItem

class PhoneListActivity : AppCompatActivity() {

    private lateinit var adapter: AndroidVersionAdapter
    private lateinit var binding: ActivityListPhoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AndroidVersionAdapter { item, view ->
            onItemClick(item, view)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        adapter.submitList(generateFakeData())
    }

    private fun onItemClick(phoneDataSample: PhoneDataSample, view : View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, "Ajouté au panier", Toast.LENGTH_LONG).show()
    }

    private fun generateFakeData(): MutableList<SealedRecyclerViewItem> {
        val result = mutableListOf<SealedRecyclerViewItem>()
        // Create data raw
        mutableListOf(
            PhoneDataSample(
                "Iphone 6",
                100.0,
                "https://logos-marques.com/wp-content/uploads/2021/03/logo-Apple.jpg",
                "Apple"
            ),
            PhoneDataSample(
                "Iphone 7",
                125.0,
                "https://assets.stickpng.com/images/580b57fcd9996e24bc43c516.png",
                "Apple"
            ),
            PhoneDataSample(
                "Iphone 7 Plus",
                130.0,
                "https://assets.stickpng.com/images/580b57fcd9996e24bc43c516.png",
                "Apple"
            ),
            PhoneDataSample(
                "Iphone 8",
                200.0,
                "https://assets.stickpng.com/images/580b57fcd9996e24bc43c516.png",
                "Apple"
            ),
            PhoneDataSample(
                "Iphone 8 Plus",
                230.0,
                "https://assets.stickpng.com/images/580b57fcd9996e24bc43c516.png",
                "Apple"
            ),
            PhoneDataSample(
                "Iphone X",
                500.0,
                "https://assets.stickpng.com/images/580b57fcd9996e24bc43c516.png",
                "Apple"
            ),
            PhoneDataSample(
                "Iphone XR",
                550.0,
                "https://assets.stickpng.com/images/580b57fcd9996e24bc43c516.png",
                "Apple"
            ),
            PhoneDataSample(
                "Iphone X Pro",
                700.0,
                "https://assets.stickpng.com/images/580b57fcd9996e24bc43c516.png",
                "Apple"
            ),
            PhoneDataSample(
                "Samsung Galaxy S6",
                100.0,
                "https://images.samsung.com/is/image/samsung/assets/global/about-us/brand/logo/mo/360_197_1.png",
                "Samsung"
            ),
            PhoneDataSample(
                "Samsung Galaxy S6 Mini",
                125.0,
                "https://images.samsung.com/is/image/samsung/assets/global/about-us/brand/logo/mo/360_197_1.png",
                "Samsung"
            ),
            PhoneDataSample(
                "Samsung Galaxy S8",
                130.0,
                "https://images.samsung.com/is/image/samsung/assets/global/about-us/brand/logo/mo/360_197_1.png",
                "Samsung"
            ),
            PhoneDataSample(
                "Samsung Galaxy S8 Plus",
                200.0,
                "https://images.samsung.com/is/image/samsung/assets/global/about-us/brand/logo/mo/360_197_1.png",
                "Samsung"
            ),
            PhoneDataSample(
                "Samsung Galaxy S11",
                230.0,
                "https://images.samsung.com/is/image/samsung/assets/global/about-us/brand/logo/mo/360_197_1.png",
                "Samsung"
            ),
            PhoneDataSample(
                "Samsung Galaxy S11 Max",
                500.0,
                "https://images.samsung.com/is/image/samsung/assets/global/about-us/brand/logo/mo/360_197_1.png",
                "Samsung"
            ),
            PhoneDataSample(
                "Samsung Galaxy Fold",
                550.0,
                "https://images.samsung.com/is/image/samsung/assets/global/about-us/brand/logo/mo/360_197_1.png",
                "Samsung"
            ),
            PhoneDataSample(
                "Samsung Galaxy Fold Pro",
                700.0,
                "https://images.samsung.com/is/image/samsung/assets/global/about-us/brand/logo/mo/360_197_1.png",
                "Samsung"
            )
        ).groupBy {
            it.brand
        }.forEach { (brand, items) ->
            result.add(PhoneDataHeaderSample(brand))
            result.addAll(items)
            result.add(PhoneDataFooterSample(brand, items.count()))
        }
        return result
    }

}