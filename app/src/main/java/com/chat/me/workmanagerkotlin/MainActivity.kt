package com.chat.me.workmanagerkotlin

import android.arch.lifecycle.Observer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.util.*

class MainActivity : AppCompatActivity(), TestFragment.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button: Button = findViewById(R.id.button)
        var button2: Button = findViewById(R.id.button2)
        var button3: Button = findViewById(R.id.button3)
        var button4: Button = findViewById(R.id.button4)
        var button6: Button = findViewById(R.id.button6)

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                /**
                 * Creating a normal onetime work request
                 */
                var oneTimeWorkRequest: OneTimeWorkRequest = OneTimeWorkRequest.Builder(NotificationWoker::class.java)
                        .addTag("workvisht")
                        .build()

                var workMgr: WorkManager = WorkManager.getInstance()
                workMgr.beginUniqueWork("workvisht", ExistingWorkPolicy.REPLACE, oneTimeWorkRequest)
                workMgr.enqueue(oneTimeWorkRequest)

                var value: TextView = findViewById(R.id.textView)
                workMgr.getWorkInfosByTagLiveData("workvisht").observe(this@MainActivity, object : Observer<MutableList<WorkInfo>> {
                    override fun onChanged(t: MutableList<WorkInfo>?) {
                        var tf: TestFragment = TestFragment.newInstance("${t!!.get(0).id}", t!!.get(0).state.name)
                        var fm: FragmentManager = supportFragmentManager
                        var ft: FragmentTransaction = fm.beginTransaction()
                        ft.add(R.id.fragment, tf)
                        ft.addToBackStack("TAG A")
                        ft.commit()
                        value.setText(t?.get(0)?.state?.name)
                        button2.setText("isFinished ${t?.get(0)?.state?.isFinished}")
                        button3.setText(t?.get(0)?.outputData.toString())
                        button4.setText("Ordinal ${t?.get(0)?.state?.ordinal}")
                        button6.setText("Work Id ${t?.get(0)?.id}")
                    }

                })
            }


        })
    }
}
