package com.book.store.stock.bookstore.pages.order.scanner

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.FragmentOrderScannerBinding
import com.book.store.stock.bookstore.databinding.SettingScannerFragmentBinding
import com.book.store.stock.bookstore.pages.setting.new_data.book.add_new_book.scanner.SettingScannerFragment
import com.book.store.stock.bookstore.pages.setting.new_data.book.add_new_book.scanner.SettingScannerViewModel
import com.google.zxing.Result
import dagger.android.support.DaggerFragment
import me.dm7.barcodescanner.zxing.ZXingScannerView


class OrderScannerFragment : Fragment(), ZXingScannerView.ResultHandler {

    companion object {
        fun newInstance() = OrderScannerFragment()
    }

    private lateinit var binding: FragmentOrderScannerBinding
    private lateinit var scannerView: ZXingScannerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_scanner, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        scannerView = ZXingScannerView(activity)
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        Toast.makeText(
            activity, "Contents = " + rawResult!!.text.toString() +
                    ", Format = " + rawResult.barcodeFormat.toString(), Toast.LENGTH_SHORT
        ).show()
        val list = ArrayList<String>()
        list.add(0, rawResult.text)
        list.add(1, rawResult.resultMetadata.toString())
        list.add(2, rawResult.resultPoints.toString())
        findNavController().previousBackStackEntry?.savedStateHandle?.set("scanner_order", list)
        activity?.onBackPressed()
        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        val handler = Handler()
        handler.postDelayed({ scannerView.resumeCameraPreview(this) }, 2000)
    }

}