package com.xwtiger.devrescollect.kotlin

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentActivity
import android.widget.TextView
import com.xwtiger.devrescollect.kotlin.model.TestPerson

class TestKotlinActivity : FragmentActivity() {

    private var x = "a";
    private var y = 2;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var tv = TextView(this)
        tv.setText("hahah")
        //tv.setBackgroundColor(Color.parseColor("#ff0000"))
        setContentView(tv)

        var p = TestPerson
        var tempstr = p.test()
        var str2 = TestPerson.test2()
        System.out.println(tempstr)
        System.out.println("str2="+str2)

        savedInstanceState?.apply {  }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }
}
