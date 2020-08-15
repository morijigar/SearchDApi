package com.example.apifetch

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.apifetch.base.BaseActivity
import com.example.apifetch.databinding.ActivityDetailBinding
import com.example.apifetch.databse.Rows
import com.example.apifetch.models.Datum
import com.example.apifetch.viewmodel.DetailActivityViewModel
import com.example.myapplication.databse.Database


class DetailActivityActivity : BaseActivity<ActivityDetailBinding, DetailActivityViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_detail
    override val viewModel: DetailActivityViewModel
        get() = ViewModelProvider(this).get(DetailActivityViewModel::class.java)

    lateinit var data: Datum
    lateinit var image:String

    var row: Rows? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = intent.getParcelableExtra("bundle_data")!!
        image = intent.getStringExtra("bundle_image")!!

        getSupportActionBar()!!.setTitle(data.title!!)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);

        row = Database.getInstance(this).appDatabase.PostDao().getAIDData(data.id)

        if (row != null) {
            viewDataBinding!!.edtComment.setText(row!!.comment)
        }

        Datum.loadImage(viewDataBinding!!.ivPic1, image)

        viewDataBinding!!.btnSubmit.setOnClickListener {



            if (row == null) {
                var row = Rows()
                row.AID = data.id
                row!!.comment = viewDataBinding!!.edtComment.text.toString()
                Database.getInstance(this).appDatabase.PostDao().save(row!!)
            }else{
                row!!.comment = viewDataBinding!!.edtComment.text.toString()
                Database.getInstance(this).appDatabase.PostDao().update(row!!)
            }

            Toast.makeText(this,"Comment saved to database",Toast.LENGTH_LONG).show()

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }


}
